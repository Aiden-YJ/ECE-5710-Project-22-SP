import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

public class Handler2 implements RequestHandler<Map<String, String>, List<ArrayList<GearItem>>> {

    @Override
    public  List<ArrayList<GearItem>> handleRequest(Map<String, String> event, Context context)
    {
        LambdaLogger logger = context.getLogger();
        logger.log("event is: " + event);
        List<ArrayList<GearItem>> gearItems = new ArrayList<>();
        for(int imageindex = 0; imageindex < 5; imageindex++){
            String keyDataStr = event.get("keyData"+imageindex);
            keyDataStr = keyDataStr.substring(1,keyDataStr.length()-1);
            String[] keyDataStrArr = keyDataStr.split(",");
            byte[] keyData = new byte[keyDataStr.length()];
            for(int i = 0; i < keyDataStrArr.length; i++)
                keyData[i] = Byte.parseByte(keyDataStrArr[i].trim());
            logger.log(" keyData is: " + Arrays.toString(keyData));
            String item = event.get("item"+imageindex);

            AnalyzePhotos photos = new AnalyzePhotos();


            // Analyze the photo and return a list where each element is a WorkItem.
            ArrayList<GearItem> gearItem = photos.detectLabels(keyData, item);
            gearItems.add(gearItem);
        }
//        String keyDataStr = event.get("keyData");
//        keyDataStr = keyDataStr.substring(1,keyDataStr.length()-1);
//        String[] keyDataStrArr = keyDataStr.split(",");
//        byte[] keyData = new byte[keyDataStr.length()];
//        for(int i = 0; i < keyDataStrArr.length; i++)
//            keyData[i] = Byte.parseByte(keyDataStrArr[i].trim());
//        logger.log(" keyData is: " + Arrays.toString(keyData));
//        String item = event.get("item");
//
//        AnalyzePhotos photos = new AnalyzePhotos();
//
//
//        // Analyze the photo and return a list where each element is a WorkItem.
//        ArrayList<GearItem> gearItem = photos.detectLabels(keyData, item);

        return gearItems;
    }

}



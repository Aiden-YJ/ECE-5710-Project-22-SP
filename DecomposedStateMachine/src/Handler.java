import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

public class Handler implements RequestHandler<Map<String, String> , Map<String,String>> {

    @Override
    public Map<String,String> handleRequest(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        String bucketName = event.get("bucketName");
        int imageIndex = Integer.valueOf(event.get("imageNumber"));
        logger.log("Bucket name is: " + bucketName);
        Map<String,String> result = new HashMap<>();

        S3Service s3Service = new S3Service() ;
        for(imageIndex = 0; imageIndex < 5; imageIndex++){
            String item = s3Service.listBucketObjects(bucketName).get(imageIndex);
            byte[] keyData = s3Service.getObjectBytes(bucketName, item);
            result.put("keyData"+imageIndex,Arrays.toString(keyData));
            result.put("item"+imageIndex,item);
            logger.log("keyData is: "+ Arrays.toString(keyData));
        }

        // StringBuilder keyDataStr = new StringBuilder();
        // for(int i = 0; i < keyData.length; i++){
        //     keyDataStr.append(keyData[i]);
        // }
       // Map<String,String> result = new HashMap<>();
//        result.put("keyData",Arrays.toString(keyData));
//        result.put("item",item);
        logger.log("result is: " + result);
        return result;

    }

}



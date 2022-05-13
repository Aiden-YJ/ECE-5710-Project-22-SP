import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import java.util.List;
import java.util.ArrayList;

public class Handler3 implements RequestHandler< List<ArrayList<GearItem>>, String> {

    @Override
    public String handleRequest(List<ArrayList<GearItem>> event, Context context) {
        LambdaLogger logger = context.getLogger();
        DynamoDBService ddb = new DynamoDBService();
        List<ArrayList<GearItem>> gearItems = event;
        ddb.persistItem(gearItems);
        // Create a new list with only unique keys to email.
        //Set<String> unqiueKeys = createUniqueList(myList);
        //email.sendMsg(unqiueKeys);
        //logger.log("Updated the DynamoDB table with PPE data");
        logger.log("Updated the DynamoDB table with PPE data");
        return gearItems.toString();
    }

}



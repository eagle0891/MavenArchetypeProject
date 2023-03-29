package GraphQLRestAssured.Modules;

import io.restassured.path.json.JsonPath;
import stepDefinitions.APIStepsDefs;

import static Helpers.BaseClass.LOG;

public class ConsoleLogger {

    public static void printMessageToConsolestatic(String messageOutput) {
        LOG.info(messageOutput);
    }

    public void printMessageToConsole(String messageOutput){
        LOG.info(messageOutput);
    }

    public void API_Response_Data_Logger(String requestModuleToTest, JsonPath jsonPath, String dataField, APIStepsDefs apiStepsDefs) {
        switch (requestModuleToTest) {

            case "allStarWarsFilmsQuery":
                if (!dataField.equals("data") && !dataField.equals("moduleConfig")) {
                    apiStepsDefs.loggedResponses += ("\nKEY:[" + dataField.toUpperCase() + "] VALUE:[" + jsonPath.getString("data.moduleConfig." + dataField) + "]");
                }
                break;
            default:
                printMessageToConsole("**** ERROR::["+requestModuleToTest+"] Incorrect / invalid Module Query Type selected for API_Response_Data_Logger method, please check setup.");
        }
    }
}

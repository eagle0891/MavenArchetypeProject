package GraphQLRestAssured.Modules;

import GraphQLRestAssured.POJO.ModuleConfigQueries;

public class QuerySwitch extends ModuleConfigQueries {

    static String getRequestModuleQuery(String requestModuleToTest, String... injectorValues) {
        switch (requestModuleToTest) {
            case "allStarWarsFilmsQuery":
                return ModuleConfigQueries.returnModuleConfigAllStarWarsFilmsQuery();
            default:
                System.out.println("Incorrect Module Query Type selected - please ensure expected and valid query injected");
                return null;
        }
    }
}

package GraphQLRestAssured.Modules;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphQLDataFields {
    private static final ArrayList<String> ModuleConfigAllStarWarsFilms_dataFields = new ArrayList<>(Arrays.asList("allFilms", "films", "title"));
    public static ArrayList<String> returnModuleArray(String moduleType){
        switch(moduleType){
            case "allStarWarsFilmsQuery":
                return ModuleConfigAllStarWarsFilms_dataFields;
            default:
                System.out.println("ERROR :: DATAFIELD ATTRIBUTES MISSING OR INVALID :: please check and try again");
                return null;
        }
    }
}

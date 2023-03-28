package GraphQLRestAssured.POJO;

public class ModuleConfigQueries {

     static String allStarWarsFilmsQuery;

     public static String returnModuleConfigQueryAllStarWarsFilms(){
         return allStarWarsFilmsQuery = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\"}";
     }

}

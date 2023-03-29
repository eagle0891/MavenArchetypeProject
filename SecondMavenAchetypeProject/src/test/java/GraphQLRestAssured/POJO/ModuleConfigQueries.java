package GraphQLRestAssured.POJO;

public class ModuleConfigQueries {

     static String allStarWarsFilmsQuery;
     static String albumsListQuery;

     public static String returnModuleConfigAllStarWarsFilmsQuery(){
         return allStarWarsFilmsQuery = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\"}";
     }

    public static String returnModuleConfigAlbumsListQuery(){
        return albumsListQuery = "{\"query\":\"query fetchAlbum {\\n  Album {\\n    AlbumId\\n    Title\\n    Tracks {\\n      TrackId\\n      Name\\n    }\\n  }\\n}\\n\",\"variables\":null,\"operationName\":\"fetchAlbum\"}";
    }

}
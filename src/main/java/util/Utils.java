package util;

import com.google.gson.Gson;
import io.restassured.response.Response;

public class Utils {

    public static String convertDTOToJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Object covertResponseToDto(Response response, String dtoClassName) {
        Gson gson = new Gson();
        Object newObject = null;
        try {
            newObject = Class.forName(dtoClassName).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return gson.fromJson(response.getBody().asString(), newObject.getClass());
    }
}

package helper;

import org.json.JSONObject;

import java.util.Iterator;

public class JsonHelper {

    public static boolean assertEqualJson(String expectedResult,String actualResult){
        boolean isEqual=true;
        JSONObject expectedJSON = new JSONObject(expectedResult);
        JSONObject actualJSON= new JSONObject(actualResult);

        Iterator<?> keyList = expectedJSON.keys();
        while (keyList.hasNext()){
            String key = (String) keyList.next();
            if (expectedJSON.has(key) && actualJSON.has(key) ){
                String expectedValue= String.valueOf(expectedJSON.get(key));
                String actualValue= String.valueOf(actualJSON.get(key));
                if (expectedValue.equals("IGNORE") || actualValue.equals("IGNORE")){
                    System.out.println("IGNORE> se esta ignorando la comparacion de ["+key+"]");
                }else if (!expectedValue.equals(actualValue)){
                    System.out.println("ERROR!! el atributo ["+key+"] es diferente en actual ["+actualValue+"] " +
                            " vs expected ["+expectedValue+"]");
                    isEqual=false;
                }
            } else {
                System.out.println("ERROR!! el atributo ["+key+"] no existe!!!");
                isEqual=false;
            }
        }
        return isEqual;
    }
}

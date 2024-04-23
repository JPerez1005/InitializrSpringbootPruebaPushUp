package com.prueba.demo.exceptions;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author perez
 */
public class ExceptionUtil {
    
    private ExceptionUtil(){}
    
    public static ResponseEntity<String> getResponseEntity
        (String message, HttpStatus httpStatus){
            return new ResponseEntity<String>("Mensaje : "+message,httpStatus);
    }
     
    /*Con este metodo podemos crear un codigo aleatorio*/
    public static String getUuid(){
        Date date= new Date();
        long time=date.getTime();
        return "FACTURA-"+time;
    }
    
    public static JSONArray getJsonArrayFromString(String data) throws JSONException{
        JSONArray jsonArray=new JSONArray(data);
        return jsonArray;
    }
    
    public static Map<String,Object> getMapFromJson(String data){
        if(!Strings.isNullOrEmpty(data)){
            /*Gson nos permite serializar y deserealizar objetos en java,
            es decir convertir objetos java a json y al reves*/
            return new Gson().fromJson(data, new TypeToken<Map<String,Object>>(){
            }.getType());
        }
        return new HashMap<>();
    }
    
    public static boolean isFileExist(String path){
//        log.info("Dentro de isFileExist{}",path);
        try {
            File file = new File(path);
            return file!=null && file.exists() ? Boolean.TRUE:Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}

package com.eleks.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ivan.hrynchyshyn on 13.07.2017.
 */
public class Properties {
    private Map<String,String> properties = new HashMap<>();
    private static final String FILENAME = "D:\\work\\JavaEEExample\\src\\database.properties";


    public Properties() {
        readProperty();
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void readProperty(){

        try(FileReader fileReader = new FileReader(FILENAME); BufferedReader br = new BufferedReader(fileReader)) {
            String curentLine = null;
            while ((curentLine = br.readLine())!= null){
                String[] property = curentLine.split("=");
                properties.put(property[0],property[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

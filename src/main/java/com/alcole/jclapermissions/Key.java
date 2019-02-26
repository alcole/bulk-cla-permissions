package com.alcole.jclapermissions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Key {

    //create an object of SingleObject
    private static Key instance = null;

    public String ocp = "";

    //make the constructor private so that this class cannot be
    //instantiated
    private Key(){
        Path keyFile = Paths.get("key.txt");
        try (BufferedReader reader =
                     Files.newBufferedReader(keyFile)) {
            ocp = reader.readLine().trim();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    //Get the only object available
    public static String getKey(){
        if (instance == null) instance = new Key();
        return instance.ocp;
    }

}

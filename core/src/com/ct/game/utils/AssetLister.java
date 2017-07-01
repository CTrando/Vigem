package com.ct.game.utils;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Cameron on 6/15/2017.
 */
public class AssetLister {

    private final static String NAME = "assets";
    private final static String DIRECTORY = "core/assets";
    private static ArrayList<String> lines = new ArrayList<String>();

    public static void main(String[] args) {
        writeLines(new File(DIRECTORY));
        BufferedWriter output = null;
        try {
            File file = new File("core/assets");
            String outputText = lines.toString().substring(1, lines.toString().length()-1);
            output = new BufferedWriter(new FileWriter(file));
            output.write(outputText);
            output.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void writeLines(File file){
        if(file.listFiles() == null){
            if(!file.getName().equals(NAME)) {
                lines.add(file.getName());
            }
        } else {
            for(File child: file.listFiles()){
                writeLines(child);
            }
        }
    }
}

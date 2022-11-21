package de.buw.se4de.map;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;

import de.buw.se4de.ID;
import de.buw.se4de.Object;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class Map {
    Vector<String> vline = new Vector<>();
    private void readmap() {
        BufferedReader reader;
        String line = "";
        try {
            reader = new BufferedReader(new FileReader("app/src/main/resources/book"));
            line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                // read next line
                line = reader.readLine();
                vline.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Vector<Object> getMap(){
        Vector<Object> vwall = new Vector<>();
        int x = 0;
        int y = 0;
        for (String sline: vline){
            String[] arrays = sline.split("");
            for(String s: arrays){
                if (s == "1") {
                    Wall temp = new Wall(x, y, ID.Frame); //TODO new id
                    vwall.add(temp);
                }
                y += 36;
            }
            x += 40;
        }
        return vwall;
    }
}

package de.buw.se4de.map;

import java.io.*;
import java.util.Vector;

import de.buw.se4de.ID;

public class Map {
    public Vector<Wall> vwall;
    public Vector<Spawnarea> vspawn;
    public Map(){
        vwall = new Vector<>();
        vspawn = new Vector<>();
    }

    private Vector<String> readmap(String s) {
        Vector<String> vline = new Vector<>();
        BufferedReader reader;
        String line;
        try {                                  //remove "app/" from string when starting with gradle / add "app/" when using intellij
            reader = new BufferedReader(new FileReader("src/main/resources/map_" + s));
            line = reader.readLine();
            while (line != null) {
                // read next line
                line = reader.readLine();
                vline.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        vline.remove(vline.size()-1);
        return vline;
    }
    public void getMap(String mapname){
        Vector<String> vline;
        vline = readmap(mapname);
        int x = 0;
        int y = 30;
        for (String sline: vline){
            String[] arrays = sline.split(" ");
            for(String s: arrays){
                if (s.equals("1")) {
                    Wall temp = new Wall(x, y, ID.Wall);
                    vwall.add(temp);
                }
                if (s.equals("2")){
                    Spawnarea temp = new Spawnarea(x, y, ID.SPAWN);
                    vspawn.add(temp);
                }
                x += 30;
            }
            y += 30;
            x = 0;
        }
    }
}

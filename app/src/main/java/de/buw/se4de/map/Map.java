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
        for (int j = 0; j < vline.size(); ++j){
            String[] arrays = vline.get(j).split(" ");
            for(int i = 0; i < arrays.length; ++i){
                String s = arrays[i];
                int spriteID = -1;
                if (s.equals("1")) {
                    if (j+1 < vline.size() && !(vline.get(j+1).split(" ")[i].equals("1"))) {
                        spriteID = 1; // bottom part of wall
                    } else {
                        spriteID = 0; // top part of wall
                    }
                    Wall temp = new Wall(x, y, ID.Wall, spriteID);
                    vwall.add(temp);
                }
                if (s.equals("2")){
                    if (i-1>=0 && j-1>=0 
                            && vline.get(j-1).split(" ")[i].equals("0")
                            && arrays[i-1].equals("0")) {
                                spriteID = 5; // top left corner
                    }     
                    else if (i+1<arrays.length && j-1>=0 
                        && vline.get(j-1).split(" ")[i].equals("0")
                        && arrays[i+1].equals("0")) {
                            spriteID = 8; // top  right corner
                    } 
                    else if (i-1>=0 && j+1<vline.size() 
                        && vline.get(j+1).split(" ")[i].equals("0")
                        && arrays[i-1].equals("0")) {
                            spriteID = 9; // bottom left corner
                    }
                    else if (i+1<arrays.length && j+1<vline.size()
                        && vline.get(j+1).split(" ")[i].equals("0")
                        && arrays[i+1].equals("0")) {
                            spriteID = 2; // bottom right corner
                    }
                    else if (j-1>=0 && vline.get(j-1).split(" ")[i].equals("0")) {
                        spriteID = 6; // top tile
                    }
                    else if (i-1>=0 && arrays[i-1].equals("0")) {
                        spriteID = 7; // left tile
                    } 
                    else if (i-1>=0 && i+1<arrays.length && j-1>=0 && j+1<vline.size() 
                        && !vline.get(j-1).split(" ")[i].equals("0")
                        && !vline.get(j+1).split(" ")[i].equals("0")
                        && !arrays[i-1].equals("0") && !arrays[i+1].equals("0")) {
                            spriteID = 10; // middle tiles
                    } 
                    else if (i+1<arrays.length && arrays[i+1].equals("0")) {
                        spriteID = 4; // right tile
                    } 
                    else if (j+1<vline.size() && vline.get(j+1).split(" ")[i].equals("0")) {
                        spriteID = 3; // bottom tile
                    }
                    else {
                        spriteID = 10; // error tile, shown as middle
                    }
                    Spawnarea temp = new Spawnarea(x, y, ID.SPAWN, spriteID);
                    vspawn.add(temp);
                }
                x += 30;
            }
            y += 30;
            x = 0;
        }
    }
}

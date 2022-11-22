package de.buw.se4de;

import de.buw.se4de.entity.*;
import de.buw.se4de.gameflow.Handler;
import de.buw.se4de.map.Spawnarea;

import java.util.Random;
import java.util.Vector;

public class Wave {
    int wave;
    Handler handler;
    Vector<Spawnarea> vspawn;
    Vector<Enemy> vtospawn;
    int alive;
    Random r;
    final float cooldown = 2.0f;
    float oncooldown = 0.0f;
    boolean ready = false;
    public Wave(Handler h){
        handler = h;
        alive = 0;
        r = new Random();
        vspawn = new Vector<>();
        vtospawn = new Vector<>();
        wave = 0;
    }
    public void choosewave(){
        switch (wave) {
            case 0 -> {
                for(int i = 0; i<= 0; ++i){
                    int sa = r.nextInt(0,vspawn.size());
                    new Fire(vspawn.get(sa).getX(),vspawn.get(sa).getY(),handler);
                }
                alive = 1;
            }
            case 1-> {
                for (int i = 0; i <= 2; ++i) {
                    int sa = r.nextInt(0, vspawn.size());
                    new Fire(vspawn.get(sa).getX(), vspawn.get(sa).getY(), handler);
                }
                for(int i = 0; i <= 2; ++i){
                    int sa = r.nextInt(0, vspawn.size());
                    new RangedFire(vspawn.get(sa).getX(), vspawn.get(sa).getY(), handler);
                }
                alive = 6;
            }
            default ->{
                for (int i = 0; i < wave*5; ++i) {
                    int sa = r.nextInt(0, vspawn.size());
                    new Fire(vspawn.get(sa).getX(), vspawn.get(sa).getY(), handler);
                }
                for(int i = 0; i < wave*3; ++i){
                    int sa = r.nextInt(0, vspawn.size());
                    new RangedFire(vspawn.get(sa).getX(), vspawn.get(sa).getY(), handler);
                }
                alive = wave*5 +wave*3;
            }
        }
    }
    public void changespawn(Vector<Spawnarea> vs) {
        vspawn.clear();
        vspawn.addAll(vs);
    }
    public boolean onkill(){
        if(--alive <= 0){
            ++wave;
            return true;
        }
        return false;
    }
    public void newwave(){
        ready = true;
        oncooldown = cooldown;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public void tospawn(Enemy e) {
        vtospawn.add(e);
    }
    public void tick(float deltatick){
        if(vtospawn.size() > 0){
            vtospawn.removeIf(Enemy::respawn);
        }
        oncooldown-= (deltatick) / 60;
        if(oncooldown <= 0.0f && ready) {
            choosewave();
            ready = false;
        }
    }
}

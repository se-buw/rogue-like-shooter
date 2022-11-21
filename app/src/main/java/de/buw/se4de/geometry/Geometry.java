package de.buw.se4de.geometry;

public abstract class Geometry {
    public int x;
    public int y;
    public int width;
    public int height;

    public Geometry(int x, int y, int w, int h) {
        this.x=x;
        this.y = y;
        width=w;
        height=h;
    }

    public abstract boolean intersect(Rectangle r);
    public abstract boolean intersect(Circle r);

    public int getdrawx() {
        return x - width / 2;
    }

    public int getdrawy() {
        return y - height / 2;
    }
}

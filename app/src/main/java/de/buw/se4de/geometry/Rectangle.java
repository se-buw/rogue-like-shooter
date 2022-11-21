package de.buw.se4de.geometry;

public class Rectangle extends Geometry{
    int right;
    int left;
    int top;
    int bottom;

    public Rectangle(int x,int y, int w, int h){
        super(x,y,w,h);
        right = x + (width/2);
        left = x - (width/2);
        top = y + (width/2);
        bottom = y -(width/2);

    }
    @Override
    public boolean intersect(Rectangle r) {
        return false;
    }

    @Override
    public boolean intersect(Circle c) {
        c.intersect(this);
        return false;
    }
}

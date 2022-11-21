package de.buw.se4de.geometry;

public class Circle extends Geometry{
    public Circle(int x,int y,int rad){
        super(x,y,rad,rad);
    }
    @Override
    public boolean intersect(Rectangle r) {
        //TOP in range, BOT in range, Y between TOP&BOT
        return false;
    }

    @Override
    public boolean intersect(Circle c) {
        if((c.x-x)*(c.x-x) + (c.y - y)*(c.y - y) > (c.width + width)*(c.width + width) )
            return false;
        return true;
    }
}

package hu.elte.t8hgxr;

public class Point
{
    public int x;
    public int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Point()
    {
        this(0, 0);
    }

    public Point translate(int tx, int ty)
    {
        return new Point(this.x + tx, this.y + ty);
    }

    public Point translate(int txy)
    {
        return translate(txy, txy);
    }


}

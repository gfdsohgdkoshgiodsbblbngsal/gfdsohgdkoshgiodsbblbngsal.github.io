class Rectangle
{
    private int length;
    private int width;

    public Rectangle(int l, int w)
    {
        length = l;
        width = w;
    }

    public void draw()
    {
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < width; j++)
            {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 4a. Add an area method to compute the area of the rectangle.
    public int area() {
        return length*width;
    }
}

// 1. Make the class square inherit from Rectangle
public class Square extends Rectangle
{

    // 2. Add a Square constructor with 1 argument for a side
    public Square(int side) {
        super(side, side);
    }

    public static void main(String[] args)
    {
        Rectangle r = new Rectangle(3, 5);
        r.draw();
        Square s1 = new Square(1);
        s1.draw();
        Square s = new Square(3);
        s.draw();

        // 4b. Add some tests for your area method after you write it
    }
}

  // 5. Define the LongRectangle class here
  //    Do not make it public because only 1 class with main can be public in 1 file.
class LongRectangle extends Rectangle {
    public LongRectangle(int width) {
        super(width*2, width);
    }
}

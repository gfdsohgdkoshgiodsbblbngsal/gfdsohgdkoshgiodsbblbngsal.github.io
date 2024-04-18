import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture
{
    ///////////////////// constructors //////////////////////////////////

    /** Constructor that takes no arguments */
    public Picture()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor
         */
        super();
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the height and width
     *
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Constructor that takes a picture and creates a copy of that picture
     *
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     *
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName, height
     *     and width.
     */
    public String toString()
    {
        String output =
                "Picture, filename "
                        + getFileName()
                        + " height "
                        + getHeight()
                        + " width "
                        + getWidth();
        return output;
    }

    /** zeroBlue() method sets the blue values at all pixels to zero */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();

        for (Pixel[] rowArray : pixels)
        {
            for (Pixel p : rowArray)
            {
                p.setBlue(0);
            }
        }
    }

    /* Add new methods here.
       keepOnlyBlue() method sets the red and green values at all pixels to zero.
       switchColors() method switches colors, for example the red values with green values, etc.
    */
    public void keepOnlyBlue()
    {
        Pixel[][] pixels = this.getPixels2D();

        for (Pixel[] rowArray : pixels)
        {
            for (Pixel p : rowArray)
            {
                p.setRed(0);
                p.setGreen(0);
            }
        }
    }
    
    public void switchColors()
    {
        Pixel[][] pixels = this.getPixels2D();
    
        int replace_value = 0;
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel p : rowArray)
            {
                replace_value = (Math.random() < 0.5) ? p.getBlue() : p.getGreen();
                p.setRed(replace_value);
            }
        }
    }

    /* Main method for testing
     */
    public static void main(String[] args)
    {
        Picture arch = new Picture("arch.jpg");
        arch.show();
        arch.zeroBlue();
        arch.show();

        Picture arch2 = new Picture("arch.jpg");
        System.out.println("Keep only blue: ");
        arch2.keepOnlyBlue();// using new method
        arch2.show();
        System.out.println();

        Picture arch3 = new Picture("arch.jpg");
        System.out.println("Switch colors: ");
        arch3.switchColors();// using new method
        arch3.show();
    }
}


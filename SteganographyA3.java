import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class SteganographyA3 {
    public static void clearLow(Pixel p) {
        Color original_color = p.getColor();
        
        int red = (original_color.getRed() & 0xFC);
        int green = (original_color.getGreen() & 0xFC);
        int blue = (original_color.getBlue() & 0xFC);
        
        Color newColor = new Color(red, green, blue);
        
        p.setColor(newColor);
    }

    public static Picture testClearLow(Picture pic) {
        for (Pixel p : pic.getPixels()) {
            clearLow(p);
        }
        
        return pic;
    }

    public static Picture revealPicture(Picture hidden) {
        Picture copy = new Picture(hidden);
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = hidden.getPixels2D();
        for (int r = 0; r < pixels.length; r++) {
            for (int c = 0; c < pixels[0].length; c++) {
                Color col = source[r][c].getColor();
                int red = (col.getRed() % 4) * 64;
                int green = (col.getGreen() % 4) * 64;
                int blue = (col.getBlue() % 4) * 64;

                Color newColor = new Color(red, green, blue);
                pixels[r][c].setColor(newColor);
            }
        }
        return copy;
    }


    public static boolean canHide(Picture source, Picture secret) {
        if (source.getWidth() < secret.getWidth() || source.getHeight() < secret.getHeight()) {
            return false;
        } 

        return true;
    }

    public static Picture hidePicture(Picture source, Picture secret, int x, int y) {
        Picture copy = new Picture(source);
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] sourcePixels = source.getPixels2D();
        Pixel[][] secretPixels = secret.getPixels2D();

        for (int r = y; r < secretPixels.length + y; r++) {
            for (int c = x; c < secretPixels[0].length + x; c++) {
                Color col = secretPixels[r-y][c-x].getColor();
                
                int red = (col.getRed() / 64) + ((sourcePixels[r][c].getRed() / 4) * 4);
                int green = (col.getGreen() / 64) + ((sourcePixels[r][c].getGreen() / 4) * 4);
                int blue = (col.getBlue() / 64) + ((sourcePixels[r][c].getBlue() / 4) * 4);

                Color newColor = new Color(red, green, blue);
                pixels[r][c].setColor(newColor);
            }
        }

        return copy;
    }

    public static boolean isSame(Picture p1, Picture p2) {
        Pixel[][] pixels1 = p1.getPixels2D();
        Pixel[][] pixels2 = p2.getPixels2D();
        for (int r = 0; r < pixels1.length; r++) {
            for (int c = 0; c < pixels1[0].length; c++) {
                if (!pixels1[r][c].getColor().equals(pixels2[r][c].getColor())) {
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<Point> findDifferences(Picture p1, Picture p2) {
        if (!canHide(p1, p2)) {
            return new ArrayList<Point>();
        }

        ArrayList<Point> differences = new ArrayList<Point>();
        Pixel[][] pixels1 = p1.getPixels2D();
        Pixel[][] pixels2 = p2.getPixels2D();
        for (int r = 0; r < pixels1.length; r++) {
            for (int c = 0; c < pixels1[0].length; c++) {
                if (!pixels1[r][c].getColor().equals(pixels2[r][c].getColor())) {
                    differences.add(new Point(c, r));
                }
            }
        }
        return differences;
    }

    public static Picture showDifferentArea(Picture p1, Picture p2) {
        // draws a red rectangle around the area where the two pictures differ

        if (!canHide(p1, p2)) {
            return new Picture(p1);
        }

        Picture copy = new Picture(p1);
        Pixel[][] pixels = copy.getPixels2D();
        ArrayList<Point> differences = findDifferences(p1, p2);
        for (Point p : differences) {
            int x = (int) p.getX();
            int y = (int) p.getY();
            pixels[x][y].setRed(255);
        }

        return copy;
    }

    public static void main(String[] args) {
        /* 
        Picture beach = new Picture("beach.jpg");
        Picture robot = new Picture("robot.jpg");
        Picture flower1 = new Picture("flower1.jpg");
        beach.explore();
        // these lines hide 2 pictures
        Picture hidden1 = hidePicture(beach, robot, 65, 208);
        Picture hidden2 = hidePicture(hidden1, flower1, 280, 110);
        hidden2.explore();
        Picture unhidden = revealPicture(hidden2);
        unhidden.explore();

        Picture swan = new Picture("swan.jpg");
        Picture swan2 = new Picture("swan.jpg");

        System.out.println("Swan and swan2 are the same: " + isSame(swan, swan2));

        swan = testClearLow(swan);

        System.out.println("Swan and swan2 are the same (after clearLow run on swan): " + isSame(swan, swan2));
        */

        Picture arch = new Picture("arch.jpg");
        Picture arch2 = new Picture("arch.jpg");
        Picture koala = new Picture("koala.jpg");
        Picture robot1 = new Picture("robot.jpg");
        // arch2 wasn't defined in the original code for some reason?..
        ArrayList<Point> pointList = findDifferences(arch, arch2);
        System.out.println("PointList after comparing two identical pictures " + "has a size of " + pointList.size());
        pointList = findDifferences(arch, koala);
        System.out.println("PointList after comparing two different sized pictures " + "has a size of " + pointList.size());
        arch2 = hidePicture(arch, robot1, 65, 102);
        pointList = findDifferences(arch, arch2);
        System.out.println("Pointlist after hiding a picture has a size of " + pointList.size());
        arch.show();
        arch2.show();

        Picture diff = showDifferentArea(arch, arch2);
        diff.show();
    }
}

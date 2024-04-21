import java.awt.Color;

public class Steganography {
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

    public static void setLow(Pixel p, Color c) {
        Color original_color = p.getColor();

        int red = (original_color.getRed() / 4) * 4;
        int green = (original_color.getGreen() / 4) * 4;
        int blue = (original_color.getBlue() / 4) * 4;

        int red_c = c.getRed() / 64;
        int green_c = c.getGreen() / 64;
        int blue_c = c.getBlue() / 64;

        red += red_c;
        green += green_c;
        blue += blue_c;

        Color newColor = new Color(red, green, blue);
        p.setColor(newColor);
    }

    public static Picture testSetLow(Picture pic, Color c) {
        for (Pixel p : pic.getPixels()) {
            setLow(p, c);
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
                int red = (col.getRed() / 4) + ((col.getRed() % 4) * 64);
                int green = (col.getGreen() / 4) + ((col.getGreen() % 4) * 64);
                int blue = (col.getBlue() / 4) + ((col.getBlue() % 4) * 64);

                Color newColor = new Color(red, green, blue);
                pixels[r][c].setColor(newColor);
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        /*
        Picture beach = new Picture ("beach.jpg");
        beach.explore();
        Picture copy = testClearLow(beach);
        copy.explore();
        */

        Picture beach2 = new Picture ("beach.jpg");
        beach2.explore();
        Picture copy2 = testSetLow(beach2, Color.PINK);
        copy2.explore();
        Picture copy3 = revealPicture(copy2);
        copy3.explore();
    }
}

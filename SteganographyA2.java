import java.awt.Color;

public class SteganographyA2 {
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

    public static Picture hidePicture(Picture source, Picture secret) {
        Picture copy = new Picture(source);
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] sourcePixels = source.getPixels2D();
        Pixel[][] secretPixels = secret.getPixels2D();

        for (int r = 0; r < secretPixels.length; r++) {
            for (int c = 0; c < secretPixels[0].length; c++) {
                Color col = secretPixels[r][c].getColor();
                
                int red = (col.getRed() / 64) + ((sourcePixels[r][c].getRed() / 4) * 4);
                int green = (col.getGreen() / 64) + ((sourcePixels[r][c].getGreen() / 4) * 4);
                int blue = (col.getBlue() / 64) + ((sourcePixels[r][c].getBlue() / 4) * 4);

                Color newColor = new Color(red, green, blue);
                pixels[r][c].setColor(newColor);
            }
        }

        return copy;
    }

    public static void main(String[] args) {
        Picture source = new Picture("blue-mark.jpg");
        Picture secret = new Picture("beach.jpg");

        if (canHide(source, secret)) {
            Picture hidden = hidePicture(source, secret);
            hidden.show();
            Picture revealed = revealPicture(hidden);
            revealed.show();
        } else {
            System.out.println("Cannot hide the secret picture in the source picture.");
        }
    }
}

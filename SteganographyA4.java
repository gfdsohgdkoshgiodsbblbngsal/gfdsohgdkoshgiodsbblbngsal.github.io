import java.awt.Color;
import java.util.ArrayList;

public class SteganographyA4 {
    /**
    * Takes a string consisting of letters and spaces and
    * encodes the string into an arraylist of integers.
    * The integers are 1-26 for A-Z, 27 for space, and 0 for end of
    * string. The arraylist of integers is returned.
    * @params string consisting of letters and spaces
    * @return ArrayList containing integer encoding of uppercase
    * version of s
    */
    public static ArrayList<Integer> encodeString(String s) {
        s = s.toUpperCase();
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i,i+1).equals(" ")) {
                result.add(27);
            } else {
                result.add(alpha.indexOf(s.substring(i,i+1))+1);
            }
        }

        result.add(0);
        return result;
    }

    /**
    * Returns the string represented by the codes arraylist.
    * 1-26 = A-Z, 27 = space
    * @param codes encoded string
    * @return decoded string
    */
    private static String decodeString(ArrayList<Integer> codes)
    {
        String result="";
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=0; i < codes.size(); i++) {
            if (codes.get(i) == 27) {
                result = result + " ";
            } else {
                result = result + alpha.substring(codes.get(i)-1,codes.get(i));
            }
        }
        return result;
    }

    /**
     * Given a number from 0 to 63, creates and returns a 3-element
     * int array consisting of the integers representing the
     * pairs of bits in the number from right to left.
     * @param num number to be broken up into bit pairs
     * @return bit pairs in number
     */
    private static int[] getBitPairs(int num)
    {
        int[] bits = new int[3];
        int code = num;
        for (int i = 0; i < 3; i++)
        {
            bits[i] = code % 4;
            code = code / 4;
        }
        return bits;
    }

    public static void hideText(Picture source, String s) {
        ArrayList<Integer> codes = encodeString(s);
        Pixel[][] pixels = source.getPixels2D();
        for (int i = 0; i < codes.size(); i++) {
            Color col = pixels[0][i].getColor();
            int[] pairs = getBitPairs(codes.get(i));

            int red = ((col.getRed() / 4) * 4) + pairs[0];
            int green = ((col.getGreen() / 4) * 4) + pairs[1];
            int blue = ((col.getBlue() / 4) * 4) + pairs[2];

            Color newColor = new Color(red, green, blue);
            pixels[0][i].setColor(newColor);
        }
    }

    public static String revealText(Picture source) {
        Pixel[][] pixels = source.getPixels2D();
        ArrayList<Integer> codes = new ArrayList<Integer>();
        for (int i = 0; i < pixels[0].length; i++) {
            Color col = pixels[0][i].getColor();
            int red = col.getRed() % 4;
            int green = col.getGreen() % 4;
            int blue = col.getBlue() % 4;

            int code = red + (green * 4) + (blue * 16);
            if (code == 0) {
                break;
            }
            codes.add(code);
        }
        return decodeString(codes);
    }

    public static void main(String[] args) {
        Picture source = new Picture("blue-mark.jpg");
        String s = "HELLO WORLD";
        hideText(source, s);

        System.out.println(revealText(source));
    }
}

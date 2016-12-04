package us.logr.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    public static BufferedImage imageFromByte(byte[] imageInByte){
        InputStream in = new ByteArrayInputStream(imageInByte);
        try {
            BufferedImage bImageFromConvert = ImageIO.read(in);
            return bImageFromConvert;
        }catch (IOException e){
            Logger.error("Can't parse image from byte", e);
            return null;
        }
    }

    public static byte[] getPixels(BufferedImage img) {
        DataBufferByte imageData = (DataBufferByte) img
                .getRaster()
                .getDataBuffer();
        byte[] pixels = imageData.getData();
        return pixels;
    }

    public static int[] rgb2yuv(int[] pixel) {
        int r = pixel[0];
        int g = pixel[1];
        int b = pixel[2];
        int[] yuv = new int[3];

        int y = (int)(0.299 * r + 0.587 * g + 0.114 * b);
        int u = (int)((b - y) * 0.492f);
        int v = (int)((r - y) * 0.877f);

        yuv[0]= y;
        yuv[1]= u;
        yuv[2]= v;

        return yuv;
    }

    /**
     * https://fr.wikipedia.org/wiki/SRGB
     */
    public static double luminance(int[] pixel) {
        int r = pixel[0];
        int g = pixel[1];
        int b = pixel[2];

        return 0.2126 * r + 0.7152 * g + 0.0722 * b;
    }

    /**
     * http://www.w3.org/TR/2008/REC-WCAG20-20081211/#contrast-ratiodef
     */
    public static double contrastRatio(int[] color1, int[] color2) {
        double l1 = luminance(color1);
        double l2 = luminance(color2);

        double max_l = Math.max(l1, l2);
        double min_l = Math.min(l1, l2);

        double ratio = (max_l + 0.05) / (min_l + 0.05);
        return ratio;
    }

    public static boolean isContrastRatioEnough(int[] color1, int[] color2) {
        return contrastRatio(color1, color2) >= 7;
    }

    public static int[] getLighterColor(int[] color1, int[] color2) {
        double l1 = luminance(color1);
        double l2 = luminance(color2);

        return (l1 > l2) ? color1 : color2;
    }
}

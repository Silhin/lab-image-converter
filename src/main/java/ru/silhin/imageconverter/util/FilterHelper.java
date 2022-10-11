package ru.silhin.imageconverter.util;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public final class FilterHelper {

    public static Color RGBValidation(final double r, final double g, final double b) {
        return RGBValidation(r, g, b, 1.0F);
    }

    public static Color RGBValidation(final double r, final double g, final double b, final double a) {
        double red = r < 0 ? 0 : Math.min(r, 1.0F);
        double green = g < 0 ? 0 : Math.min(g, 1.0F);
        double blue = b < 0 ? 0 : Math.min(b, 1.0F);
        double alpha = a < 0 ? 0 : Math.min(a, 1.0F);

        return new Color(red, green, blue, alpha);
    }

    public static WritableImage filterImage(Image originalImage, Filterable filterable) {
        WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());

        for(int y = 0; y < originalImage.getHeight(); ++y) {
            for(int x = 0; x < originalImage.getWidth(); ++x) {
                filterable.filter(originalImage, out, x, y);
            }
        }

        return out;
    }

    public static WritableImage filterImage(Image originalImage, int yShift, int xShift, Filterable filterable) {
        WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());

        for(int y = yShift; y < originalImage.getHeight() - yShift; ++y) {
            for(int x = xShift; x < originalImage.getWidth() - xShift; ++x) {
                filterable.filter(originalImage, out, x, y);
            }
        }

        return out;
    }

}

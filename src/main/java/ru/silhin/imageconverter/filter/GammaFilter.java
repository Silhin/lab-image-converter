package ru.silhin.imageconverter.filter;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.util.FilterHelper;

public class GammaFilter implements IFilter {
    public static double GAMMA_C = 1D;
    public static double GAMMA_Y = 1.5D;

    @Override
    public Image useFilter(Image originalImage) {
        return FilterHelper.filterImage(originalImage, (original, out, x, y) -> {
            Color originalColor = original.getPixelReader().getColor(x, y);

            double r = GAMMA_C * Math.pow(originalColor.getRed(), GAMMA_Y);
            double g = GAMMA_C * Math.pow(originalColor.getGreen(), GAMMA_Y);
            double b = GAMMA_C * Math.pow(originalColor.getBlue(), GAMMA_Y);

            Color color = FilterHelper.RGBValidation(r, g, b, originalColor.getOpacity());
            out.getPixelWriter().setColor(x, y, color);
        });
    }

}

package ru.silhin.imageconverter.filter;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.util.FilterHelper;

public class LinearFilter implements IFilter {
    public static int LINEAR_RADIUS = 1;

    @Override
    public Image useFilter(Image originalImage) {
        return FilterHelper.filterImage(originalImage, LINEAR_RADIUS, LINEAR_RADIUS, (original, out, x, y) -> {
            double r = 0F;
            double g = 0F;
            double b = 0F;

            int count = 0;
            for (int i = y - LINEAR_RADIUS; i <= y + LINEAR_RADIUS; ++i) {
                for (int j = x - LINEAR_RADIUS; j <= x + LINEAR_RADIUS; ++j) {
                    Color z = original.getPixelReader().getColor(j, i);

                    r += z.getRed();
                    g += z.getGreen();
                    b += z.getBlue();

                    ++count;
                }
            }

            r /= count;
            g /= count;
            b /= count;

            Color color = FilterHelper.RGBValidation(r, g, b);
            out.getPixelWriter().setColor(x, y, color);
        });
    }

}

package ru.silhin.imageconverter.filter.lab4;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.filter.IFilter;
import ru.silhin.imageconverter.util.FilterHelper;

public class LaplasianFilter implements IFilter {

    @Override
    public Image useFilter(Image originalImage) {
        return FilterHelper.filterImage(originalImage, 1, 1, (original, out, x, y) -> {
            double dR = 0;
            double dG = 0;
            double dB = 0;

            for (int i = y - 1; i <= y + 1; ++i) {
                for (int j = x - 1; j <= x + 1; ++j) {
                    Color z = original.getPixelReader().getColor(j, i);

                    dR += (i == y && j == x ? -8 : 1) * z.getRed();
                    dG += (i == y && j == x ? -8 : 1) * z.getGreen();
                    dB += (i == y && j == x ? -8 : 1) * z.getBlue();
                }
            }

            Color originalColor = original.getPixelReader().getColor(x, y);
            double r = originalColor.getRed() - dR;
            double g = originalColor.getGreen() - dG;
            double b = originalColor.getBlue() - dB;

            Color color = FilterHelper.RGBValidation(r, g, b, originalColor.getOpacity());
            out.getPixelWriter().setColor(x, y, color);
        });
    }

}

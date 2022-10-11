package ru.silhin.imageconverter.filter.lab3;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.filter.IFilter;
import ru.silhin.imageconverter.util.FilterHelper;

public class RobertsFilter implements IFilter {

    @Override
    public Image useFilter(Image originalImage) {
        final int[] X_MATRIX_ROBERTS = new int[]{-1, 0, 0, 1};
        final int[] Y_MATRIX_ROBERTS = new int[]{0, -1, 1, 0};

        return FilterHelper.filterImage(originalImage, 1, 1, (original, out, x, y) -> {
            double rX = 0;
            double gX = 0;
            double bX = 0;
            double rY = 0;
            double gY = 0;
            double bY = 0;

            int index = 0;
            for (int i = y; i <= y + 1; ++i) {
                for (int j = x; j <= x + 1; ++j) {
                    Color z = original.getPixelReader().getColor(j, i);

                    rX += X_MATRIX_ROBERTS[index] * z.getRed();
                    gX += X_MATRIX_ROBERTS[index] * z.getGreen();
                    bX += X_MATRIX_ROBERTS[index] * z.getBlue();

                    rY += Y_MATRIX_ROBERTS[index] * z.getRed();
                    gY += Y_MATRIX_ROBERTS[index] * z.getGreen();
                    bY += Y_MATRIX_ROBERTS[index] * z.getBlue();

                    ++index;
                }
            }

            double r = Math.abs(rX) + Math.abs(rY);
            double g = Math.abs(gX) + Math.abs(gY);
            double b = Math.abs(bX) + Math.abs(bY);

            Color color = FilterHelper.RGBValidation(r, g, b);
            out.getPixelWriter().setColor(x, y, color);
        });
    }

}

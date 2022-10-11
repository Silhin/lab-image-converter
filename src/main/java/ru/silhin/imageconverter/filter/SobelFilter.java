package ru.silhin.imageconverter.filter;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.util.FilterHelper;

public class SobelFilter implements IFilter {

    @Override
    public Image useFilter(Image originalImage) {
        final int[] X_SOBEL_MATRIX = new int[]{-1, 0, 1, -2, 0, 2, -1, 0, 1};
        final int[] Y_SOBEL_MATRIX = new int[]{-1, -2, -1, 0, 0, 0, 1, 2, 1};

        return FilterHelper.filterImage(originalImage, 1, 1, (original, out, x, y) -> {
            double rX = 0;
            double gX = 0;
            double bX = 0;
            double rY = 0;
            double gY = 0;
            double bY = 0;

            int index = 0;
            for (int i = y - 1; i <= y + 1; ++i) {
                for (int j = x - 1; j <= x + 1; ++j) {
                    Color z = originalImage.getPixelReader().getColor(j, i);

                    rX += X_SOBEL_MATRIX[index] * z.getRed();
                    gX += X_SOBEL_MATRIX[index] * z.getGreen();
                    bX += X_SOBEL_MATRIX[index] * z.getBlue();

                    rY += Y_SOBEL_MATRIX[index] * z.getRed();
                    gY += Y_SOBEL_MATRIX[index] * z.getGreen();
                    bY += Y_SOBEL_MATRIX[index] * z.getBlue();

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

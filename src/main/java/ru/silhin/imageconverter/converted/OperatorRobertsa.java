package ru.silhin.imageconverter.converted;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class OperatorRobertsa implements IConvertingProcess {

    @Override
    public Image converting(Image originalImage) {
        WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());
        final int[] xRobertsaMatrix = new int[]{-1, 0, 0, 1};
        final int[] yRobertsaMatrix = new int[]{0, -1, 1, 0};

        for (int y = 0; y < originalImage.getHeight() - 1; ++y) {
            for (int x = 0; x < originalImage.getWidth() - 1; ++x) {

                double rX = 0;
                double gX = 0;
                double bX = 0;
                double rY = 0;
                double gY = 0;
                double bY = 0;

                int index = 0;
                for (int i = y; i <= y + 1; ++i) {
                    for (int j = x; j <= x + 1; ++j) {
                        Color z = originalImage.getPixelReader().getColor(j, i);

                        rX += xRobertsaMatrix[index] * z.getRed();
                        gX += xRobertsaMatrix[index] * z.getGreen();
                        bX += xRobertsaMatrix[index] * z.getBlue();

                        rY += yRobertsaMatrix[index] * z.getRed();
                        gY += yRobertsaMatrix[index] * z.getGreen();
                        bY += yRobertsaMatrix[index] * z.getBlue();

                        ++index;
                    }
                }

                double r = Math.abs(rX) + Math.abs(rY);
                double g = Math.abs(gX) + Math.abs(gY);
                double b = Math.abs(bX) + Math.abs(bY);

                if (r < 0) r = 0;
                if (r > 1F) r = 1F;

                if (g < 0) g = 0;
                if (g > 1F) g = 1F;

                if (b < 0) b = 0;
                if (b > 1F) b = 1F;

                out.getPixelWriter().setColor(x, y, new Color(r, g, b, 1.0F));
            }
        }
        return out;
    }

}

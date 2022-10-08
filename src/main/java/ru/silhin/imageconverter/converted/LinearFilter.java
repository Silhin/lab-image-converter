package ru.silhin.imageconverter.converted;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LinearFilter implements IConvertingProcess {
    public static int LINEAR_RADIUS = 0;

    @Override
    public Image converting(Image originalImage) {
        WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());

        for(int y = LINEAR_RADIUS; y < originalImage.getHeight() - LINEAR_RADIUS; ++y) {
            for(int x = LINEAR_RADIUS; x < originalImage.getWidth() - LINEAR_RADIUS; ++x) {

                int count = 0;

                double r = 0F;
                double g = 0F;
                double b = 0F;

                for (int i = y - LINEAR_RADIUS; i <= y + LINEAR_RADIUS; ++i) {
                    for (int j = x - LINEAR_RADIUS; j <= x + LINEAR_RADIUS; ++j) {
                        Color z = originalImage.getPixelReader().getColor(j, i);

                        r += z.getRed();
                        g += z.getGreen();
                        b += z.getBlue();

                        ++count;
                    }
                }

                r /= count;
                g /= count;
                b /= count;

                if (r < 0) r = 0;
                if (r > 1F) r = 1F;

                if (g < 0) g = 0;
                if (g > 1F) g = 1F;

                if (b < 0) b = 0;
                if (b > 1F) b = 1F;

                Color color = new Color(r, g, b, 1.0F);
                out.getPixelWriter().setColor(x, y, color);
            }
        }

        return out;
    }

}

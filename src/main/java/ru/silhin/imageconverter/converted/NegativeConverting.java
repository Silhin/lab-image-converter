package ru.silhin.imageconverter.converted;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class NegativeConverting implements IConvertingProcess {

    @Override
    public Image converting(Image originalImage) {
        int width = (int) originalImage.getWidth();
        int height = (int) originalImage.getHeight();

        WritableImage out = new WritableImage(width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color originalColor = originalImage.getPixelReader().getColor(x, y);

                double r = 1F - originalColor.getRed();
                double g = 1F - originalColor.getGreen();
                double b = 1F - originalColor.getBlue();

                Color convertedColor = new Color(r, g, b, originalColor.getOpacity());

                out.getPixelWriter().setColor(x, y, convertedColor);
            }
        }

        return out;
    }
}

package ru.silhin.imageconverter.converted;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class LinearFilter implements IConvertingProcess {
    @Override
    public Image converting(Image originalImage) {
        WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());

        for(int y = 0; y < originalImage.getHeight(); ++y) {
            for(int x = 0; x < originalImage.getWidth(); ++x) {
                Color originalColor = originalImage.getPixelReader().getColor(x, y);
                Color color = Color.WHITE;



                out.getPixelWriter().setColor(x, y, color);
            }
        }

        return out;
    }

}

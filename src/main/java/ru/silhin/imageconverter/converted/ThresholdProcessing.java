package ru.silhin.imageconverter.converted;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.MainApplication;

public class ThresholdProcessing implements IConvertingProcess {
    @Override
    public Image converting(Image originalImage) {
        WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());

        for(int y = 0; y < originalImage.getHeight(); ++y) {
            for(int x = 0; x < originalImage.getWidth(); ++x) {
                Color originalColor = originalImage.getPixelReader().getColor(x, y);
                Color color = Color.BLACK;

                if(MainApplication.Rl <= originalColor.getRed() && originalColor.getRed() <= MainApplication.Ru) {
                    color = originalColor;
                }

                if(MainApplication.Rl <= originalColor.getBlue() && originalColor.getBlue() <= MainApplication.Ru) {
                    color = originalColor;
                }

                if(MainApplication.Rl <= originalColor.getGreen() && originalColor.getGreen() <= MainApplication.Ru) {
                    color = originalColor;
                }

                out.getPixelWriter().setColor(x, y, color);
            }
        }


        return out;
    }
}

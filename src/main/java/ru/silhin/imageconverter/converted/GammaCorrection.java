package ru.silhin.imageconverter.converted;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static ru.silhin.imageconverter.MainApplication.GAMMA_C;
import static ru.silhin.imageconverter.MainApplication.GAMMA_Y;

public class GammaCorrection implements IConvertingProcess {
    private final System.Logger logger = System.getLogger(this.getClass().getName());

    @Override
    public Image converting(Image originalImage) {
        logger.log(System.Logger.Level.INFO, "gammaC: " + GAMMA_C);
        logger.log(System.Logger.Level.INFO, "gammaY: " + GAMMA_Y);

        WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());

        for (int y = 0; y < (int) originalImage.getHeight(); ++y) {
            for (int x = 0; x < originalImage.getWidth(); ++x) {
                Color originalColor = originalImage.getPixelReader().getColor(x, y);

                double r = GAMMA_C * Math.pow(originalColor.getRed(), GAMMA_Y) / Math.pow(1, GAMMA_Y - 1);
                double g = GAMMA_C * Math.pow(originalColor.getGreen(), GAMMA_Y) / Math.pow(1, GAMMA_Y - 1);
                double b = GAMMA_C * Math.pow(originalColor.getBlue(), GAMMA_Y) / Math.pow(1, GAMMA_Y - 1);

                Color convertColor = new Color(r, g, b, originalColor.getOpacity());

                out.getPixelWriter().setColor(x, y, convertColor);
            }
        }

        return out;
    }

}

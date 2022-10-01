package ru.silhin.imageconverter.converted;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Laplasian implements IConvertingProcess {

    @Override
    public Image converting(Image originalImage) {
        WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());

        for(int y = 1; y < originalImage.getHeight() - 1; ++y) {
            for(int x = 1; x < originalImage.getWidth() - 1; ++x) {
                Color originalColor = originalImage.getPixelReader().getColor(x, y);
                double dR = 0;
                double dG = 0;
                double dB = 0;

                for(int i = y - 1; i <= y + 1; ++i) {
                    for (int j = x - 1; j <= x + 1; ++j) {
                        Color z = originalImage.getPixelReader().getColor(j, i);

                        int k = 1;
                        if(i == y && j == x) {
                            k = -8;
                        }

                        dR += k * z.getRed();
                        dG += k * z.getGreen();
                        dB += k * z.getBlue();
                    }
                }

                double r = originalColor.getRed() - dR;
                double g = originalColor.getGreen() - dG;
                double b = originalColor.getBlue() - dB;

                if(r < 0) r = 0;
                if(r > 1F) r = 1F;

                if(g < 0) g = 0;
                if(g > 1F) g = 1F;

                if(b < 0) b = 0;
                if(b > 1F) b = 1F;

                out.getPixelWriter().setColor(x, y, new Color(r, g, b, 1.0F));
            }
        }

        return out;
    }

}

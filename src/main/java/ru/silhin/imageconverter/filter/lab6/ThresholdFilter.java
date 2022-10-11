package ru.silhin.imageconverter.filter.lab6;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.filter.IFilter;
import ru.silhin.imageconverter.util.FilterHelper;

public class ThresholdFilter implements IFilter {
    public static double Rl = 0;
    public static double Ru = 1;

    private boolean inRange(double value) {
        return Rl <= value && value <= Ru;
    }

    @Override
    public Image useFilter(Image originalImage) {
        return FilterHelper.filterImage(originalImage, (original, out, x, y) -> {
            Color color = original.getPixelReader().getColor(x, y);

            if (inRange(color.getRed()) || inRange(color.getGreen()) || inRange(color.getBlue())) {
                out.getPixelWriter().setColor(x, y, color);
            } else {
                out.getPixelWriter().setColor(x, y, Color.BLACK);
            }

        });
    }

}

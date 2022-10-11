package ru.silhin.imageconverter.filter.lab1;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.filter.IFilter;
import ru.silhin.imageconverter.util.FilterHelper;

public class NegativeFilter implements IFilter {

    @Override
    public Image useFilter(Image originalImage) {
        return FilterHelper.filterImage(originalImage, (original, out, x, y) -> {
            Color originalColor = original.getPixelReader().getColor(x, y);

            double r = 1F - originalColor.getRed();
            double g = 1F - originalColor.getGreen();
            double b = 1F - originalColor.getBlue();

            Color color = FilterHelper.RGBValidation(r, g, b, originalColor.getOpacity());
            out.getPixelWriter().setColor(x, y, color);
        });
    }

}

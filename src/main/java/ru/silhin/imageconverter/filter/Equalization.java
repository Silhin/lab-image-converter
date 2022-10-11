package ru.silhin.imageconverter.filter;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.util.FilterHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Equalization implements IFilter {

    @Override
    public Image useFilter(Image originalImage) {
        Map<Color, Integer> pixelMap = new HashMap<>();

        FilterHelper.filterImage(originalImage, (original, out, x, y) -> {
            Color color = original.getPixelReader().getColor(x, y);

            Integer count = pixelMap.get(color);
            pixelMap.put(color, count == null ? 1 : count + 1);
        });

        List<Color> colorList = pixelMap.keySet()
                .stream()
                .sorted((o1, o2) -> {
                    double o1RGB = o1.getRed() + o1.getGreen() + o1.getBlue();
                    double o2RGB = o2.getRed() + o2.getGreen() + o2.getBlue();
                    return Double.compare(o1RGB, o2RGB);
                }).toList();

        final int height = pixelMap.values()
                .stream()
                .max(Integer::compareTo)
                .orElseThrow(NullPointerException::new);
        final int barH = Math.max(1, (int) (0.05 * height));
        final int size = height / pixelMap.keySet().size();

        WritableImage out = new WritableImage(size * pixelMap.keySet().size(), height + barH);

        int x = 0;
        for(Color color : colorList) {
            for (int i = 0; i < size; ++i) {
                int length = pixelMap.get(color);

                for (int y = 0; y < length; ++y) {
                    out.getPixelWriter().setColor(x, height - y, Color.BLACK);
                }

                for (int y = (int) out.getHeight() - 1; y > out.getHeight() - barH; --y) {
                    out.getPixelWriter().setColor(x, y, color);
                }

                ++x;
            }
        }

        return out;
    }
}

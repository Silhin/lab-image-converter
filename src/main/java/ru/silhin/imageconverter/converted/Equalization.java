package ru.silhin.imageconverter.converted;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Equalization implements IConvertingProcess {
    @Override
    public Image converting(Image originalImage) {
        Map<Color, Integer> pixelMap = new HashMap<>();
        for (int y = 0; y < originalImage.getHeight(); ++y) {
            for (int x = 0; x < originalImage.getWidth(); ++x) {
                Color color = originalImage.getPixelReader().getColor(x, y);

                if (pixelMap.containsKey(color)) {
                    pixelMap.put(color, pixelMap.get(color) + 1);
                } else {
                    pixelMap.put(color, 1);
                }
            }
        }

        List<Color> colorList = pixelMap.keySet().stream()
                .sorted((o1, o2) -> {
                    double o1RGB = o1.getRed() + o1.getGreen() + o1.getBlue();
                    double o2RGB = o2.getRed() + o2.getGreen() + o2.getBlue();
                    return Double.compare(o1RGB, o2RGB);
                }).toList();

        final int height = pixelMap.values().stream().max(Integer::compareTo).orElseThrow(NullPointerException::new);
        final int barList = (int) (0.05 * height);
        final int k = height / pixelMap.keySet().size();

        WritableImage out = new WritableImage(k * pixelMap.keySet().size(), height + barList);

        AtomicInteger x = new AtomicInteger(0);
        colorList.forEach((color) -> {
            for (int i = 0; i < k; ++i) {
                int length = pixelMap.get(color);

                for (int y = 0; y < length; ++y) {
                    out.getPixelWriter().setColor(x.get(), height - y, new Color(0, 0, 0, 1.0F));
                }

                for (int y = (int) out.getHeight() - 1; y > out.getHeight() - barList; --y) {
                    out.getPixelWriter().setColor(x.get(), y, color);
                }

                x.set(x.get() + 1);
            }
        });

        return out;
    }
}

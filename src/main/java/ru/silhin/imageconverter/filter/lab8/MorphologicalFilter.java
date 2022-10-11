package ru.silhin.imageconverter.filter.lab8;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.filter.IFilter;
import ru.silhin.imageconverter.util.FilterHelper;

import java.util.ArrayList;
import java.util.List;

public class MorphologicalFilter {
    public static int PRIMITIVE_WIDTH = 4;
    public static int PRIMITIVE_HEIGHT = 4;
    public static float[] PRIMITIVE = new float[]{
            1, 0, 0, 1,
            0, 1, 1, 0,
            0, 1, 1, 0,
            1, 0, 0, 1
    };

    public static class DilationFilter implements IFilter {
        @Override
        public Image useFilter(Image originalImage) {
            return FilterHelper.filterImage(originalImage, PRIMITIVE_HEIGHT, PRIMITIVE_WIDTH, (original, out, x, y) -> {
                final List<Integer> colorList = new ArrayList<>();

                int index = 0;
                for (int i = y - PRIMITIVE_HEIGHT; i < y; ++i) {
                    for (int j = x - PRIMITIVE_WIDTH; j < x; ++j) {
                        if (PRIMITIVE[index] > 0) {
                            colorList.add(original.getPixelReader().getArgb(j, i));
                        }
                        ++index;
                    }
                }

                colorList.sort(Integer::compareTo);
                out.getPixelWriter().setArgb(x, y, colorList.get(0));
            });
        }
    }

    public static class ErosionFilter implements IFilter {
        @Override
        public Image useFilter(Image originalImage) {
            return FilterHelper.filterImage(originalImage, PRIMITIVE_HEIGHT, PRIMITIVE_WIDTH, (original, out, x, y) -> {
                final List<Integer> colorList = new ArrayList<>();

                int index = 0;
                for (int i = y - PRIMITIVE_HEIGHT; i < y; ++i) {
                    for (int j = x - PRIMITIVE_WIDTH; j < x; ++j) {
                        if (PRIMITIVE[index] > 0) {
                            colorList.add(original.getPixelReader().getArgb(j, i));
                        }
                        ++index;
                    }
                }

                colorList.sort(Integer::compareTo);
                out.getPixelWriter().setArgb(x, y, colorList.get(colorList.size() - 1));
            });
        }
    }

    public static class BorderFilter implements IFilter {
        @Override
        public Image useFilter(Image originalImage) {
            WritableImage out = (WritableImage) new DilationFilter().useFilter(originalImage);

            FilterHelper.filterImage(originalImage, (original, out1, x, y) -> {
                Color originalColor = original.getPixelReader().getColor(x, y);
                Color outColor = out.getPixelReader().getColor(x, y);

                double r = originalColor.getRed() - outColor.getRed();
                double g = originalColor.getGreen() - outColor.getGreen();
                double b = originalColor.getBlue() - outColor.getBlue();

                Color color = FilterHelper.RGBValidation(r, g, b);
                out.getPixelWriter().setColor(x, y, color);
            });

            return out;
        }
    }

}

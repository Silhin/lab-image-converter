package ru.silhin.imageconverter.converted;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class MorphologicalFilter {
    public static int PRIMITIVE_WIDTH = 4;
    public static int PRIMITIVE_HEIGHT = 4;
    public static float[] PRIMITIVE = new float[] {
            1, 0, 0, 1,
            0, 1, 1, 0,
            0, 1, 1, 0,
            1, 0, 0, 1
    };

    public static class DilationFilter implements IConvertingProcess {
        @Override
        public Image converting(Image originalImage) {
            WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());

            for(int y = PRIMITIVE_HEIGHT; y < originalImage.getHeight() - PRIMITIVE_HEIGHT; ++y) {
                for(int x = PRIMITIVE_WIDTH; x < originalImage.getWidth() - PRIMITIVE_WIDTH; ++x) {

                    final List<Integer> colorList = new ArrayList<>();

                    int index = 0;
                    for(int i = y - PRIMITIVE_HEIGHT; i < y; ++i) {
                        for(int j = x - PRIMITIVE_WIDTH; j < x; ++j) {
                            if(PRIMITIVE[index] > 0) {
                                colorList.add(originalImage.getPixelReader().getArgb(j, i));
                            }
                            ++index;
                        }
                    }

                    colorList.sort(Integer::compareTo);
                    out.getPixelWriter().setArgb(x, y, colorList.get(0));
                }
            }

            return out;
        }
    }

    public static class ErosionFilter implements IConvertingProcess {
        @Override
        public Image converting(Image originalImage) {
            WritableImage out = new WritableImage((int) originalImage.getWidth(), (int) originalImage.getHeight());

            for(int y = PRIMITIVE_HEIGHT; y < out.getHeight() - PRIMITIVE_HEIGHT; ++y) {
                for(int x = PRIMITIVE_WIDTH; x < out.getWidth() - PRIMITIVE_WIDTH; ++x) {

                    final List<Integer> colorList = new ArrayList<>();

                    int index = 0;
                    for(int i = y - PRIMITIVE_HEIGHT; i < y; ++i) {
                        for(int j = x - PRIMITIVE_WIDTH; j < x; ++j) {
                            if(PRIMITIVE[index] > 0) {
                                colorList.add(originalImage.getPixelReader().getArgb(j, i));
                            }
                            ++index;
                        }
                    }

                    colorList.sort(Integer::compareTo);
                    out.getPixelWriter().setArgb(x, y, colorList.get(colorList.size() - 1));
                }
            }

            return out;
        }
    }

    public static class BorderFilter implements IConvertingProcess {
        @Override
        public Image converting(Image originalImage) {
            WritableImage out = (WritableImage) new DilationFilter().converting(originalImage);

            for(int y = 0; y < originalImage.getHeight(); ++y) {
                for(int x = 0; x < originalImage.getWidth(); ++x) {
                    Color originalColor = originalImage.getPixelReader().getColor(x, y);
                    Color outColor = out.getPixelReader().getColor(x, y);

                    double r = originalColor.getRed() - outColor.getRed();
                    double b = originalColor.getBlue() - outColor.getBlue();
                    double g = originalColor.getGreen() - outColor.getGreen();

                    if(r < 0) r = 0;
                    if(g < 0) g = 0;
                    if(b < 0) b = 0;

                    out.getPixelWriter().setColor(x, y, new Color(r, g, b, 1.0F));
                }
            }

            return out;
        }
    }

}

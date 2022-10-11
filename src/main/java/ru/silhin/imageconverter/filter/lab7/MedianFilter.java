package ru.silhin.imageconverter.filter.lab7;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import ru.silhin.imageconverter.filter.IFilter;
import ru.silhin.imageconverter.util.FilterHelper;

import java.util.ArrayList;
import java.util.List;

public class MedianFilter implements IFilter {
    public static int MEDIAN_RADIUS = 1;

    private double getMedian(List<Double> list) {
        list.sort(Double::compareTo);
        if(list.size() % 2 == 1) {
            return list.get((list.size() / 2) + 1);
        }
        return (list.get(list.size() / 2) + list.get(1 + list.size() / 2)) / 2;
    }

    @Override
    public Image useFilter(Image originalImage) {
        return FilterHelper.filterImage(originalImage, MEDIAN_RADIUS, MEDIAN_RADIUS, (original, out, x, y) -> {
            List<Double> rList = new ArrayList<>();
            List<Double> gList = new ArrayList<>();
            List<Double> bList = new ArrayList<>();

            for (int i = y - MEDIAN_RADIUS; i <= y + MEDIAN_RADIUS; ++i) {
                for (int j = x - MEDIAN_RADIUS; j <= x + MEDIAN_RADIUS; ++j) {
                    Color z = original.getPixelReader().getColor(j, i);

                    rList.add(z.getRed());
                    gList.add(z.getGreen());
                    bList.add(z.getBlue());
                }
            }

            double r = this.getMedian(rList);
            double g = this.getMedian(gList);
            double b = this.getMedian(bList);

            Color color = new Color(r, g, b, 1.0F);
            out.getPixelWriter().setColor(x, y, color);
        });
    }

}

package ru.silhin.imageconverter.util;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

@FunctionalInterface
public interface Filterable {

    void filter(Image original, WritableImage out, int x, int y);
}

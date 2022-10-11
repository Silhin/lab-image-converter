package ru.silhin.imageconverter.filter;

import javafx.scene.image.Image;

public interface IFilter {
    Image useFilter(Image originalImage);
}

package com.kodilla;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoardCell {
    private int height;
    private int width;


    public BoardCell(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Rectangle createCell() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(this.height);
        rectangle.setWidth(this.width);
        rectangle.setFill(Color.GRAY);
        rectangle.setStroke(Color.GREEN);

        return rectangle;
    }




}



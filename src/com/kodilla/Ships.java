package com.kodilla;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class Ships {
    Image battleshipImage = new Image("file:resources/battleship.png");
    ImagePattern battleship = new ImagePattern(battleshipImage);

    Image aircrafcImage = new Image("file:resources/aircraft.png");
    ImagePattern aircraft = new ImagePattern(aircrafcImage);

    Image destroyerImage = new Image(("file:resources/destroyer.png"));
    ImagePattern destroyer = new ImagePattern(destroyerImage);

    Image smallShipImage = new Image("file:resources/small_ship.png");
    ImagePattern smallShip = new ImagePattern(smallShipImage);
    GridPane shipsBoard = new GridPane();



    public GridPane createShips() {

        shipsBoard.setVgap(25);

        Rectangle battleship = createBattleship();
        shipsBoard.add(battleship, 0, 0);
        Rectangle aircraft1 = createAircraft();
        shipsBoard.add(aircraft1, 0, 1);
        Rectangle aircraft2 = createAircraft();
        shipsBoard.add(aircraft2, 0, 2);
        Rectangle submarine1 = createDestroyer();
        shipsBoard.add(submarine1, 0,3);
        Rectangle submarine2 = createDestroyer();
        shipsBoard.add(submarine2,0,4);
        Rectangle smalShip1 = createSmallShip();
        shipsBoard.add(smalShip1,0,5);
        Rectangle smallShip2 = createSmallShip();
        shipsBoard.add(smallShip2,0,6);

        return shipsBoard;
    }

    public Rectangle getShip(GridPane shipsBoard, int col, int row) {
        for (Node node : shipsBoard.getChildren()) {
            if (node == null) {
                return null;
            }
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                Rectangle rectangle = (Rectangle) node;
                return rectangle;
            }

        }
        return null;
    }


    Rectangle createBattleship() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(125);
        rectangle.setHeight(25);
        rectangle.setFill(battleship);
        return rectangle;

    }

    Rectangle createAircraft() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(100);
        rectangle.setHeight(25);
        rectangle.setFill(aircraft);
        return rectangle;
    }

    Rectangle createDestroyer() {
        Rectangle rectangle= new Rectangle();
        rectangle.setWidth(75);
        rectangle.setHeight(25);
        rectangle.setFill(destroyer);
    return rectangle;
    }

    Rectangle createSmallShip() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(50);
        rectangle.setHeight(25);
        rectangle.setFill(smallShip);
        return rectangle;
    }
}


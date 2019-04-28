package com.kodilla;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;


public class AircraftListener {
    public static ArrayList<Integer> aircraftInstall(GridPane shipsBoard, GridPane userBoard, int number,
                                                     ArrayList<Integer> occupiedCells, ArrayList<Integer> shipsList) {
        Node shipToMove = shipsBoard.getChildren().get(number);
        ArrayList<Integer> aircraftCells = new ArrayList<>();
        ArrayList<Integer> aircraftList = new ArrayList<>();
        InstallListener installListener = new InstallListener();


        Color aircraft1Color = Color.rgb(255, 255, 1);
        Color aircraft2Color = Color.rgb(255, 255, 2);

        shipsBoard.setOnMouseClicked(event -> {
            if (number == 1) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    Rotate rotate = new Rotate();
                    rotate.setPivotX(200 / 2);
                    rotate.setPivotY(25 / 2);
                    if (shipToMove.getRotate() == 0) {
                        shipToMove.setRotate(90);
                        shipToMove.setTranslateX(event.getSceneX() - 100);
                        shipToMove.setTranslateY(event.getSceneY() - 75);

                    } else {
                        shipToMove.setRotate(0);
                        shipToMove.setTranslateX(event.getSceneX() - 50);
                        shipToMove.setTranslateY(event.getSceneY() - 125);
                    }

                    installListener.resetColor(userBoard, aircraft1Color, occupiedCells, aircraftCells);
                }
            } else if (number == 2) {

                if (event.getButton() == MouseButton.SECONDARY) {
                    Rotate rotate = new Rotate();
                    rotate.setPivotX(200 / 2);
                    rotate.setPivotY(25 / 2);
                    if (shipToMove.getRotate() == 0) {
                        shipToMove.setRotate(90);
                        shipToMove.setTranslateX(event.getSceneX() - 90);
                        shipToMove.setTranslateY(event.getSceneY() - 125);

                    } else {
                        shipToMove.setRotate(0);
                        shipToMove.setTranslateX(event.getSceneX() - 50);
                        shipToMove.setTranslateY(event.getSceneY() - 150);
                    }
                    installListener.resetColor(userBoard, aircraft2Color, occupiedCells, aircraftCells);
                }
            }
        });

        shipToMove.setOnMouseDragged(event -> {

            if (shipToMove.getRotate() == 0) {
                shipToMove.setTranslateX(event.getSceneX() - shipToMove.getLayoutX() - 65);
                shipToMove.setTranslateY(event.getSceneY() - shipToMove.getLayoutY() - 65);
            } else if (shipToMove.getRotate() == 90) {
                shipToMove.setTranslateX(event.getSceneX() - shipToMove.getLayoutX() - 80);
                shipToMove.setTranslateY(event.getSceneY() - shipToMove.getLayoutY() - 25);
            }
            if (number == 1) {

                installListener.resetColor(userBoard, aircraft1Color, occupiedCells, aircraftCells);

            } else if (number == 2) {

                installListener.resetColor(userBoard, aircraft2Color, occupiedCells, aircraftCells);
            }
            shipsList.removeAll(aircraftList);
            aircraftCells.clear();
            aircraftList.clear();


            shipToMove.setCursor(Cursor.HAND);

        });
        shipToMove.setOnMouseReleased(event -> {
            double localX = event.getSceneX() - 230;
            double localY = event.getSceneY() - 60;
            int col = (int) localX / 25;
            int row = (int) localY / 25;
            int index1 = row + (col * 20);
            Node node = userBoard.getChildren().get(index1);

            if (shipToMove.getRotate() == 0) {

                if (occupiedCells.contains(index1) || occupiedCells.contains(index1 + 20) || occupiedCells.contains(index1 + 40)
                        || occupiedCells.contains(index1 + 60) || col > 15) {
                    shipToMove.setTranslateY(500);
                } else {
                    if (number == 1) {
                        shipToMove.setTranslateX(node.getLayoutX() + 170);
                        shipToMove.setTranslateY(node.getLayoutY() - 50);


                    } else if (number == 2) {
                        shipToMove.setTranslateX(node.getLayoutX() + 170);
                        shipToMove.setTranslateY(node.getLayoutY() - 100);

                    }
                    installListener.setShipZeroRotation(index1, aircraftList, aircraftCells, col, row, 4);
                }

            } else if (shipToMove.getRotate() == 90) {


                if (occupiedCells.contains(index1) || occupiedCells.contains(index1 + 1) || occupiedCells.contains(index1 + 2)
                        || occupiedCells.contains(index1 + 3) || row > 15) {
                    shipToMove.setTranslateY(400);
                } else {
                    if (number == 1) {
                        shipToMove.setTranslateX(node.getLayoutX() + 140);
                        shipToMove.setTranslateY(node.getLayoutY() - 10);


                    } else if (number == 2) {
                        shipToMove.setTranslateX(node.getLayoutX() + 140);
                        shipToMove.setTranslateY(node.getLayoutY() - 60);



                    }
                    installListener.setRotatedShip(index1, aircraftList, aircraftCells, col, row, 4);
                }
            }


            for (Integer index : aircraftCells) {
                Rectangle rectangle = (Rectangle) userBoard.getChildren().get(index);
                if (number == 1) {
                    rectangle.setFill(aircraft1Color);

                } else if (number == 2) {

                    rectangle.setFill(aircraft2Color);
                }

            }

            occupiedCells.addAll(aircraftCells);
            shipsList.addAll(aircraftList);


        });


        return aircraftCells;

    }
}

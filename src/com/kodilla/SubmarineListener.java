package com.kodilla;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class SubmarineListener {
    public static ArrayList<Integer> submarineInstall(GridPane shipsBoard, GridPane userBoard, int number,
                                                      ArrayList<Integer> occupiedCells, ArrayList<Integer> shipsList) {
        Node shipToMove = shipsBoard.getChildren().get(number);
        ArrayList<Integer> submarineCells = new ArrayList<>();
        ArrayList<Integer> submarineList = new ArrayList<>();
        InstallListener installListener = new InstallListener();

        Color submarine1Color = Color.rgb(255, 255, 3);
        Color submarine2Color = Color.rgb(255, 255, 4);

        shipsBoard.setOnMouseClicked(event -> {


            if (number == 3) {

                if (event.getButton() == MouseButton.SECONDARY) {
                    Rotate rotate = new Rotate();
                    rotate.setPivotX(175 / 2);
                    rotate.setPivotY(25 / 2);
                    if (shipToMove.getRotate() == 0) {
                        shipToMove.setRotate(90);
                        shipToMove.setTranslateX(event.getSceneX() - 80);
                        shipToMove.setTranslateY(event.getSceneY() - 175);

                    } else {
                        shipToMove.setRotate(0);
                        shipToMove.setTranslateX(event.getSceneX() - 50);
                        shipToMove.setTranslateY(event.getSceneY() - 200);
                    }

                    installListener.resetColor(userBoard, submarine1Color, occupiedCells, submarineCells);

                }

            } else if (number == 4) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    Rotate rotate = new Rotate();
                    rotate.setPivotX(175 / 2);
                    rotate.setPivotY(25 / 2);
                    if (shipToMove.getRotate() == 0) {
                        shipToMove.setRotate(90);
                        shipToMove.setTranslateX(event.getSceneX() - 70);
                        shipToMove.setTranslateY(event.getSceneY() - 225);

                    } else {
                        shipToMove.setRotate(0);
                        shipToMove.setTranslateX(event.getSceneX() - 50);
                        shipToMove.setTranslateY(event.getSceneY() - 250);
                    }

                    installListener.resetColor(userBoard, submarine2Color, occupiedCells, submarineCells);

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

            if (number == 3) {

                installListener.resetColor(userBoard, submarine1Color, occupiedCells, submarineCells);


            } else if (number == 4) {

                installListener.resetColor(userBoard, submarine2Color, occupiedCells, submarineCells);
            }

            shipsList.removeAll(submarineList);
            submarineCells.clear();
            submarineList.clear();

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

                if (occupiedCells.contains(index1) || occupiedCells.contains(index1 + 20) || occupiedCells.contains(index1 + 40) || col > 16) {
                    shipToMove.setTranslateY(500);
                } else {
                    if (number == 3) {
                        shipToMove.setTranslateX(node.getLayoutX() + 170);
                        shipToMove.setTranslateY(node.getLayoutY() - 150);
                    } else if (number == 4) {
                        shipToMove.setTranslateX(node.getLayoutX() + 170);
                        shipToMove.setTranslateY(node.getLayoutY() - 200);
                    }
                    installListener.setShipZeroRotation(index1, submarineList, submarineCells, col, row, 3);
                }

            } else if (shipToMove.getRotate() == 90) {

                if (occupiedCells.contains(index1) || occupiedCells.contains(index1 + 1) || occupiedCells.contains(index1 + 2) || row > 16) {
                    shipToMove.setTranslateY(400);
                } else {
                    if (number == 3) {
                        shipToMove.setTranslateX(node.getLayoutX() + 155);
                        shipToMove.setTranslateY(node.getLayoutY() - 120);
                    } else if (number == 4) {
                        shipToMove.setTranslateX(node.getLayoutX() + 155);
                        shipToMove.setTranslateY(node.getLayoutY() - 170);
                    }
                    installListener.setRotatedShip(index1, submarineList, submarineCells, col, row, 3);
                }
            }


            for (Integer index : submarineCells) {
                Rectangle rectangle = (Rectangle) userBoard.getChildren().get(index);
                if (number == 3) {

                    rectangle.setFill(submarine1Color);

                } else if (number == 4) {

                    rectangle.setFill(submarine2Color);

                }

            }
            occupiedCells.addAll(submarineCells);
            shipsList.addAll(submarineList);
        });

        return submarineCells;
    }
}


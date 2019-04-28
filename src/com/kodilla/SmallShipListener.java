package com.kodilla;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class SmallShipListener {
    public static ArrayList<Integer> smallShipInstall(GridPane shipsBoard, GridPane userBoard, int number,
                                                      ArrayList<Integer> occupiedCells, ArrayList<Integer> shipsList) {
        Node shipToMove = shipsBoard.getChildren().get(number);
        ArrayList<Integer> smallShipCells = new ArrayList<>();
        ArrayList<Integer> smallShipList = new ArrayList<>();
        InstallListener installListener = new InstallListener();

        Color smallShip1Color = Color.rgb(255, 255, 5);
        Color smallShip2Color = Color.rgb(255, 255, 6);

        shipsBoard.setOnMouseClicked(event -> {
            if (number==5) {
            if (event.getButton() == MouseButton.SECONDARY) {
                Rotate rotate = new Rotate();
                rotate.setPivotX(150 / 2);
                rotate.setPivotY(25 / 2);
                if (shipToMove.getRotate() == 0) {
                    shipToMove.setRotate(90);
                    shipToMove.setTranslateX(event.getSceneX() - 60);
                    shipToMove.setTranslateY(event.getSceneY() - 290);

                } else {
                    shipToMove.setRotate(0);
                    shipToMove.setTranslateX(event.getSceneX() - 50);
                    shipToMove.setTranslateY(event.getSceneY() - 300);
                }

               installListener.resetColor(userBoard,smallShip1Color,occupiedCells,smallShipCells);
            }
        } else if (number==6) {
            if (event.getButton() == MouseButton.SECONDARY) {
                Rotate rotate = new Rotate();
                rotate.setPivotX(150 / 2);
                rotate.setPivotY(25 / 2);
                if (shipToMove.getRotate() == 0) {
                    shipToMove.setRotate(90);
                    shipToMove.setTranslateX(event.getSceneX() - 60);
                    shipToMove.setTranslateY(event.getSceneY() - 340);

                } else {
                    shipToMove.setRotate(0);
                    shipToMove.setTranslateX(event.getSceneX() - 50);
                    shipToMove.setTranslateY(event.getSceneY() - 350);
                }

                installListener.resetColor(userBoard,smallShip2Color,occupiedCells,smallShipCells);
            }
        }
    });

        shipToMove.setOnMouseDragged(event -> {
            if (shipToMove.getRotate() == 0) {
                shipToMove.setTranslateX(event.getSceneX() - shipToMove.getLayoutX() - 65);
                shipToMove.setTranslateY(event.getSceneY() - shipToMove.getLayoutY() - 65);
            } else if (shipToMove.getRotate() == 90) {
                shipToMove.setTranslateX(event.getSceneX() - shipToMove.getLayoutX() - 70);
                shipToMove.setTranslateY(event.getSceneY() - shipToMove.getLayoutY() - 50);
            }

            if (number == 5) {

                installListener.resetColor(userBoard,smallShip1Color,occupiedCells,smallShipCells);


            } else if (number == 6) {
                installListener.resetColor(userBoard, smallShip2Color, occupiedCells, smallShipCells);
            }
            shipsList.removeAll(smallShipList);
            smallShipCells.clear();
            smallShipList.clear();

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

                if (occupiedCells.contains(index1) || occupiedCells.contains(index1 + 20)|| col>17) {
                    shipToMove.setTranslateY(500);
                } else {
                    if (number == 5) {
                        shipToMove.setTranslateX(node.getLayoutX() + 170);
                        shipToMove.setTranslateY(node.getLayoutY() - 250);
                    } else if (number == 6) {
                        shipToMove.setTranslateX(node.getLayoutX() + 170);
                        shipToMove.setTranslateY(node.getLayoutY() - 300);
                    }
                    installListener.setShipZeroRotation(index1, smallShipList, smallShipCells, col, row, 2);
                }

            } else if (shipToMove.getRotate() == 90) {

                if (occupiedCells.contains(index1) || occupiedCells.contains(index1 + 1)||row>17) {
                    shipToMove.setTranslateY(400);
                } else {
                    if (number == 5) {
                        shipToMove.setTranslateX(node.getLayoutX() + 170);
                        shipToMove.setTranslateY(node.getLayoutY() - 240);
                    } else if (number == 6) {
                        shipToMove.setTranslateX(node.getLayoutX() + 170);
                        shipToMove.setTranslateY(node.getLayoutY() - 285);
                    }

                    installListener.setRotatedShip(index1,smallShipList,smallShipCells,col,row,2);
                }
            }


            for (Integer index : smallShipCells) {
                Rectangle rectangle = (Rectangle) userBoard.getChildren().get(index);
                if (number == 5) {

                    rectangle.setFill(smallShip1Color);

                } else if (number == 6) {

                    rectangle.setFill(smallShip2Color);

                }

            }
            occupiedCells.addAll(smallShipCells);
            shipsList.addAll(smallShipList);
        });

        return smallShipCells;
    }
}

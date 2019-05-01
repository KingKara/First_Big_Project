package com.kodilla;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;


public class BattleshipListener {
    public static ArrayList<Integer> battleShipInstall(GridPane shipsBoard, GridPane userBoard, ArrayList<Integer> occupiedCells,
                                                       ArrayList<Integer> shipsList, ArrayList<Integer> usersBattleship) {
        Node shipToMove = shipsBoard.getChildren().get(0);
        ArrayList<Integer> cells = new ArrayList<>();
        ArrayList<Integer> battleshipList = new ArrayList<>();
        InstallListener installListener = new InstallListener();


        Color battleshipColor = Color.rgb(255, 255, 0);

        shipsBoard.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                Rotate rotate = new Rotate();
                rotate.setPivotX(225 / 2);
                rotate.setPivotY(25 / 2);
                if (shipToMove.getRotate() == 0) {
                    shipToMove.setRotate(90);
                    shipToMove.setTranslateX(event.getSceneX() - 100);
                    shipToMove.setTranslateY(event.getSceneY());
                } else {
                    shipToMove.setRotate(0);
                    shipToMove.setTranslateX(event.getSceneX() - 50);
                    shipToMove.setTranslateY(event.getSceneY() - 75);
                }
                installListener.resetColor(userBoard, battleshipColor, occupiedCells, cells);
            }
        });

        shipToMove.setOnMouseDragged(event -> {


            if (shipToMove.getRotate() == 0) {
                shipToMove.setTranslateX(event.getSceneX() - shipToMove.getLayoutX() - 65);
                shipToMove.setTranslateY(event.getSceneY() - shipToMove.getLayoutY() - 65);

            } else if (shipToMove.getRotate() == 90) {
                shipToMove.setTranslateX(event.getSceneX() - shipToMove.getLayoutX() - 95);
                shipToMove.setTranslateY(event.getSceneY() - shipToMove.getLayoutY());
            }
            installListener.resetColor(userBoard, battleshipColor, occupiedCells, cells);
            shipsList.removeAll(battleshipList);
            cells.clear();
            battleshipList.clear();

            shipToMove.setCursor(Cursor.HAND);

        });
        shipToMove.setOnMouseReleased(event -> {
            double localX = event.getSceneX() - 230;
            double localY = event.getSceneY() - 60;
            int col = (int) localX / 25;
            int row = (int) localY / 25;
            int index1 = row + (col * 20);
            if (index1>=0 && index1<400 && event.getSceneY()<575 ) {
                Node node = userBoard.getChildren().get(index1);

                if (shipToMove.getRotate() == 0) {


                    if (occupiedCells.contains(index1) || occupiedCells.contains(index1 + 20) || occupiedCells.contains(index1 + 40)
                            || occupiedCells.contains(index1 + 60) || occupiedCells.contains(index1 + 80) || col > 14) {
                        shipToMove.setTranslateY(0);
                        shipToMove.setTranslateX(0);
                    } else {
                        shipToMove.setTranslateX(node.getLayoutX() + 175);
                        shipToMove.setTranslateY(node.getLayoutY());

                        installListener.setShipZeroRotation(index1, battleshipList, cells, col, row, 5);

                    }

                } else if (shipToMove.getRotate() == 90) {


                    if (occupiedCells.contains(index1) || occupiedCells.contains(index1 + 1) || occupiedCells.contains(index1 + 2)
                            || occupiedCells.contains(index1 + 3) || occupiedCells.contains(index1 + 4) || row > 14) {
                        shipToMove.setTranslateY(0);
                        shipToMove.setTranslateX(0);
                    } else {
                        shipToMove.setTranslateX(node.getLayoutX() + 125);
                        shipToMove.setTranslateY(node.getLayoutY() + 50);

                        installListener.setRotatedShip(index1, battleshipList, cells, col, row, 5);


                    }
                }
            } else {
                shipToMove.setTranslateY(0);
                shipToMove.setTranslateX(0);
            }
            for (Integer index : cells) {
                Rectangle rectangle = (Rectangle) userBoard.getChildren().get(index);
                rectangle.setFill(battleshipColor);
            }

            occupiedCells.addAll(cells);
            usersBattleship.addAll(cells);
            shipsList.addAll(battleshipList);
        });


        return cells;
    }
}

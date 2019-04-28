package com.kodilla;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class ComputersSmartMove {

    public static boolean checkNeighbour(int index, GridPane usersBoard, ArrayList<Integer> usersShips, ArrayList<Integer> nodesToCheck, ArrayList<Integer> computersHitTrue, ArrayList<Integer> computersHitFalse) {
        Random random = new Random();
        ArrayList<Integer> neighbours = new ArrayList<>();
        neighbours.add(index - 1);
        nodesToCheck.add(index - 1);

        neighbours.add(index + 1);
        nodesToCheck.add(index + 1);

        neighbours.add(index + 20);
        nodesToCheck.add(index + 20);

        neighbours.add(index - 20);
        nodesToCheck.add(index - 20);


        int choose = random.nextInt(4);
        int check = neighbours.get(choose);

        Rectangle rectangle = (Rectangle) usersBoard.getChildren().get(check);
        if (rectangle.getFill() == Color.GRAY) {
            if (usersShips.contains(check)) {
                rectangle.setFill(Color.RED);
                int indexToRemove2 = usersShips.indexOf(check);
                usersShips.remove(indexToRemove2);
                computersHitTrue.add(check);


                if (check == index - 1) {
                    nodesToCheck.clear();
                    nodesToCheck.add(0, index + 1);
                    nodesToCheck.add(1, index - 2);
                    nodesToCheck.add(2, index + 2);
                    nodesToCheck.add(3, index - 3);
                    nodesToCheck.add(4, index + 3);
                    nodesToCheck.add(5, index - 4);

                } else if (check == index + 1) {
                    nodesToCheck.clear();
                    nodesToCheck.add(0, index - 1);
                    nodesToCheck.add(1, index + 2);
                    nodesToCheck.add(2, index - 2);
                    nodesToCheck.add(3, index + 3);
                    nodesToCheck.add(4, index - 3);
                    nodesToCheck.add(5, index + 4);
                } else if (check == index + 20) {
                    nodesToCheck.clear();
                    nodesToCheck.add(0, index - 20);
                    nodesToCheck.add(1, index + 40);
                    nodesToCheck.add(2, index - 40);
                    nodesToCheck.add(3, index + 60);
                    nodesToCheck.add(4, index - 60);
                    nodesToCheck.add(5, index + 80);
                } else if (check == index - 20) {
                    nodesToCheck.clear();
                    nodesToCheck.add(0, index + 20);
                    nodesToCheck.add(1, index - 40);
                    nodesToCheck.add(2, index + 40);
                    nodesToCheck.add(3, index - 60);
                    nodesToCheck.add(4, index + 60);
                    nodesToCheck.add(5, index - 80);
                }

            } else {
                computersHitFalse.add(check);
                rectangle.setFill(Color.AQUAMARINE);
                nodesToCheck.remove(choose);

                return true;

            }
        }
        return false;
    }

    public static boolean stageTwo(int size, ArrayList<Integer> nodesToCheck, GridPane userBoard, ArrayList<Integer> shipsList, int hitNode, int originalNode
    , ArrayList<Integer> computersHitTrue, ArrayList<Integer> computersHitFalse) {
        Random random = new Random();
        int hit = random.nextInt(size);
        int usersBoardIndex = nodesToCheck.get(hit);
        if (usersBoardIndex >= 0 && usersBoardIndex < 399) {
            Rectangle rectangle2 = (Rectangle) userBoard.getChildren().get(usersBoardIndex);
            if (shipsList.contains(usersBoardIndex)) {
                hitNode = usersBoardIndex;
                rectangle2.setFill(Color.RED);
                int indexToRemove2 = shipsList.indexOf(usersBoardIndex);
                shipsList.remove(indexToRemove2);
                computersHitTrue.add(usersBoardIndex);
                nodesToCheck.clear();
                int sub = hitNode - originalNode;
                if ((sub == 1) || (sub == -1)) {

                    nodesToCheck.add(0, originalNode + 1);
                    nodesToCheck.add(1, originalNode - 1);
                    nodesToCheck.add(2, originalNode - 2);
                    nodesToCheck.add(3, originalNode + 2);
                    nodesToCheck.add(4, originalNode - 3);
                    nodesToCheck.add(5, originalNode + 3);
                    nodesToCheck.add(6, originalNode - 4);
                    nodesToCheck.add(7, originalNode + 4);
                } else if (sub == 20 || sub == -20) {
                    nodesToCheck.add(0, originalNode - 20);
                    nodesToCheck.add(1, originalNode + 20);
                    nodesToCheck.add(2, originalNode + 40);
                    nodesToCheck.add(3, originalNode - 40);
                    nodesToCheck.add(4, originalNode + 60);
                    nodesToCheck.add(5, originalNode - 60);
                    nodesToCheck.add(6, originalNode + 80);
                    nodesToCheck.add(7, originalNode - 80);
                }
                if (nodesToCheck.contains(hitNode)) {
                    int indexOfNode = nodesToCheck.indexOf(hitNode);
                    nodesToCheck.remove(indexOfNode);
                }

            } else {
                computersHitFalse.add(usersBoardIndex);
                rectangle2.setFill(Color.AQUAMARINE);
                nodesToCheck.remove(hit);
                return true;
            }
        } else {
            nodesToCheck.remove(hit);

        }
        return false;
    }
}

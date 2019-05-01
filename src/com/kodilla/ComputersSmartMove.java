package com.kodilla;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class ComputersSmartMove {

    public static boolean checkNeighbour(int index, GridPane usersBoard, ArrayList<Integer> usersShips, ArrayList<Integer> nodesToCheck, ArrayList<Integer> computersHitTrue, ArrayList<Integer> computersHitFalse,
                                         ArrayList<Integer> usersBattleship, ArrayList<Integer> usersAircraft1, ArrayList<Integer> usersAircraft2,
                                         ArrayList<Integer> usersSubmarine1, ArrayList<Integer> usersSubmarine2, ArrayList<Integer> usersSmallShip1,
                                         ArrayList<Integer> usersSmallShip2, int checkFirstShip, int checkSecondShip, int checkThirdShip,
                                         int checkFourthShip, int checkFifthShip, int checkSixthShip, int checkSeventhShip ) {
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
                int indexToRemove = usersShips.indexOf(check);
                usersShips.remove(indexToRemove);
                computersHitTrue.add(check);

                if (usersBattleship.contains(check)) {
                    indexToRemove = usersBattleship.indexOf(check);
                    usersBattleship.remove(indexToRemove);
                } else if (usersAircraft1.contains(check)) {
                    indexToRemove = usersAircraft1.indexOf(check);
                    usersAircraft1.remove(indexToRemove);
                } else if (usersAircraft2.contains(check)) {
                    indexToRemove = usersAircraft2.indexOf(check);
                    usersAircraft2.remove(indexToRemove);
                } else if (usersSubmarine1.contains(check)) {
                    indexToRemove = usersSubmarine1.indexOf(check);
                    usersSubmarine1.remove(indexToRemove);
                } else if (usersSubmarine2.contains(check)) {
                    indexToRemove = usersSubmarine2.indexOf(check);
                    usersSubmarine2.remove(indexToRemove);
                } else if (usersSmallShip1.contains(check)) {
                    indexToRemove = usersSmallShip1.indexOf(check);
                    usersSmallShip1.remove(indexToRemove);
                } else if (usersSmallShip2.contains(check)) {
                    indexToRemove = usersSmallShip2.indexOf(check);
                    usersSmallShip2.remove(indexToRemove);
                }

                if (usersBattleship.size() == checkFirstShip) {
                    for (Integer cell: usersBattleship) {
                        if (cell>=0&& cell<400) {
                            Rectangle rectangle1 = (Rectangle) usersBoard.getChildren().get(cell);
                            rectangle1.setFill(Color.AQUAMARINE);
                        }
                    }
                    computersHitFalse.addAll(usersBattleship);
                }
                if (usersAircraft1.size() == checkSecondShip) {
                    for (Integer cell: usersAircraft1) {
                        if (cell>=0&& cell<400) {
                            Rectangle rectangle1 = (Rectangle) usersBoard.getChildren().get(cell);
                            rectangle1.setFill(Color.AQUAMARINE);
                        }
                    }

                    computersHitFalse.addAll(usersAircraft1);
                }

                if (usersAircraft2.size() == checkThirdShip) {
                    for (Integer cell: usersAircraft2) {
                        if (cell>=0&& cell<400) {
                            Rectangle rectangle1 = (Rectangle) usersBoard.getChildren().get(cell);
                            rectangle1.setFill(Color.AQUAMARINE);
                        }
                    }
                    computersHitFalse.addAll(usersAircraft2);
                }
                if (usersSubmarine1.size() == checkFourthShip) {
                    for (Integer cell: usersSubmarine1) {
                        if (cell >= 0 && cell < 400) {
                            Rectangle rectangle1 = (Rectangle) usersBoard.getChildren().get(cell);
                            rectangle1.setFill(Color.AQUAMARINE);
                        }
                    }
                    computersHitFalse.addAll(usersSubmarine1);
                }
                if (usersSubmarine2.size() == checkFifthShip) {
                    for (Integer cell: usersSubmarine2) {
                        if (cell>=0&& cell<400) {
                            Rectangle rectangle1 = (Rectangle) usersBoard.getChildren().get(cell);
                            rectangle1.setFill(Color.AQUAMARINE);
                        }
                    }

                    computersHitFalse.addAll(usersSubmarine2);
                }
                if (usersSmallShip1.size() == checkSixthShip) {
                    for (Integer cell: usersSmallShip1) {
                        if (cell>=0&& cell<400) {
                            Rectangle rectangle1 = (Rectangle) usersBoard.getChildren().get(cell);
                            rectangle1.setFill(Color.AQUAMARINE);
                        }
                    }

                    computersHitFalse.addAll(usersSmallShip1);
                }
                if (usersSmallShip2.size() == checkSeventhShip) {
                    for (Integer cell: usersSmallShip2) {
                        if (cell>=0&& cell<400) {
                            Rectangle rectangle1 = (Rectangle) usersBoard.getChildren().get(cell);
                            rectangle1.setFill(Color.AQUAMARINE);
                        }
                    }

                    computersHitFalse.addAll(usersSmallShip2);
                }


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

    public static boolean stageTwo(int size, ArrayList<Integer> nodesToCheck, GridPane userBoard, ArrayList<Integer> shipsList, int originalNode
    , ArrayList<Integer> computersHitTrue, ArrayList<Integer> computersHitFalse, ArrayList<Integer> usersBattleship, ArrayList<Integer> usersAircraft1,
                                   ArrayList<Integer> usersAircraft2, ArrayList<Integer> usersSubmarine1, ArrayList<Integer> usersSubmarine2,
                                   ArrayList<Integer> usersSmallShip1, ArrayList<Integer> usersSmallShip2, int checkFirstShip, int checkSecondShip, int checkThirdShip,
                                   int checkFourthShip, int checkFifthShip, int checkSixthShip, int checkSeventhShip) {
        Random random = new Random();
        int hit = random.nextInt(size);
        int usersBoardIndex = nodesToCheck.get(hit);
        int hitNode= usersBoardIndex;


        if (usersBoardIndex >= 0 && usersBoardIndex < 399) {
            Rectangle rectangle2 = (Rectangle) userBoard.getChildren().get(usersBoardIndex);
            if (shipsList.contains(usersBoardIndex)) {
                if (rectangle2.getFill() == Color.GRAY) {
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

                    if (usersBattleship.contains(usersBoardIndex)) {
                        indexToRemove2 = usersBattleship.indexOf(usersBoardIndex);
                        usersBattleship.remove(indexToRemove2);
                    } else if (usersAircraft1.contains(usersBoardIndex)) {
                        indexToRemove2 = usersAircraft1.indexOf(usersBoardIndex);
                        usersAircraft1.remove(indexToRemove2);
                    } else if (usersAircraft2.contains(usersBoardIndex)) {
                        indexToRemove2 = usersAircraft2.indexOf(usersBoardIndex);
                        usersAircraft2.remove(indexToRemove2);
                    } else if (usersSubmarine1.contains(usersBoardIndex)) {
                        indexToRemove2 = usersSubmarine1.indexOf(usersBoardIndex);
                        usersSubmarine1.remove(indexToRemove2);
                    } else if (usersSubmarine2.contains(usersBoardIndex)) {
                        indexToRemove2 = usersSubmarine2.indexOf(usersBoardIndex);
                        usersSubmarine2.remove(indexToRemove2);
                    } else if (usersSmallShip1.contains(usersBoardIndex)) {
                        indexToRemove2 = usersSmallShip1.indexOf(usersBoardIndex);
                        usersSmallShip1.remove(indexToRemove2);
                    } else if (usersSmallShip2.contains(usersBoardIndex)) {
                        indexToRemove2 = usersSmallShip2.indexOf(usersBoardIndex);
                        usersSmallShip2.remove(indexToRemove2);
                    }

                    if (usersBattleship.size() == checkFirstShip) {
                        for (Integer cell : usersBattleship) {
                            if (cell >= 0 && cell < 400) {
                                Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                rectangle1.setFill(Color.AQUAMARINE);
                            }
                        }
                        computersHitFalse.addAll(usersBattleship);
                    }
                    if (usersAircraft1.size() == checkSecondShip) {
                        for (Integer cell : usersAircraft1) {
                            if (cell >= 0 && cell < 400) {
                                Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                rectangle1.setFill(Color.AQUAMARINE);
                            }
                        }

                        computersHitFalse.addAll(usersAircraft1);
                    }

                    if (usersAircraft2.size() == checkThirdShip) {
                        for (Integer cell : usersAircraft2) {
                            if (cell >= 0 && cell < 400) {
                                Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                rectangle1.setFill(Color.AQUAMARINE);
                            }
                        }

                        computersHitFalse.addAll(usersAircraft2);
                    }
                    if (usersSubmarine1.size() == checkFourthShip) {
                        for (Integer cell : usersSubmarine1) {
                            if (cell >= 0 && cell < 400) {
                                Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                rectangle1.setFill(Color.AQUAMARINE);
                            }
                        }

                        computersHitFalse.addAll(usersSubmarine1);
                    }
                    if (usersSubmarine2.size() == checkFifthShip) {
                        for (Integer cell : usersSubmarine2) {
                            if (cell >= 0 && cell < 400) {
                                Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                rectangle1.setFill(Color.AQUAMARINE);
                            }
                        }

                        computersHitFalse.addAll(usersSubmarine2);
                    }
                    if (usersSmallShip1.size() == checkSixthShip) {
                        for (Integer cell : usersSmallShip1) {
                            if (cell >= 0 && cell < 400) {
                                Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                rectangle1.setFill(Color.AQUAMARINE);
                            }
                        }

                        computersHitFalse.addAll(usersSmallShip1);
                    }
                    if (usersSmallShip2.size() == checkSeventhShip) {
                        for (Integer cell : usersSmallShip2) {
                            if (cell >= 0 && cell < 400) {
                                Rectangle rectangle1 = (Rectangle) userBoard.getChildren().get(cell);
                                rectangle1.setFill(Color.AQUAMARINE);
                            }
                        }

                        computersHitFalse.addAll(usersSmallShip2);
                    }


                }
            }else {
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

package com.kodilla;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class SaveGame  {
    HashMap<String, ArrayList<Integer>> boardSave = new HashMap<>();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveGame)) return false;

        SaveGame saveGame = (SaveGame) o;

        return boardSave != null ? boardSave.equals(saveGame.boardSave) : saveGame.boardSave == null;

    }

    @Override
    public int hashCode() {
        return boardSave != null ? boardSave.hashCode() : 0;
    }


    public void saveBoards(ArrayList<Integer> usersHitFalseList, ArrayList<Integer> computerHitFalseList, ArrayList<Integer> usersHitTrueList, ArrayList<Integer> computerHitTrueList,
                           ArrayList<Integer> shipsList, ArrayList<Integer> computerShips) {
        boardSave.clear();
        String key1 = "usersHitFalseList";
        String key2 = "computerHitFalseList";
        String key3 = "usersHitTrueList";
        String key4 = "computerHitTrueList";
        String key5 = "shipsList";
        String key6 = "computerShips";

        boardSave.put(key1, usersHitFalseList);
        boardSave.put(key2, computerHitFalseList);
        boardSave.put(key3, usersHitTrueList);
        boardSave.put(key4, computerHitTrueList);
        boardSave.put(key5, shipsList);
        boardSave.put(key6, computerShips);

        try {

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
            out.writeObject(boardSave);
            out.close();
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public void saveTurn (boolean usersMove) {

        try {
            BufferedWriter usersWriter = new BufferedWriter(new FileWriter("C:/Users/Kinga/Documents/Development/Projects/BattleShips/moveStatus.txt"));
            if (usersMove) {
                usersWriter.append("true");
            } else {
                usersWriter.append("false");
            }
            usersWriter.newLine();
        } catch (IOException e) {
            System.out.println("wystąpił błąd: " + e);
        }
    }

}

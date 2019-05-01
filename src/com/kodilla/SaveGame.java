package com.kodilla;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
                           ArrayList<Integer> shipsList, ArrayList<Integer> computerShips,ArrayList<Integer> usersBattleship, ArrayList<Integer> usersAircraft1, ArrayList<Integer> usersAircraft2,
                           ArrayList<Integer> usersSubmarine1, ArrayList<Integer> usersSubmarine2, ArrayList<Integer> usersSmallShip1, ArrayList<Integer> usersSmallShip2,
                           ArrayList<Integer> computersBattleship, ArrayList<Integer> computersAircraft1, ArrayList<Integer> computersAircraft2, ArrayList<Integer> computersSubmarine1,
                           ArrayList<Integer> computersSubmarine2, ArrayList<Integer> computersSmallShip1, ArrayList<Integer> computersSmallShip2) {
        boardSave.clear();
        String key1 = "usersHitFalseList";
        String key2 = "computerHitFalseList";
        String key3 = "usersHitTrueList";
        String key4 = "computerHitTrueList";
        String key5 = "shipsList";
        String key6 = "computerShips";
        String key7 = "usersBattleship";
        String key8 = "usersAircraft1";
        String key9 = "usersAircraft2";
        String key10 = "usersSubmarine1";
        String key11 = "usersSubmarine2";
        String key12 = "usersSmallShip1";
        String key13 = "usersSmallShip2";
        String key14 = "computersBattleship";
        String key15 = "computerAircraft1";
        String key16 = "computerAircraft2";
        String key17 = "computerSubmarine1";
        String key18 = "computerSubmarine2";
        String key19 = "computerSmallShip1";
        String key20 = "computerSmallShip2";

        boardSave.put(key1, usersHitFalseList);
        boardSave.put(key2, computerHitFalseList);
        boardSave.put(key3, usersHitTrueList);
        boardSave.put(key4, computerHitTrueList);
        boardSave.put(key5, shipsList);
        boardSave.put(key6, computerShips);
        boardSave.put(key7, usersBattleship);
        boardSave.put(key8, usersAircraft1);
        boardSave.put(key9, usersAircraft2);
        boardSave.put(key10, usersSubmarine1);
        boardSave.put(key11,usersSubmarine2);
        boardSave.put(key12, usersSmallShip1);
        boardSave.put(key13, usersSmallShip2);
        boardSave.put(key14, computersBattleship);
        boardSave.put(key15, computersAircraft1);
        boardSave.put(key16, computersAircraft2);
        boardSave.put(key17, computersSubmarine1);
        boardSave.put(key18, computersSubmarine2);
        boardSave.put(key19, computersSmallShip1);
        boardSave.put(key20, computersSmallShip2);

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
            BufferedWriter computersWriter = new BufferedWriter(new FileWriter("turn.txt"));
            if (usersMove) {
                computersWriter.append("true");
            } else {
               computersWriter.append("false");
            }
        computersWriter.close();
        } catch (IOException e) {
            System.out.println("wystąpił błąd: " + e);
        }
    }
    public void saveSizez (int checkFirstShip,int checkSecondShip, int checkThirdShip, int checkFourthShip, int checkFifthShip, int checkSixthShip, int checkSeventhShip,
                           int checkFirstUsersShip, int checkSecondUsersShip, int checkThirdUsersShip, int checkFourthUsersShip, int checkFifthUsersShip, int checkSixthUsersShip,
                           int checkSeventhUsersShip) {
        ArrayList<Integer> checks = new ArrayList<>();

        checks.add(checkFirstShip);
        checks.add(checkSecondShip);
        checks.add(checkThirdShip);
        checks.add(checkFourthShip);
        checks.add(checkFifthShip);
        checks.add(checkSixthShip);
        checks.add(checkSeventhShip);
        checks.add(checkFirstUsersShip);
        checks.add(checkSecondUsersShip);
        checks.add(checkThirdUsersShip);
        checks.add(checkFourthUsersShip);
        checks.add(checkFifthUsersShip);
        checks.add(checkSixthUsersShip);
        checks.add(checkSeventhUsersShip);


        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("sizesToColor.ser"));
            out.writeObject(checks);
            out.close();


        } catch (IOException e) {
            System.out.println("wystąpił błąd: " + e);
        }
    }

}

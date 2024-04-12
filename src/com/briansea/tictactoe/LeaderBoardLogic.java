package com.briansea.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LeaderBoardLogic {

    private Map<String, ArrayList<Integer>> leaderboard = new HashMap<>();


    public void updateLeaderboard(){

    }

    /*
    if(leaderboard.containsKey(winner)){
        leaderboard.get(winner).set(0, leaderboard.get(winner).get(0) + 1);
            if(leaderboard.containsKey(loser)){
                leaderboard.get(loser).set(1, leaderboard.get(loser).get(1) + 1);

            } else {
                ArrayList<Integer> winloss = new ArrayList<Integer>();
                winloss.add(0);
                winloss.add(1);
                winloss.add(0);
                leaderboard.put(loser, winloss);
            }
        }
        else{
            ArrayList<Integer> winloss = new ArrayList<Integer>();
            winloss.add(1);
            winloss.add(0);
            winloss.add(0);
            leaderboard.put(winner, winloss);
            if(leaderboard.containsKey(loser)) {
                leaderboard.get(loser).set(1, leaderboard.get(loser).get(1) + 1);
            }
            else {
                ArrayList<Integer> winloss1 = new ArrayList<Integer>();
                winloss1.add(0);
                winloss1.add(1);
                winloss1.add(0);
                leaderboard.put(loser, winloss1);
                }

        }

            if(leaderboard.containsKey(winner)){
            leaderboard.get(winner).set(0, leaderboard.get(winner).get(0) + 1);
            if(leaderboard.containsKey(loser)){
                leaderboard.get(loser).set(1, leaderboard.get(loser).get(1) + 1);

            } else {
                ArrayList<Integer> winloss = new ArrayList<Integer>();
                winloss.add(0);
                winloss.add(1);
                winloss.add(0);
                leaderboard.put(loser, winloss);
            }
        }
        else{
            ArrayList<Integer> winloss = new ArrayList<Integer>();
            winloss.add(1);
            winloss.add(0);
            winloss.add(0);
            leaderboard.put(winner, winloss);
            if(leaderboard.containsKey(loser)) {
                leaderboard.get(loser).set(1, leaderboard.get(loser).get(1) + 1);
            }
            else {
                ArrayList<Integer> winloss1 = new ArrayList<Integer>();
                winloss1.add(0);
                winloss1.add(1);
                winloss1.add(0);
                leaderboard.put(loser, winloss1);
                }

        }


        if(leaderboard.containsKey(player1)){
            leaderboard.get(player1).set(3, leaderboard.get(player1).get(3) + 1);
            if(leaderboard.containsKey(player2)){
                leaderboard.get(player2).set(3, leaderboard.get(player2).get(3) + 1);

            } else {
                ArrayList<Integer> winloss = new ArrayList<Integer>();
                winloss.add(0);
                winloss.add(0);
                winloss.add(1);
                leaderboard.put(player2, winloss);
            }
        }
        else{
            ArrayList<Integer> winloss = new ArrayList<Integer>();
            winloss.add(0);
            winloss.add(0);
            winloss.add(1);
            leaderboard.put(player1, winloss);
            if(leaderboard.containsKey(player2)) {
                leaderboard.get(player2).set(3, leaderboard.get(player2).get(3) + 1);
            }
            else {
                ArrayList<Integer> winloss1 = new ArrayList<Integer>();
                winloss1.add(0);
                winloss1.add(0);
                winloss1.add(1);
                leaderboard.put(player2, winloss1);
                }

        }
        */



}

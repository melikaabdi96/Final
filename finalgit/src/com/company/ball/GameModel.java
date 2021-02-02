package com.company.ball;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class is a model for this project
 *
 * @author Melika-Morvarid
 * @since 2021
 */
public class GameModel implements Serializable {
    private ArrayList<GameState> gameStates;
    private String userName;
    private int losses;
    private int  wins;
    private int scores;
    private ArrayList<Integer> gamenums;
    public GameModel (String username) {
        this.gameStates = new ArrayList<>();
        this.userName = username;
        this.losses = 0;
    }

    @Override
    public String toString() {
        return
                "Player :" +
                        "username='" + userName + '\'' +
                        ", scoreboard='" + setResult() + '\'';
    }

    /**
     * @return result of the game
     */
    public String setResult(){
        String result = "";
        for (GameState gameState:gameStates){
            if(gameState.isGameOver() == true){
                losses +=1;
                scores -=(gameState.getType().equals(GameState.gameType.NORMAL)? 1 : 3);
                result += "losses:'" + String.valueOf(losses) + '\'' + "score:'" + String.valueOf(scores) + '\n';

            }
            else if(gameState.isEndOfGame() == true){
                wins +=1;
                scores +=(gameState.getType() == GameState.gameType.NORMAL ? 3 : 10);
                result += "winnings:'" + String.valueOf(wins) + '\'' + "score:'" + String.valueOf(scores) + '\n';

            }
        }
        return result;
    }

   /* public String setLosses(){
        for (GameState gameState: gameStates){
            if (gameState.isGameOver() == true){
                losses += 1;
            }
        }
        return null;
    }

    public String setWins(){
        for (GameState gameState: gameStates){
            if (gameState.isEndOfGame() == true){
                wins += 1;
            }
        }
        return null;
    }*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<GameState> getGameStates() {
        return gameStates;
    }

    public void addGame(GameState gameState){
        /*if (!gameStates.contains(gameState)){
            gameStates.add(gameState);
        }*/
        gameStates.add(gameState);
    }

    public int[] loadGame(){
        for (GameState gameState1: gameStates){
            return new int[]{gameStates.indexOf(gameState1)};
        }
        return new int[]{0};
    }
}

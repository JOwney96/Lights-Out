package com.jowney.lightsoutfragments;

public interface LightsOutInterface {
    void newGame();

    boolean isLightOn(int row, int col);

    void selectLight(int row, int col);

    boolean isGameOver();

    String getState();

    void setState(String gameState);
}

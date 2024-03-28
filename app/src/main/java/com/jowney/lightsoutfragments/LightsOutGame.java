package com.jowney.lightsoutfragments;

import java.util.Random;

public class LightsOutGame implements LightsOutInterface {
    public static final int GRID_SIZE = 3;
    private final static Random random = new Random();
    private final boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE];

    @Override
    public void newGame() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                grid[row][col] = random.nextBoolean();
            }
        }
    }

    @Override
    public boolean isLightOn(int row, int col) {
        return grid[row][col];
    }

    @Override
    public void selectLight(int row, int col) {
        grid[row][col] = !grid[row][col];

        if (row > 0)
            grid[row - 1][col] = !grid[row - 1][col];
        if (row < GRID_SIZE - 1)
            grid[row + 1][col] = !grid[row + 1][col];

        if (col > 0)
            grid[row][col - 1] = !grid[row][col - 1];
        if (col < GRID_SIZE - 1)
            grid[row][col + 1] = !grid[row][col + 1];

    }

    @Override
    public boolean isGameOver() {
        for (boolean[] row : grid) {
            for (boolean element : row) {
                if (element)
                    return false;
            }
        }

        return true;
    }

    @Override
    public String getState() {
        StringBuilder builder = new StringBuilder();

        for (boolean[] row : grid) {
            for (boolean element : row) {
                builder.append(
                        element ? "T" : "F"
                );
            }
        }

        return builder.toString();
    }

    @Override
    public void setState(String gameState) {
        for (int i = 0; i < GRID_SIZE * GRID_SIZE - 1; i++) {
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            grid[row][col] = gameState.charAt(i) == 'T';
        }
    }
}

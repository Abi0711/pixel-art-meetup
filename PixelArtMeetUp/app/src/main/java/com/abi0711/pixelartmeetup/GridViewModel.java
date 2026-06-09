package com.abi0711.pixelartmeetup;

import android.graphics.Color;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GridViewModel extends ViewModel {
    private int width = 8;
    private int height = 8;

    private MutableLiveData<int[][]> grid = new MutableLiveData<>();

    public GridViewModel(int rows, int cols) {
        width = cols;
        height = rows;
        // Create the initial primitive 2D array
        int[][] initialMatrix = new int[rows][cols];

        // Populate it with a default color
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                initialMatrix[r][c] = Color.WHITE;
            }
        }

        // Assign it to the LiveData wrapper
        grid.setValue(initialMatrix);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Todo maybe add error message if we can't rtansform
     * @param offset
     */
    public void transformGrid(int offset){
        int[][] newGrid = new int[height][width];
        if(grid.getValue() == null) return;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newGrid[i+offset][j+offset] = grid.getValue()[i][j];
            }
        }
        grid.setValue(newGrid);
    }

    public MutableLiveData<int[][]> getGrid() {
        return grid;
    }

    public void setGrid(MutableLiveData<int[][]> grid) {
        this.grid = grid;
    }

    public void paintCell(int row, int col, int colour){
        int[][] currentGrid = grid.getValue();

        if (currentGrid != null) {
            currentGrid[row][col] = colour;
            grid.setValue(currentGrid);
        }
    }
}

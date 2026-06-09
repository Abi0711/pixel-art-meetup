package com.abi0711.pixelartmeetup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GridViewModelFactory implements ViewModelProvider.Factory {
    private int rows;
    private int cols;

    // The factory constructor accepts the parameters your ViewModel needs
    public GridViewModelFactory(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // Verify that Android is asking for the correct ViewModel class
        if (modelClass.isAssignableFrom(GridViewModel.class)) {
            return (T) new GridViewModel(rows, cols);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

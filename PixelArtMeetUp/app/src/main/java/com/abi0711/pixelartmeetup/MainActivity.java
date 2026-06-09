package com.abi0711.pixelartmeetup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private GridViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        PixelGridView gridView = findViewById(R.id.myCustomGrid);

        GridViewModelFactory gridViewModelFactory = new GridViewModelFactory(10, 10);
        viewModel = new ViewModelProvider(this, gridViewModelFactory).get(GridViewModel.class);

        final Observer<int[][]> gridObserver = new Observer<int[][]>() {
            @Override
            public void onChanged(@Nullable final int[][] newGrid) {
                gridView.setGridColour(newGrid);
            }
        };

        viewModel.getGrid().observe(this, gridObserver);

        gridView.setOnGridClickListener(new PixelGridView.OnGridClickListener() {
            @Override
            public void onCellClick(int row, int col, int colour) {
                viewModel.paintCell(row, col, colour);
            }
        });

    }
}
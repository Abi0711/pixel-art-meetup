package com.abi0711.pixelartmeetup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        gridView.setOnGridClickListener(new PixelGridView.OnGridClickListener() {
            @Override
            public void onCellClick(int row, int col) {
                Toast.makeText(MainActivity.this,
                        "Clicked cell at: [" + row + ", " + col + "]",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
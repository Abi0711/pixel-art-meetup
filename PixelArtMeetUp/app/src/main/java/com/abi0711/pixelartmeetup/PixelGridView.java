package com.abi0711.pixelartmeetup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.Arrays;

/**
 * TODO: document your custom view class.
 */
public class PixelGridView extends View {
    private int rows = 8;
    private int cols = 8;
    private Paint linePaint;
    private Paint selectionPaint;

    private int[][] gridColour = new int[rows][cols];

    public PixelGridView(Context context) {
        super(context);
        init();
    }

    public PixelGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PixelGridView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Initialize paint for grid lines
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(4f);
        linePaint.setStyle(Paint.Style.STROKE);

        // Initialize paint for highlighting a clicked cell
        selectionPaint = new Paint();
        selectionPaint.setColor(Color.YELLOW);
        selectionPaint.setStyle(Paint.Style.FILL);

        for (int[] row : gridColour) {
            Arrays.fill(row, -1);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        float cellWidth = (float) width / cols;
        float cellHeight = (float) height / rows;

        for (int i = 0; i < gridColour.length; i++) {
            for (int j = 0; j < gridColour.length; j++) {
                float left = j * cellWidth;
                float top = i * cellHeight;
                float right = left + cellWidth;
                float bottom = top + cellHeight;
                if(gridColour[i][j] == -1){
                    selectionPaint.setColor(Color.WHITE);
                }
                else {
                    selectionPaint.setColor(gridColour[i][j]);
                }

                canvas.drawRect(left, top, right, bottom, selectionPaint);
            }
        }

        // Draw grid lines
        for (int i = 1; i < cols; i++) {
            float x = i * cellWidth;
            canvas.drawLine(x, 0, x, height, linePaint);
        }

        for (int i = 1; i < rows; i++) {
            float y = i * cellHeight;
            canvas.drawLine(0, y, width, y, linePaint);
        }

        // Draw border outline
        canvas.drawRect(0, 0, width, height, linePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();

            float cellWidth = (float) getWidth() / cols;
            float cellHeight = (float) getHeight() / rows;

            // Calculate which row and column were clicked
            int clickedCol = (int) (x / cellWidth);
            int clickedRow = (int) (y / cellHeight);

            // Bound checking to ensure taps on the very edge don't cause an IndexOutOfBounds
            if (clickedRow >= 0 && clickedRow < rows && clickedCol >= 0 && clickedCol < cols) {

                if(gridColour[clickedRow][clickedCol] != -1) gridColour[clickedRow][clickedCol] = -1;
                else gridColour[clickedRow][clickedCol] = Color.YELLOW;




                // CRITICAL: Tells Android the view's visual state is old and forces an onDraw() update
                invalidate();

                // Optional: Trigger a custom action or standard click listener
//                if (gridClickListener != null) {
//                    gridClickListener.onCellClick(selectedRow, selectedCol);
//                }
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    // Custom interface to pass click data back to your Activity/Fragment
    public interface OnGridClickListener {
        void onCellClick(int row, int col);
    }

    private OnGridClickListener gridClickListener;

    public void setOnGridClickListener(OnGridClickListener listener) {
        this.gridClickListener = listener;
    }
}
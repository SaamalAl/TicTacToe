package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean playerXTurn = true; // X или O
    int[][] board = new int[3][3]; // игровое поле
    TextView statusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusTextView = findViewById(R.id.status);
        resetBoard();
    }

    public void onGridClick(View view) {
        Button button = (Button) view;
        int row = -1, col = -1;

        // Определить кнопку с помощью if-else вместо switch
        if (button.getId() == R.id.button1) {
            row = 0; col = 0;
        } else if (button.getId() == R.id.button2) {
            row = 0; col = 1;
        } else if (button.getId() == R.id.button3) {
            row = 0; col = 2;
        } else if (button.getId() == R.id.button4) {
            row = 1; col = 0;
        } else if (button.getId() == R.id.button5) {
            row = 1; col = 1;
        } else if (button.getId() == R.id.button6) {
            row = 1; col = 2;
        } else if (button.getId() == R.id.button7) {
            row = 2; col = 0;
        } else if (button.getId() == R.id.button8) {
            row = 2; col = 1;
        } else if (button.getId() == R.id.button9) {
            row = 2; col = 2;
        }

        if (row == -1 || col == -1 || board[row][col] != 0) {
            return; // Кнопка не распознана или клетка уже занята
        }

        // Установить метку X или O
        if (playerXTurn) {
            button.setText("X");
            board[row][col] = 1;
        } else {
            button.setText("O");
            board[row][col] = 2;
        }

        if (checkForWin()) {
            statusTextView.setText(playerXTurn ? "Игрок X победил!" : "Игрок O победил!");
            disableButtons();
        } else if (isBoardFull()) {
            statusTextView.setText("Ничья!");
        } else {
            playerXTurn = !playerXTurn;
            statusTextView.setText(playerXTurn ? "Игрок X ходит" : "Игрок O ходит");
        }
    }

    public boolean checkForWin() {
        // Проверка строк, столбцов и диагоналей
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
                return true;
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0) {
                return true;
            }
        }

        // Проверка диагоналей
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
            return true;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
            return true;
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void disableButtons() {
        GridLayout grid = findViewById(R.id.grid);
        for (int i = 0; i < grid.getChildCount(); i++) {
            grid.getChildAt(i).setEnabled(false);
        }
    }

    public void resetGame(View view) {
        resetBoard();
    }

    public void resetBoard() {
        GridLayout grid = findViewById(R.id.grid);
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button button = (Button) grid.getChildAt(i);
            button.setText("");
            button.setEnabled(true);
        }
        board = new int[3][3];
        playerXTurn = true;
        statusTextView.setText("Игрок X ходит");
    }
}

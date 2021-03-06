package com.amanaggarwal1.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declaring views
    private GridLayout grid;
    private TextView information;
    private Button clearButton;

    private String updatedInfo;
    int updateColor = Color.YELLOW;
    private boolean gameOver = false;
    private int count = 0; // number of turn in the ongoing game

    private int activePlayer = 0;
    private int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //value 2 at any index refers that it is empty, i.e not occupied by any player
    //value 0 at any index refers that it is occupied by yellow
    //value 1 at any index refers that it is occupied by red

    //Storing all possible winning positions in an array
    private int[][] winningPositions = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7} };


    private void checkForWin() {
        //Loop through all winning situation status
        for (int[] pos : winningPositions)
            if (gameStatus[pos[0] - 1] == gameStatus[pos[1] - 1] && gameStatus[pos[1] - 1] == gameStatus[pos[2] - 1] && gameStatus[pos[0] - 1] != 2) {

                gameOver = true;

                information.animate().alpha(1).setDuration(300);
                clearButton.animate().alpha(1).setDuration(300);

                if (activePlayer == 1) {
                    updatedInfo = "Red Wins!";
                    updateColor = Color.RED;
                    //Set images with crown symbol in them
                    for (int i = 0; i < grid.getChildCount(); i++)
                        if (i == pos[0] - 1 || i == pos[1] - 1 || i == pos[2] - 1) {
                            ImageView image = (ImageView) grid.getChildAt(i);
                            image.setImageResource(R.drawable.red_win);
                        }

                } else {
                    updatedInfo = "Yellow Wins!";
                    updateColor = Color.YELLOW;
                    //Set images with crown symbol in them
                    for (int i = 0; i < grid.getChildCount(); i++)
                        if (i == pos[0] - 1 || i == pos[1] - 1 || i == pos[2] - 1) {
                            ImageView image = (ImageView) grid.getChildAt(i);
                            image.setImageResource(R.drawable.yellow_win);
                        }
                }
            }else if(count == 9){
                updatedInfo = "This game was tie";
                gameOver = true;
                information.animate().alpha(1).setDuration(300);
                clearButton.animate().alpha(1).setDuration(300);
            }
    }

    public void resetGame(View v){
        //check if game is over yet or not
        if(!gameOver){
            Toast.makeText(this, "Please finish this round", Toast.LENGTH_SHORT).show();
            return;
        }

        information.setText("Yellow's Turn");
        information.setTextColor(Color.YELLOW);
        information.animate(). alpha(0.4f).setDuration(300);
        clearButton.animate().alpha(0.4f).setDuration(300);
        for(int i = 0; i < grid.getChildCount(); i++){
            ImageView image = (ImageView) grid.getChildAt(i);
            image.setImageDrawable(null);
        }
        for(int p = 0; p < gameStatus.length; p++)
            gameStatus[p] = 2;

        count = 0;
        gameOver = false;
        activePlayer = 0;
    }


    //dropIn function gets called when any of the grid layout position is tapped
    public void dropIn(View v){
        ImageView image = (ImageView) v;

        //Get tag associated with image chosen by user
        int tag = Integer.parseInt( image.getTag().toString() );

        if(gameOver) {
            // Display a toast message if the game is over
            Toast.makeText(this, "Game is over\nPlease reset the board to play again", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(gameStatus[tag -1] != 2) {
            // Display a toast message if the position is already occupied
            Toast.makeText(this, "This tile is already occupied", Toast.LENGTH_SHORT).show();
            return;
        }

        count++;
        // Initially translating the image beyond the screen in upward direction
        image.setTranslationY(-1000);

        //Setting image resource and information text view depending on active Player
        if(activePlayer == 1) {
            image.setImageResource(R.drawable.red);
            updatedInfo = "Yellow's Turn";
            updateColor = Color.YELLOW;
        } else{
            image.setImageResource(R.drawable.yellow);
            updatedInfo = "Red's Turn";
            updateColor = Color.RED;
        }

        //Set game status corresponding to that image position to the active player
        gameStatus[tag - 1] = activePlayer;

        if(count >=5 && count <= 9)
            checkForWin();

        activePlayer ^= 1; //Changing the value of active player

        //information text update takes a delay of 300ms
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                information.setText(updatedInfo);
                information.animate().alpha(0.6f).setDuration(200);
                information.setTextColor(updateColor);
            }
        }, 300);

        // image comes spinning from the top of the screen
        image.animate().translationYBy(1000).rotationBy(1800).setDuration(300);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking views
        grid = findViewById(R.id.boardGridLayout);
        information = findViewById(R.id.informationTV);
        clearButton = findViewById(R.id.clearButton);
    }
}

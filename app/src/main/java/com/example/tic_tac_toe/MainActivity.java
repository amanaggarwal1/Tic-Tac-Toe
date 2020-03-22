package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declaring views
    private TextView information;
    private Button clearButton;

    String updatedInfo;
    private int activePlayer = 0;
    private int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //value 2 at any index refers that it is empty, i.e not occupied by any player
    //value 0 at any index refers that it is occupied by yellow
    //value 1 at any index refers that it is occupied by red

    //Storing all possible winning positions in an array
    private int[][] winningPositions = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7} };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking views
        information = findViewById(R.id.informationTV);
        clearButton = findViewById(R.id.clearButton);
    }

    //dropIn function gets called when any of the grid layout position is tapped
    public void dropIn(View v){
        ImageView image = (ImageView) v;

        //Get tag associated with image chosen by user
        int tag = Integer.parseInt( image.getTag().toString() );

        // Display a toast message if the position is already occupied
        if(gameStatus[tag -1] != 2) {
            Toast.makeText(MainActivity.this, "This tile is already occupied", Toast.LENGTH_SHORT).show();
            return;
        }

        // Initially translating the image beyond the screen in upward direction
        image.setTranslationY(-1000);

        //Setting image resource and information text view depending on active Player
        if(activePlayer == 1) {
            image.setImageResource(R.drawable.red);
            updatedInfo = "Yellow's Turn";
        } else{
            image.setImageResource(R.drawable.yellow);
            updatedInfo = "Red's Turn";
        }

        //Set game status corresponding to that image position to the active player
        gameStatus[tag - 1] = activePlayer;

        //Loop through all winning situation status
        for(int i = 0; i < 8; i++){
            if(gameStatus[ winningPositions[i][0]  - 1 ] == gameStatus[ winningPositions[i][1]  - 1 ] && gameStatus[ winningPositions[i][1]  - 1 ] == gameStatus[ winningPositions[i][2]  - 1 ]  && gameStatus[ winningPositions[i][0]  - 1 ] != 2)
                if(activePlayer == 1) updatedInfo = "Red Wins ";
                else updatedInfo = "Yellow Wins";
        }

        activePlayer ^= 1; //Changing the value of active player

        //information text update takes a delay of 300ms
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                information.setText(updatedInfo);
            }
        }, 300);

        // image comes spinning from the top of the screen
        image.animate().translationYBy(1000).rotationBy(1800).setDuration(300);

        Log.i("LOGCAT", "Position = " + tag + " occupied by " + gameStatus[tag - 1]);

    }
}

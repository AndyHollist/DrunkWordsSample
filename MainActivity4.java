package com.example.alpha.project_palindrome;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity4 extends ActionBarActivity {

    String[] my_array1 = new String[13];
    int[] player_scores = new int[9];
    //fetching the variables
    int pos1;
    int pos2;
    int pos3;

    int curr_round = 1;
    int curr_player = 1;
    TextView player_name;
    TextView round_number;

    int[] players_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_activity4);


        Bundle b = getIntent().getExtras();
        my_array1 = b.getStringArray("names");

        pos1 = b.getInt("pos1",pos1);//num players
        pos2 = b.getInt("pos2",pos2);//difficulty
        pos3 = b.getInt("pos3",pos3);//num rounds

        players_word = b.getIntArray("players_word");

        player_name = (TextView) findViewById(R.id.textView6);
        player_name.setTextSize(40);
        player_name.setText(my_array1[curr_player]);

        round_number = (TextView) findViewById(R.id.textView7);
        round_number.setTextSize(30);
        round_number.setText("Round " + curr_round);

        Log.d("k"+pos1, my_array1[1]);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void StartGame(View view)
    {

        Intent i = new Intent(this, MainActivity5.class);
        i.putExtra("names", my_array1);
        i.putExtra("pos1",pos1);
        i.putExtra("pos2",pos2);
        i.putExtra("pos3",pos3);
        i.putExtra("curr_player",curr_player);
        i.putExtra("curr_round",curr_round);
        i.putExtra("player_scores", player_scores);
        i.putExtra("players_word", players_word);

        Log.d("m", "Starting Game");

        startActivityForResult(i, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                curr_player++;
                Bundle b1 = new Bundle();
                try
                {
                    b1 = data.getExtras();

                }
                catch(Exception e){}
                players_word = b1.getIntArray("players_word");

                if(curr_player > pos1) {
                    curr_round++;
                    Log.d("m", " " + pos3 + " " + curr_round);
                    if (curr_round > pos3) {
                        //game over
                        player_scores = b1.getIntArray("player_scores");
                        Intent game_over = new Intent(this, MainActivity9.class);
                        game_over.putExtra("names", my_array1);
                        game_over.putExtra("players_word", players_word);
                        game_over.putExtra("curr_round", curr_round);
                        game_over.putExtra("num_players", pos1);
                        game_over.putExtra("player_scores", player_scores);
                        startActivity(game_over);
                    }
                    else {
                        round_number.setText("Round " + curr_round);
                        curr_player = 1;
                        Log.d("m", "test8");
                        Bundle b = new Bundle();
                        try {
                            b = data.getExtras();
                        } catch (Exception e) {
                            Log.d("m", e.getMessage());
                        }
                        player_scores = b.getIntArray("player_scores");
                        //players_word = b.getIntArray("players_word");


                        Log.d("m", "test9");


                    }
                }
                player_name.setText(my_array1[curr_player]);

            }
        }
    }


    @Override
    public void onBackPressed() {
    }
}

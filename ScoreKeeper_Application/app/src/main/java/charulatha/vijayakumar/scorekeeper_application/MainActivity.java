package charulatha.vijayakumar.scorekeeper_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
implements TextView.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    Button btnIncrease = findViewById(R.id.btnIncrease);
    Button btnDecrease = findViewById(R.id.btnDecrease);
    EditText TeamNameA = findViewById(R.id.etTeam_A_Name);
    EditText TeamNameB = findViewById(R.id.etTeam_B_Name);


    ToggleButton togglePlayingTeam = findViewById(R.id.togglePlayingTeam);
    togglePlayingTeam.setOnClickListener(this);


    btnIncrease.setOnClickListener(this);
    btnDecrease.setOnClickListener(this);

    //Updating toggle button on change of team name A
    TeamNameA.addTextChangedListener(new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            togglePlayingTeam.setTextOn(s);
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    });

    //Updating toggle button on change of team name B
    TeamNameB.addTextChangedListener(new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            togglePlayingTeam.setTextOff(s);
        }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    });

    }

    @Override
    public void onClick(View v) {
        TextView txtViewTeamScoreA = findViewById(R.id.txtViewTeamScoreA);
        TextView txtViewTeamScoreB = findViewById(R.id.txtViewTeamScoreB);
        RadioGroup scoreGroup = findViewById(R.id.rgScoreGroup);
        ToggleButton togglePlayingTeam = findViewById(R.id.togglePlayingTeam);

        String teamScoreA = txtViewTeamScoreA.getText().toString();
        String teamScoreB = txtViewTeamScoreB.getText().toString();

        int selectedId = scoreGroup.getCheckedRadioButtonId();
        String scoreSplit[];

        String playingTeam = "";
        if(togglePlayingTeam.isChecked()){
            playingTeam = "A";
        }else {
            playingTeam = "B";
        }
        int addScore = 0, wicket=0;
        boolean flagWicket =false, inningsComplete=false,inningsCompleteTeamA=false,inningsCompleteTeamB=false;
        switch (selectedId){
            case R.id.rbOneRun:
                addScore=1;
                break;
            case R.id.rbTwoRuns:
                addScore=2;
                break;
            case R.id.rbFourRuns:
                addScore=4;
                break;
            case R.id.rbSixRuns:
                addScore=6;
                break;
            case R.id.rbWicket:
                addScore=0;
                flagWicket=true;
                break;
        }

        switch (v.getId()){
            case R.id.btnIncrease:
                if(playingTeam=="A") {
                    scoreSplit = teamScoreA.split(" - ", 2);
                    if (flagWicket) {
                        if(Integer.parseInt(scoreSplit[1])>=9){
                            inningsComplete=true;
                            inningsCompleteTeamA=true;
                            wicket = Integer.parseInt(scoreSplit[1]) + 1;
                            teamScoreA = scoreSplit[0] + " - " + String.valueOf(wicket);
                            updateOvers("inc","A");
                        }else {
                            wicket = Integer.parseInt(scoreSplit[1]) + 1;
                            teamScoreA = scoreSplit[0] + " - " + String.valueOf(wicket);
                            inningsCompleteTeamA=updateOvers("inc","A");
                            inningsComplete=inningsCompleteTeamA;
                        }
                    }
                    else{
                        addScore += Integer.parseInt(scoreSplit[0]);
                        teamScoreA = String.valueOf(addScore) + " - " + scoreSplit[1];
                        inningsCompleteTeamA=updateOvers("inc","A");
                        inningsComplete=inningsCompleteTeamA;
                    }
                    txtViewTeamScoreA.setText(teamScoreA.toString());
                }
                else if (playingTeam=="B"){
                    scoreSplit = teamScoreB.split(" - ",2);
                    if (flagWicket) {
                        if(Integer.parseInt(scoreSplit[1])>=9){
                            inningsComplete=true;
                            inningsCompleteTeamB=true;
                            wicket = Integer.parseInt(scoreSplit[1]) + 1;
                            teamScoreB = scoreSplit[0] + " - " + String.valueOf(wicket);
                            updateOvers("inc","B");
                        }else {
                            wicket = Integer.parseInt(scoreSplit[1]) + 1;
                            teamScoreB = scoreSplit[0] + " - " + String.valueOf(wicket);
                            inningsCompleteTeamB=updateOvers("inc","B");
                            inningsComplete=inningsCompleteTeamB;
                        }
                    }
                    else{
                        addScore += Integer.parseInt(scoreSplit[0]);
                        teamScoreB = String.valueOf(addScore) + " - " + scoreSplit[1];
                        inningsCompleteTeamB=updateOvers("inc","B");
                        inningsComplete=inningsCompleteTeamB;
                    }
                    txtViewTeamScoreB.setText(teamScoreB.toString());
                }

                break;

            case R.id.btnDecrease:
                //Team A
                if(playingTeam=="A") {
                    scoreSplit = teamScoreA.split(" - ", 2);
                    if (flagWicket) {
                        if(!(Integer.parseInt(scoreSplit[1])<=0)){
                            wicket = Integer.parseInt(scoreSplit[1]) - 1;
                            inningsCompleteTeamA=updateOvers("dec","A");
                            inningsComplete=inningsCompleteTeamA;
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Wicket cannot be negative", Toast.LENGTH_SHORT).show();
                        }
                        teamScoreA = scoreSplit[0] + " - " + String.valueOf(wicket);
                    }
                    else{
                        addScore = Integer.parseInt(scoreSplit[0])-addScore;
                        if(!(addScore<0)){
                            teamScoreA = String.valueOf(addScore) + " - " + scoreSplit[1];
                            inningsCompleteTeamA=updateOvers("dec","A");
                            inningsComplete=inningsCompleteTeamA;
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Score cannot be negative", Toast.LENGTH_SHORT).show();
                        }

                    }
                    txtViewTeamScoreA.setText(teamScoreA.toString());

                }
                //Team B
                else if (playingTeam=="B"){
                    scoreSplit = teamScoreB.split(" - ",2);
                    if (flagWicket) {
                        if(!(Integer.parseInt(scoreSplit[1])<=0)){
                            wicket = Integer.parseInt(scoreSplit[1]) - 1;
                            inningsCompleteTeamB=updateOvers("dec","B");
                            inningsComplete=inningsCompleteTeamB;
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Wicket cannot be negative", Toast.LENGTH_SHORT).show();
                        }
                        teamScoreB = scoreSplit[0] + " - " + String.valueOf(wicket);
                    }
                    else{
                        addScore = Integer.parseInt(scoreSplit[0])-addScore;
                        if(!(addScore<0)){
                            teamScoreB = String.valueOf(addScore) + " - " + scoreSplit[1];
                            inningsCompleteTeamB=updateOvers("dec","B");
                            inningsComplete=inningsCompleteTeamB;
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Score cannot be negative", Toast.LENGTH_SHORT).show();
                        }
                    }
                    txtViewTeamScoreB.setText(teamScoreB.toString());

                }

                break;

            case R.id.togglePlayingTeam:
                boolean matchLive=false;

                if(playingTeam=="A"){
                    if (!(teamScoreB.contentEquals("0 - 0"))){
                        matchLive=true;
                    }
                }
                else if(playingTeam=="B"){
                    if (!(teamScoreA.contentEquals("0 - 0"))){
                        matchLive=true;
                    }
                }
                if(matchLive){
                    new AlertDialog.Builder(this)
                            .setTitle("Confirm Reset")
                            .setMessage("Do you want to reset scores and restart match?")
//                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                TextView textViewOvers_A = findViewById(R.id.txtViewOversTeamA);
                                TextView textViewOvers_B = findViewById(R.id.txtViewOversTeamB);

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    txtViewTeamScoreA.setText("0 - 0");
                                    txtViewTeamScoreB.setText("0 - 0");
                                    textViewOvers_A.setText("0 . 0");
                                    textViewOvers_B.setText("0 . 0");
//                                    togglePlayingTeam.setChecked(!togglePlayingTeam.isChecked());
                                    Toast.makeText(MainActivity.this, "Reset the score to zero", Toast.LENGTH_SHORT).show();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();
                }
                break;

            default:
                break;


        }
        if(inningsComplete){
            if(!(teamScoreA.contentEquals("0 - 0")) && (!(teamScoreB.contentEquals("0 - 0")))){
                String teamScoreARuns[] = teamScoreA.split(" - ");
                String teamScoreBRuns[] = teamScoreB.split(" - ");
                EditText TeamNameA = findViewById(R.id.etTeam_A_Name);
                EditText TeamNameB = findViewById(R.id.etTeam_B_Name);
                String TeamNameAString = TeamNameA.getText().toString();
                String TeamNameBString = TeamNameB.getText().toString();
                String resultString;

                if(teamScoreARuns[0]==teamScoreBRuns[0]){
                    resultString="Match Draw \n Click Ok to reset scores.";
                }
                else if(Integer.parseInt(teamScoreARuns[0])>Integer.parseInt(teamScoreBRuns[0])){
                    resultString=TeamNameAString+" Won ! \n Click Ok to reset scores";
                }
                else {
                    resultString=TeamNameBString+" Won ! \n Click Ok to reset scores";
                }

                new AlertDialog.Builder(this)
                        .setTitle("Match Over")
                        .setMessage(resultString)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            TextView textViewOvers_A = findViewById(R.id.txtViewOversTeamA);
                            TextView textViewOvers_B = findViewById(R.id.txtViewOversTeamB);

                            public void onClick(DialogInterface dialog, int whichButton) {
                                txtViewTeamScoreA.setText("0 - 0");
                                txtViewTeamScoreB.setText("0 - 0");
                                textViewOvers_A.setText("0 . 0");
                                textViewOvers_B.setText("0 . 0");
//                                    togglePlayingTeam.setChecked(!togglePlayingTeam.isChecked());
                                Toast.makeText(MainActivity.this, "Starting a new match ! ", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }else{
                Toast.makeText(MainActivity.this, "First Innings Completed!", Toast.LENGTH_SHORT).show();
                togglePlayingTeam.setChecked(!togglePlayingTeam.isChecked());
                scoreGroup.check(R.id.rbOneRun);
            }


        }
    }

    public boolean updateOvers(String task, String team){
        TextView textViewOvers_A = findViewById(R.id.txtViewOversTeamA);
        TextView textViewOvers_B = findViewById(R.id.txtViewOversTeamB);
        String teamAOver = textViewOvers_A.getText().toString();
        String teamBOver = textViewOvers_B.getText().toString();
        String overSplit[];
        boolean inningsOver=false;



        if(task=="inc"){
            if(team=="A"){
                overSplit=teamAOver.split(" . ");
                if (overSplit[1].contentEquals("5")){
                    teamAOver=String.valueOf((Integer.parseInt(overSplit[0]))+1)+" . 0";
                }
                else{
                    teamAOver=overSplit[0]+" . "+String.valueOf((Integer.parseInt(overSplit[1]))+1);
                }
                textViewOvers_A.setText(teamAOver.toString());
            }
            else if(team=="B"){
                overSplit=teamBOver.split(" . ");
                if (overSplit[1].contentEquals("5")){
                    teamBOver=String.valueOf((Integer.parseInt(overSplit[0]))+1)+" . 0";
                }
                else{
                    teamBOver=overSplit[0]+" . "+String.valueOf((Integer.parseInt(overSplit[1]))+1);
                }
                textViewOvers_B.setText(teamBOver.toString());
            }
            if((team=="A" && (teamAOver.contentEquals("20 . 0"))) || (team=="B" && (teamBOver.contentEquals("20 . 0")))){
                inningsOver=true;
            }
        }

        if(task=="dec"){
            if(team=="A"){
                overSplit=teamAOver.split(" . ");
                if (!(teamAOver.contentEquals("0 . 0"))){
                    if (overSplit[1].contentEquals("0")){
                        teamAOver=String.valueOf((Integer.parseInt(overSplit[0]))-1)+" . 5";
                    }
                    else{
                        switch (overSplit[1]){
                            case "1":
                                teamAOver=overSplit[0]+" . 0";
                                break;
                            case "2":
                                teamAOver=overSplit[0]+" . 1";
                                break;
                            case "3":
                                teamAOver=overSplit[0]+" . 2";
                                break;
                            case "4":
                                teamAOver=overSplit[0]+" . 3";
                                break;
                            case "5":
                                teamAOver=overSplit[0]+" . 4";
                                break;
                        }

                        //teamAOver=overSplit[0]+" . "+String.valueOf((Integer.parseInt(overSplit[1]))-1);
                    }
                    textViewOvers_A.setText(teamAOver.toString());
                }
                else {
                    Toast.makeText(MainActivity.this, "Overs cannot be less than 0 . 0", Toast.LENGTH_SHORT).show();
                }

            }
            else if(team=="B"){
                overSplit=teamBOver.split(" . ");
                if (!(teamBOver.contentEquals("0 . 0"))){
                    if (overSplit[1].contentEquals("0")){
                        teamBOver=String.valueOf((Integer.parseInt(overSplit[0]))-1)+" . 5";
                    }
                    else{
                        switch (overSplit[1]){
                            case "1":
                                teamBOver=overSplit[0]+" . 0";
                                break;
                            case "2":
                                teamBOver=overSplit[0]+" . 1";
                                break;
                            case "3":
                                teamBOver=overSplit[0]+" . 2";
                                break;
                            case "4":
                                teamBOver=overSplit[0]+" . 3";
                                break;
                            case "5":
                                teamBOver=overSplit[0]+" . 4";
                                break;
                        }

                    }
                    textViewOvers_B.setText(teamBOver.toString());
                }
                else {
                    Toast.makeText(MainActivity.this, "Overs cannot be less than 0 . 0", Toast.LENGTH_SHORT).show();
                }
            }
            if((team=="A" && (teamAOver.contentEquals("20 . 0"))) || (team=="B" && (teamBOver.contentEquals("20 . 0")))){
                inningsOver=true;
            }
        }
        return inningsOver;
    }


}
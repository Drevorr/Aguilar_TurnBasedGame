package mcm.edu.ph.aguilar_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView txtEnemyMon, txtEnemyHP, txtEnemyMP, txtMyMon, txtMonHP, txtMonMP;
    TextView txtMonDmg, txtEnemyDmg;
    TextView txtBattleHistory;
    Button buttonNextTurn;

    //Salamence
    String MyMon = "Salamence";
    int monHP = 125;
    int monMP = 80;
    int monMinDmg = 14;
    int monMaxDmg = 29;

    //Garchomp
    String EnemyMon = "Garchomp";
    int enemyHP = 150;
    int enemyMP = 100;
    int enemyMinDmg = 12;
    int enemyMaxDmg = 26;

    int turnNumber= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEnemyMon = findViewById(R.id.txtEnemyMon);
        txtEnemyHP = findViewById(R.id.txtEnemyHP);
        txtEnemyMP = findViewById(R.id.txtEnemyMP);
        txtMyMon = findViewById(R.id.txtMyMon);
        txtMonHP = findViewById(R.id.txtMonHP);
        txtMonMP = findViewById(R.id.txtMonMP);
        buttonNextTurn = findViewById(R.id.buttonNextTurn);
        txtMonDmg = findViewById(R.id.txtMonDmg);
        txtEnemyDmg = findViewById(R.id.txtEnemyDmg);
        txtBattleHistory = findViewById(R.id.txtBattleHistory);


        txtMyMon.setText(MyMon);
        txtMonHP.setText(String.valueOf(monHP));
        txtMonMP.setText(String.valueOf(monMP));

        txtEnemyMon.setText(EnemyMon);
        txtEnemyHP.setText(String.valueOf(enemyHP));
        txtEnemyMP.setText(String.valueOf(enemyMP));

        txtMonDmg.setText(String.valueOf(monMinDmg)+ " = "+ String.valueOf(monMaxDmg));
        txtEnemyDmg.setText(String.valueOf(enemyMinDmg)+ " = "+ String.valueOf(enemyMaxDmg));


        buttonNextTurn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        Random randomizer = new Random();
        int MonDamageRoll = randomizer.nextInt(monMaxDmg - monMinDmg) + monMinDmg ;
        int EnemyDamageRoll = randomizer.nextInt(enemyMaxDmg - enemyMinDmg) + enemyMinDmg ;

        switch(v.getId()) {
            case R.id.buttonNextTurn:

                if(turnNumber % 2 ==1){
                    enemyHP = enemyHP - MonDamageRoll;
                    turnNumber++;
                    txtEnemyHP.setText(String.valueOf(enemyHP));
                    buttonNextTurn.setText("Next Turn ("+String.valueOf(turnNumber)+")");

                    txtBattleHistory.setText("Salamence used Dragon Rush! It's super effective!");

                    if(enemyHP < 0){
                        txtBattleHistory.setText("Salamence used Dragon Rush! It's super effective! Congratulations! You have won the battle!");
                        monHP = 125;
                        enemyHP = 150;
                        turnNumber= 1;
                        buttonNextTurn.setText("Restart Game");
                    }
                }
                else if(turnNumber%2 != 1){
                    monHP = monHP - EnemyDamageRoll;
                    turnNumber++;
                    txtMonHP.setText(String.valueOf(monHP));
                    buttonNextTurn.setText("Next Turn ("+String.valueOf(turnNumber)+")");

                    txtBattleHistory.setText("Garchomp used Outrage! It's super effective!");

                    if(monHP < 0){
                        txtBattleHistory.setText("Garchomp used Outrage! It's super effective! You have lost the battle. Better luck next time!");
                        monHP = 125;
                        enemyHP = 150;
                        turnNumber= 1;
                        buttonNextTurn.setText("Restart Game");
                    }
                }


                break;
        }
    }

}
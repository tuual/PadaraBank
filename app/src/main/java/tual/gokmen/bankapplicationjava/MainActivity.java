package tual.gokmen.bankapplicationjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.view.Window;


public class MainActivity extends AppCompatActivity {
    private Button button;
    private SharedPreferences sharedPreferences;
    private Window window;
    private Boolean tutorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.cardBtn);
        window = this.getWindow();
        sharedPreferences = getSharedPreferences("skipPage",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        tutorial = sharedPreferences.getBoolean("tutorial",false);

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorTutorialBg));
        if(tutorial == true){
            startActivity(new Intent(this, frmLogin.class));
            MainActivity.this.overridePendingTransition(
                    R.anim.animate_card_enter,
                    R.anim.animate_card_exit
            );
        }
        else{
            
        }


        button.setOnClickListener(view ->{
            startActivity(new Intent(this, frmLogin.class));
            MainActivity.this.overridePendingTransition(
                    R.anim.animate_card_enter,
                    R.anim.animate_card_exit
            );
            editor.putBoolean("tutorial",true);
            editor.apply();
        });

    }
    private void skipPage(){

    }
}
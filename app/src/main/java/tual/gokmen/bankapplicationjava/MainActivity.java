package tual.gokmen.bankapplicationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.cardBtn);

        button.setOnClickListener(view ->{
            startActivity(new Intent(this, frmLogin.class));
            MainActivity.this.overridePendingTransition(
                    R.anim.animate_card_enter,
                    R.anim.animate_card_exit
            );
        });

    }
}
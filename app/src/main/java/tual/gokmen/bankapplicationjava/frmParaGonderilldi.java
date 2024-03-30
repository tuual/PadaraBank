package tual.gokmen.bankapplicationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class frmParaGonderilldi extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_para_gonderilldi);
        btn = findViewById(R.id.btnDevamet);
        btn.setOnClickListener(view -> {
            startActivity(new Intent(this,frmAnaMenu.class));
            finish();
        });
    }
}
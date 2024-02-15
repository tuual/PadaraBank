package tual.gokmen.bankapplicationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.imageview.ShapeableImageView;

public class frmAnaMenu extends AppCompatActivity {

    private ShapeableImageView imgGonder,imgDetay,imgYatir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_ana_menu);
        setID();
        clickFuncs();
    }

    private void clickFuncs() {
        imgGonder.setOnClickListener(view ->{
            startActivity(new Intent(this, frmParaGonderme.class));
        });
    }

    private void setID(){
        imgGonder = findViewById(R.id.imgGonder);
        imgDetay = findViewById(R.id.imgDetay);
        imgYatir = findViewById(R.id.imgYatir);
    }
}
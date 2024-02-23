package tual.gokmen.bankapplicationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

public class frmAnaMenu extends AppCompatActivity {

    private ShapeableImageView imgGonder,imgDetay,imgYatir;
    private MaterialTextView tvBakiye,tvpadaranumara;
    private String musteriID,musteriTC,musteriParola,musteriBakiye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_ana_menu);
        Bundle extras = getIntent().getExtras();
        setID();


        musteriID = extras.getString("musteriID");
        musteriTC = extras.getString("musteriTC");
        musteriParola = extras.getString("musteriParola");
        musteriBakiye = extras.getString("musteriBakiye");
        tvBakiye.setText(musteriBakiye.toString()+"TL");
        tvpadaranumara.setText("Padara Numaran: "+musteriID.toString());
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
        tvBakiye = findViewById(R.id.tvBakiye);
        tvpadaranumara = findViewById(R.id.materialTextView3);
    }
}
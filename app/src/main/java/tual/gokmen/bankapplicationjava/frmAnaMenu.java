package tual.gokmen.bankapplicationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;
    private Bundle extras;
    private SharedPreferences.Editor editor;
    private String hesapBakiye,hesapID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_ana_menu);
         extras = getIntent().getExtras();
        setID();
        sharedPreferencesSet();


        tvBakiye.setText(hesapBakiye.toString()+"TL");
        tvpadaranumara.setText("Padara Numaran: "+hesapID.toString());
        clickFuncs();
    }

    private void clickFuncs() {
        imgGonder.setOnClickListener(view ->{
            Intent intent = new Intent(getApplicationContext(), frmParaGonderme.class);
            startActivity(intent);

        });


    }

    private void setID(){
        imgGonder = findViewById(R.id.imgGonder);
        imgDetay = findViewById(R.id.imgDetay);
        imgYatir = findViewById(R.id.imgYatir);
        tvBakiye = findViewById(R.id.tvBakiye);
        tvpadaranumara = findViewById(R.id.materialTextView3);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferencesSet();
        tvpadaranumara.setText("Padara Numaran: "+hesapID.toString());
        tvBakiye.setText(hesapBakiye.toString()+"TL");

    }


    private void sharedPreferencesSet(){
        sharedPreferences = getSharedPreferences("skipPage", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        hesapBakiye = sharedPreferences.getString("hesapBakiye","");
        hesapID = sharedPreferences.getString("hesapID","");

    }
}
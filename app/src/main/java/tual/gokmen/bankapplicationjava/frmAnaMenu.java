package tual.gokmen.bankapplicationjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import org.w3c.dom.Text;

public class frmAnaMenu extends AppCompatActivity {

    private ShapeableImageView imgGonder, imgDetay, imgYatir;
    private MaterialTextView tvBakiye, tvpadaranumara;
    private String musteriID, musteriTC, musteriParola, musteriBakiye;
    private SharedPreferences sharedPreferences;
    private Bundle extras;
    private SharedPreferences.Editor editor;
    private String hesapBakiye, hesapID;
    private ConstraintLayout constHesaplar;
    private ImageView imgcc;
    private TextView tvHesaplar, tvKartlar, tvkullanablrbakiye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_ana_menu);
        extras = getIntent().getExtras();
        setID();
        sharedPreferencesSet();

        imgcc.setVisibility(View.GONE);
        tvBakiye.setText(hesapBakiye.toString() + " TL");
        tvpadaranumara.setText("Padara Numaran: " + hesapID.toString());
        imgcc.setAlpha(0.0f);
        clickFuncs();
    }

    private void clickFuncs() {
        imgGonder.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), frmParaGonderme.class);
            startActivity(intent);

        });

        tvKartlar.setOnClickListener(view -> {
            tvKartlar.setTextColor(getResources().getColor(R.color.white));
            tvHesaplar.setTextColor(getResources().getColor(R.color.grey));
            tvBakiye.setVisibility(View.GONE);
            tvpadaranumara.setVisibility(View.GONE);
            tvkullanablrbakiye.setVisibility(View.GONE);

            imgcc.animate().alpha(1.0f).setDuration(200);
            imgcc.setVisibility(View.VISIBLE);
        });
        tvHesaplar.setOnClickListener(view -> {
            tvKartlar.setTextColor(getResources().getColor(R.color.grey));
            tvHesaplar.setTextColor(getResources().getColor(R.color.white));
            tvBakiye.setVisibility(View.VISIBLE);
            tvpadaranumara.setVisibility(View.VISIBLE);
            tvkullanablrbakiye.setVisibility(View.VISIBLE);

            imgcc.setVisibility(View.GONE);
        });

    }

    private void setID() {
        imgGonder = findViewById(R.id.imgGonder);
        imgDetay = findViewById(R.id.imgDetay);
        imgYatir = findViewById(R.id.imgYatir);
        tvBakiye = findViewById(R.id.tvBakiye);
        tvpadaranumara = findViewById(R.id.materialTextView3);
        tvHesaplar = findViewById(R.id.tvHesaplar);
        tvKartlar = findViewById(R.id.tvKartlar);
        constHesaplar = findViewById(R.id.constHesaplar);
        imgcc = findViewById(R.id.imgCC);
        tvkullanablrbakiye = findViewById(R.id.tvkullanablrbakiye);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferencesSet();
        tvpadaranumara.setText("Padara Numaran: " + hesapID.toString());
        tvBakiye.setText(hesapBakiye.toString() + "TL");

    }


    private void sharedPreferencesSet() {
        sharedPreferences = getSharedPreferences("skipPage", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        hesapBakiye = sharedPreferences.getString("hesapBakiye", "");
        hesapID = sharedPreferences.getString("hesapID", "");

    }
}
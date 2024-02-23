package tual.gokmen.bankapplicationjava;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class frmLogin extends AppCompatActivity {

    private TextInputEditText txttc;
    private TextInputEditText txtsifre;
    private TextInputLayout txtlayoutsifre;
    private Button btn;
    private MaterialTextView textView;
    boolean deger = true;
    private String tcno,parola;
    private SqlConnection connection;
    public  String musteriID,musteriTC,musteriParola,musteriBakiye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_login);
        setID();
        txtsifre.setVisibility(View.GONE);
        txtlayoutsifre.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        connection = new SqlConnection(getApplicationContext());

        clickFunc();
        getTime();

    }
    private void setID(){
        txttc =(TextInputEditText) findViewById(R.id.txtTcNo);
        txtsifre = (TextInputEditText)findViewById(R.id.txtSifre);
        txtlayoutsifre = (TextInputLayout) findViewById(R.id.textinputlayout2);
        btn = (Button) findViewById(R.id.btnSign);
        textView = (MaterialTextView) findViewById(R.id.materialTextView);
    }
    private void clickFunc(){
        txttc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                txtsifre.setVisibility(View.GONE);
                txtlayoutsifre.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txttc.length() ==11){
                    txtsifre.setVisibility(View.VISIBLE);
                    txtlayoutsifre.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txtsifre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtsifre.length() == 6){
                    btn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn.setOnClickListener(view -> {
            String tcNo = txttc.getText().toString();
            String parola = txtsifre.getText().toString();

            try {
                if (connection.checkUser(tcNo, parola)) {
                    Toast.makeText(getApplicationContext(), "Giriş başarılı", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), frmAnaMenu.class);
                    checkUserFunc(tcNo,parola);
                    startActivity(intent);
                    intent.putExtra("musteriID",musteriID);
                    intent.putExtra("musteriTC",musteriTC);
                    intent.putExtra("musteriParola",musteriParola);
                    intent.putExtra("musteriBakiye",musteriBakiye);
                    startActivity(intent);
                    frmLogin.this.overridePendingTransition(
                            R.anim.animate_card_enter,
                            R.anim.animate_card_exit
                    );

                } else {
                    Toast.makeText(getApplicationContext(), "Giriş başarısız", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    private void getTime(){

        Calendar cal = Calendar.getInstance();
        int timeofDay = cal.get(Calendar.HOUR_OF_DAY);
        if (timeofDay >= 0 && timeofDay <12) {
            textView.setText(R.string.text1);
        } else if (timeofDay >= 12 && timeofDay <17) {
            textView.setText(R.string.text3);

        } else if (timeofDay >= 17 && timeofDay < 20) {
            textView.setText(R.string.text4);
        }
        else if (timeofDay >= 20 && timeofDay < 24){
            textView.setText(R.string.text2);
        }


    }
    private void checkUserFunc(String tc,String pass)  {
        try {
            ResultSet resultSet = connection.getUserInfo(tc,pass);

            if (resultSet.next()){
                musteriID = resultSet.getString("musteriID");
                musteriTC = resultSet.getString("musteriTC");
                musteriParola = resultSet.getString("musteriParola");
                musteriBakiye = resultSet.getString("musteriBakiye");
                String userInfo = "Müşteri ID: " + musteriID + "\nMüşteri TC: " + musteriTC + "\nMüşteri Parola: " + musteriParola + "\nMüşteri Bakiye: " + musteriBakiye;
                Toast.makeText(this, userInfo, Toast.LENGTH_SHORT).show();

            }
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}
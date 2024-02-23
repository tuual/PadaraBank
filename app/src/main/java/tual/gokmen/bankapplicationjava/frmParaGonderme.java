package tual.gokmen.bankapplicationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class frmParaGonderme extends AppCompatActivity {
    private EditText etpara,etno;
    String miktar,no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_para_gonderme);
        etpara = findViewById(R.id.etbakiye);
        etno = findViewById(R.id.etpadarano);

        SqlConnection connection = new SqlConnection(getApplicationContext());




    }
}
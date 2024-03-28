package tual.gokmen.bankapplicationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class frmParaGonderme extends AppCompatActivity {
    private EditText etpara, etno;
    private String miktar, no, musteribakiye, musteriid;
    private Button button;
    int bakiyeyeni;
    private SqlConnection connection;
    private SQLiteDatabase db;
    private String DbGelenBakiye;
    private int toplamGonderilecekPara;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String hesapBakiye, hesapID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frm_para_gonderme);
        etpara = findViewById(R.id.etbakiye);
        etno = findViewById(R.id.etpadarano);

        button = findViewById(R.id.btnGonder);

        connection = new SqlConnection(getApplicationContext());
        db = connection.getWritableDatabase();
        button.setOnClickListener(view -> {
            miktar = etpara.getText().toString().trim();
            no = etno.getText().toString().trim();
            try {
                paragonderme(no, Integer.parseInt(miktar));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        sharedPreferences = getSharedPreferences("skipPage", MODE_PRIVATE);

        hesapBakiye = sharedPreferences.getString("hesapBakiye", "");
        hesapID = sharedPreferences.getString("hesapID", "");

    }

    private void paragonderme(String id, int bakiye) throws SQLException {

        try {
            // Para Göndericek kişinin bakiyesi alındı
            bakiyeyeni = Integer.parseInt(hesapBakiye);

            //Eğer gönderilecek para bakiyenin üstündeyse fazla para gönderilemiyor.
            if (bakiyeyeni < bakiye) {
                // Uyarı mesajı
                Toast.makeText(this, "Bakiyenizin üstü bir para gönderilemez.", Toast.LENGTH_SHORT).show();
            } else {
                if (idChecked(id)){
                    paratransferi(id);
                    toplamGonderilecekPara = Integer.parseInt(DbGelenBakiye) + bakiye;
                    //Gönderilecek para
                    String query1 = "UPDATE musteri SET musteriBakiye='" + toplamGonderilecekPara + "' WHERE musteriID='" + id + "'";
                    Statement statement = connection.connection.createStatement();
                    statement.executeUpdate(query1);

                    // Hesap bakiyesini güncelleme
                    int sonpara = bakiyeyeni - bakiye;
                    String query2 = "UPDATE musteri SET musteriBakiye='" + sonpara + "' WHERE musteriID='" + hesapID + "'";
                    statement.executeUpdate(query2);

                    //Bakiye kaydetme
                    editor = sharedPreferences.edit();
                    editor.putString("hesapBakiye", String.valueOf(sonpara));
                    editor.apply();


                    Toast.makeText(this, "Para Gönderildi", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Böyle bir padara numarası yok.", Toast.LENGTH_SHORT).show();
                }
           
            }
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void paratransferi(String DbGelenID) {

        DbGelenBakiye = connection.getCustomerBalanceByID(DbGelenID);
        if (DbGelenBakiye != null) {

        } else {
            Toast.makeText(this, "Hata oluştu", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean idChecked(String id) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM musteri WHERE musteriID='" + id + "'";
        Statement statement = connection.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        int count = resultSet.getInt("count");
        return count > 0;
    }
}
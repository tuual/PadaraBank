package tual.gokmen.bankapplicationjava;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Struct;

import javax.xml.transform.Result;

    public class SqlConnection extends SQLiteOpenHelper {
        Connection connection;
        String username, password, ip, port, database;
        private Statement st;

        public SqlConnection(Context context) {
            super(context, "OnMuhasebe.db", null, 2);
            username = "biltekbilisim";
            database = "OnMuhasebe";
            password = "Bilisim20037816";
            port = "1433";
            ip = "213.254.137.231";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String connectionUrl = null;

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" + username + ";password=" + password + ";";
                connection = DriverManager.getConnection(connectionUrl);
                Toast.makeText(context, "Bağlandı", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.i("VeriTabanıHatası", e.getMessage());
            }
        }

        public void execSQL(String query, Context cont) throws SQLException {
            try {
                st = connection.createStatement();
                st.executeUpdate(query);
                Toast.makeText(cont, "bağlandı", Toast.LENGTH_SHORT).show();
            } catch (SQLException ex) {
                throw new SQLException(ex);
            }
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE musteri(musteriID INTEGER PRIMARY KEY AUTOINCREMENT, musteriTC nvarchar(15), musteriParola nvarchar(10), musteriBakiye INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS musteri");
            onCreate(db);
        }

        public boolean checkUser(String tcno, String parola) throws SQLException {
            try{
                st = connection.createStatement();
                String query = "Select * from musteri WHERE musteriTC='"+tcno+"' AND musteriParola='"+ parola + "'";
                ResultSet resultSet = st.executeQuery(query);
                return resultSet.next();
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }
        public ResultSet getUserInfo(String tcno,String parola) throws SQLException {
            try {
                String query = "SELECT * FROM musteri WHERE musteriTC= '"+tcno+"'AND musteriParola='"+parola+ "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                return resultSet;
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }





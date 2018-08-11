package lite.cipher.sunny.cipherdatabaseviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.sqlcipher.database.SQLiteDatabase;

/**
 * Created by sunny on 8/8/18.
 */

public class MainActivity extends AppCompatActivity {

    protected static final String DATABASE_NAME = "Cropin_Smartfarm.db";
    protected static final String DB_PASSWORD = "q/_a!?t:f86mW?MH!dp@";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SqlCipherHelper.initializeSQLCipher(this);

        SQLiteDatabase sqLiteDatabase = new DbHelper(this).getDb();

     /*   initializeSQLCipher();
        try {
            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(getDatabasePath(), DB_PASSWORD, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}

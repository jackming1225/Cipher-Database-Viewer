package lite.cipher.sunny.cipherdatabaseviewer;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * Created by sunny on 8/8/18.
 */

public class DbHelper extends SQLiteOpenHelper {


    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public DbHelper(Context context) {
        super(context, SqlCipherHelper.getDatabasePath(), null, 72);
        this.context = context;

    }

    public SQLiteDatabase getDb() {
        SqlCipherHelper.initializeSQLCipher(context);
        sqLiteDatabase = getReadableDatabase(SqlCipherHelper.getPWD());
        return sqLiteDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

package lite.cipher.sunny.cipherdatabaseviewer;

import android.content.Context;
import android.os.Environment;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteDatabaseHook;

import java.io.File;

/**
 * Created by sunny on 12/7/18.
 */

public class SqlCipherHelper {
    protected static final String DATABASE_NAME = "Cropin_Smartfarm.db";
    private static final String TAG = SqlCipherHelper.class.getSimpleName();
    private static SQLiteDatabaseHook keyHook = new SQLiteDatabaseHook() {
        private String licenseKey = BuildConfig.SQL_CIPHER_LICENSE;

        @Override
        public void preKey(SQLiteDatabase database) {
            database.rawExecSQL(String.format("PRAGMA cipher_license = '%s';", licenseKey));
        }

        public void postKey(SQLiteDatabase database) {
        }
    };

    private static SQLiteDatabaseHook wrapHook(final SQLiteDatabaseHook hook) {
        if (hook == null) {
            return keyHook;
        }
        return new SQLiteDatabaseHook() {
            @Override
            public void preKey(SQLiteDatabase database) {
                keyHook.preKey(database);
                hook.preKey(database);
            }

            @Override
            public void postKey(SQLiteDatabase database) {
                keyHook.postKey(database);
                hook.preKey(database);
            }
        };
    }

    public static String getPWD() {
        return BuildConfig.DB_PASSWORD;
    }


    public static String getDatabasePath() {
        return Environment.getExternalStorageDirectory() + "/" + DATABASE_NAME;
    }

    public static void initializeSQLCipher(Context context) {
        SQLiteDatabase.loadLibs(context);
        File databaseFile = new File(getDatabasePath());
        if (!databaseFile.exists()) {
            databaseFile.mkdirs();
            databaseFile.delete();
        }
        try {
            SQLiteDatabase.openOrCreateDatabase(databaseFile, getPWD(), null, keyHook);
        } catch (Exception e) {
//            Logger.logException(TAG, e);
            e.printStackTrace();
        }
    }
}
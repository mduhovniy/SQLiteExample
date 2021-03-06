package info.duhovniy.maxim.sqliteexample;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by maxduhovniy on 9/8/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cmd = "CREATE TABLE songs (_id INTEGER PRIMARY KEY " +
                ", song TEXT, singer TEXT, year INTEGER, band TEXT);";
        try {
            db.execSQL(cmd);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

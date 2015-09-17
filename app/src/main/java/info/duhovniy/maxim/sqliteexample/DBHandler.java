package info.duhovniy.maxim.sqliteexample;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by maxduhovniy on 9/8/15.
 */
public class DBHandler extends ListActivity {

    private DBHelper helper;

    public DBHandler(Context context) {

        helper = new DBHelper(context, "Hits.db", null, 1);
    }

    public boolean addSong(String song, String singer, int year, String band) {
        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put("song", song);
            values.put("singer", singer);
            values.put("year", year);
            values.put("band", band);
            db.insert("songs", null, values);
        } catch (SQLException e) {
            e.getMessage();
            return false;
        } finally {
            if (db.isOpen())
                db.close();
        }
        return true;
    }

    public int delSong(String song, String singer, String year, String band) {

        SQLiteDatabase db = helper.getWritableDatabase();
        int numRowsDeleted = 0;

        try {
            numRowsDeleted = db.delete("songs", "song=? OR singer=? OR year=? OR band=?",
                    new String[]{song, singer, year, band});
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (db.isOpen())
                db.close();
        }
        return numRowsDeleted;
    }

    public Cursor listSong() {

        Cursor cursor = null;
        SQLiteDatabase db = helper.getReadableDatabase();

        try {
            cursor = db.query("songs", null, null, null, null, null, null);
        } catch (SQLException e) {
            e.getMessage();
        }

        return cursor;

    }

}

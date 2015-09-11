package info.duhovniy.maxim.sqliteexample;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CursorAdapter;

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
            db.insertOrThrow("songs", null, values);
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

    public void listSong() {

        SQLiteDatabase db = helper.getWritableDatabase();

        try {
            Cursor cursor = db.rawQuery("SELECT song, singer, year, band FROM songs;", null);

            if (cursor != null) {

                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);

                // use a linear layout manager
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                mRecyclerView.setAdapter(new ClientCursorAdapter(this, R.layout.row, cursor, 0));

            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            if (db.isOpen())
                db.close();
        }
    }

}

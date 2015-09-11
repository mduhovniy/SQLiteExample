package info.duhovniy.maxim.sqliteexample;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by maxduhovniy on 9/11/15.
 */
public class ClientCursorAdapter extends ResourceCursorAdapter {

    public ClientCursorAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
/*        TextView id = (TextView) view.findViewById(R.id.idSong);
        id.setText(cursor.getString(cursor.getColumnIndex("_id")));*/

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
        TextView song = (TextView) view.findViewById(R.id.song);
        song.setText(cursor.getString(cursor.getColumnIndex("song")));

        TextView singer = (TextView) view.findViewById(R.id.singer);
        singer.setText(cursor.getString(cursor.getColumnIndex("singer")));

        TextView year = (TextView) view.findViewById(R.id.year);
        year.setText(cursor.getString(cursor.getColumnIndex("year")));

        TextView band = (TextView) view.findViewById(R.id.band);
        band.setText(cursor.getString(cursor.getColumnIndex("band")));

    }
}

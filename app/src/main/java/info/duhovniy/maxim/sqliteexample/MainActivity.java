package info.duhovniy.maxim.sqliteexample;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private DBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new DBHandler(MainActivity.this);

        final EditText song = (EditText) findViewById(R.id.editSong);
        final EditText singer = (EditText) findViewById(R.id.editSinger);
        final EditText year = (EditText) findViewById(R.id.editYear);
        final EditText band = (EditText) findViewById(R.id.editBand);

        song.setText("");
        singer.setText("");
        year.setText("2015");
        band.setText("");

        Button butAdd = (Button) findViewById(R.id.buttonAdd);
        Button butDel = (Button) findViewById(R.id.buttonDel);
        Button butView = (Button) findViewById(R.id.buttonView);
//        Button butCreate = (Button) findViewById(R.id.buttonCreate);
//        Button butDelete = (Button) findViewById(R.id.buttonDelete);

        final View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {

            }
        };

        final View coordinatorLayoutView = findViewById(R.id.snackbarPosition);

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(handler.addSong(String.valueOf(song.getText()),
                        String.valueOf(singer.getText()),
                        Integer.valueOf(String.valueOf(year.getText())),
                        String.valueOf(band.getText()))) {
                    Snackbar.make(coordinatorLayoutView, R.string.snackbar_row_added, Snackbar.LENGTH_LONG)
                        .setAction(R.string.snackbar_action, clickListener).show();
                }
            }
        });


        butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = handler.delSong(String.valueOf(song.getText()),
                        String.valueOf(singer.getText()),
                        String.valueOf(year.getText()),
                        String.valueOf(band.getText()));
                String message = String.valueOf(num) + " songs successfully deleted";
                Snackbar.make(coordinatorLayoutView, message, Snackbar.LENGTH_LONG)
                            .setAction(R.string.snackbar_action, clickListener).show();

            }
        });



        butView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = handler.listSong();

                String [] from = {"song", "singer", "year", "band"};
                int [] to = {R.id.song, R.id.singer, R.id.year, R.id.band};
                SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(MainActivity.this,
                        R.layout.row,cursor,from,to,0);
                ListView mListView = (ListView) findViewById(R.id.my_list_view);
                mListView.setAdapter(mAdapter);

            }
        });

 /*       butCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.listSong(String.valueOf(song.getText()),
                        String.valueOf(singer.getText()),
                        Integer.valueOf(String.valueOf(year.getText())),
                        String.valueOf(band.getText()));
            }
        });
*/
 /*       butDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.listSong(String.valueOf(song.getText()),
                        String.valueOf(singer.getText()),
                        Integer.valueOf(String.valueOf(year.getText())),
                        String.valueOf(band.getText()));
            }
        });*/

    }
}

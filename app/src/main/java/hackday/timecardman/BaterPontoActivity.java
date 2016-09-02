package hackday.timecardman;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import static hackday.timecardman.FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE;
import static hackday.timecardman.FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE;
import static hackday.timecardman.FeedReaderContract.FeedEntry.TABLE_NAME;
import static hackday.timecardman.FeedReaderContract.FeedEntry._ID;

public class BaterPontoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bater_ponto);
    }

    public void baterPonto(View view) {

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, Calendar.getInstance().getTime().toString());
        values.put(COLUMN_NAME_SUBTITLE, "teste2");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME, null, values);

        SQLiteDatabase db2 = mDbHelper.getWritableDatabase();

        String[] projection = {
                COLUMN_NAME_TITLE,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = _ID + " = ?";
        String[] selectionArgs = { "1" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                COLUMN_NAME_SUBTITLE + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        cursor.moveToFirst();
        String result = cursor.getString(
                cursor.getColumnIndexOrThrow(COLUMN_NAME_TITLE)
        );


        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

}
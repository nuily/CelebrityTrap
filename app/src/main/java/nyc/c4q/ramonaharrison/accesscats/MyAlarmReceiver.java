package nyc.c4q.ramonaharrison.accesscats;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;

import nyc.c4q.ramonaharrison.accesscats.model.Cat;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by huilin on 12/4/16.
 */

public class MyAlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "nyc.c4q.notificationdemo.alarm";
    private SQLiteDatabase db;

    // Triggered by the Alarm periodically (starts the notification service)
    @Override
    public void onReceive(Context context, Intent intent) {
        AnimalDatabaseHelper dbHelper = AnimalDatabaseHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();

        Long lastFed = Calendar.getInstance().getTimeInMillis();
        String catName = intent.getStringExtra("random name");
        addCat(new Cat(catName, lastFed));
        Intent i = new Intent(context, CatActivity.class);
        context.startService(i);
    }



    public void addCat(Cat cat) {
        cupboard().withDatabase(db).put(cat);
    }
}
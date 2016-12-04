package nyc.c4q.ramonaharrison.accesscats;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Random;

/**
 * Created by huilin on 12/4/16.
 */

public class MyNotificationService extends IntentService {

    // Used to name the worker thread, important only for debugging.
    private static final String SERVICE_NAME = "notification-service";

    // Must create a default constructor
    public MyNotificationService() {
        super(SERVICE_NAME);
    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // This describes what will happen when service is triggered, i.e. show a notification
        startPendingIntent(randomWord(5));
    }

    private void startPendingIntent(String randomWord) {
        int NOTIFICATION_ID = 555;

        // Define an intent to trigger when notification is selected (in this case to open an activity)
        Intent intent = new Intent(this, CatActivity.class);
        intent.putExtra("random name", randomWord);

        // Turn this into a PendingIntent
        int requestID = (int) System.currentTimeMillis(); // Unique requestID to differentiate between various notification with same notification ID
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // Cancel old intent and create new one
        PendingIntent pendingIntent = PendingIntent.getActivity(this, requestID, intent, flags);

        // Attach the pendingIntent to a new notification using setContentIntent
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_accessibility_black_24dp)
                .setContentTitle("Found a cat!")
                .setContentText("My name is " + randomWord)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // Hides the notification after its been selected
                .build();

        // Get the notification manager system service
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Setting a notification ID allows you to update the notification later on.
        notificationManager.notify(NOTIFICATION_ID, notification);


    }

    public static String randomWord(int length) {
        Random random = new Random();
        StringBuilder word = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            word.append((char) ('a' + random.nextInt(26)));
        }

        return word.toString();
    }
}
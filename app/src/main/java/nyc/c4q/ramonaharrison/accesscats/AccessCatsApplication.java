package nyc.c4q.ramonaharrison.accesscats;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by huilin on 11/20/16.
 */

public class AccessCatsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}

package dofam.in.bible;

import android.app.Activity;
import android.app.Application;

import dofam.in.bible.di.application.AppComponent;
import dofam.in.bible.di.application.AppModule;
import dofam.in.bible.di.application.DaggerAppComponent;


public class BibleApplication extends Application {

    private AppComponent appComponent;

    public static BibleApplication get(Activity activity){
        return (BibleApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

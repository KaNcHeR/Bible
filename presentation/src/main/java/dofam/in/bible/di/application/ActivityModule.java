package dofam.in.bible.di.application;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.dofam.data.repository.BookDataRepository;
import in.dofam.domain.repository.BookRepository;

@Module
public class ActivityModule {
    private final Context context;

    ActivityModule(Activity context){
        this.context = context;
    }

    @ApplicationScope
    @Provides
    public Context context(){ return context; }

}

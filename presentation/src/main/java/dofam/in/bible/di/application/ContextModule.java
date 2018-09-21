package dofam.in.bible.di.application;


import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationContext
    @ApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}

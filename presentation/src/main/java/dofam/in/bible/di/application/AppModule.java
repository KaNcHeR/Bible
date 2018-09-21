package dofam.in.bible.di.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dofam.in.bible.BibleApplication;
import dofam.in.bible.UIThread;
import in.dofam.data.executor.JobExecutor;
import in.dofam.data.repository.BookDataRepository;
import in.dofam.domain.executor.PostExecutionThread;
import in.dofam.domain.executor.ThreadExecutor;
import in.dofam.domain.repository.BookRepository;

@Module
public class AppModule {

    private final BibleApplication application;

    public AppModule(BibleApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    BookRepository provideUserRepository(BookDataRepository bookDataRepository) {
        return bookDataRepository;
    }
}

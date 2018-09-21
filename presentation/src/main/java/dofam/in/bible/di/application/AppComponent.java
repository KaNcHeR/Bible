package dofam.in.bible.di.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dofam.in.bible.di.main.MainComponent;
import dofam.in.bible.di.main.MainModule;
import in.dofam.domain.executor.PostExecutionThread;
import in.dofam.domain.executor.ThreadExecutor;
import in.dofam.domain.repository.BookRepository;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    BookRepository bookRepository();
}

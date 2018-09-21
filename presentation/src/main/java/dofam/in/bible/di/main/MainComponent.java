package dofam.in.bible.di.main;

import dagger.Component;
import dagger.Subcomponent;
import dofam.in.bible.di.application.AppComponent;
import dofam.in.bible.presentation.main.views.MainActivity;

@Component(dependencies = AppComponent.class, modules = MainModule.class)
@MainScope
public interface MainComponent {
    void inject(MainActivity mainActivity);
}

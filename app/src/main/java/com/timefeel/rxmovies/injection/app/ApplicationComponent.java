package com.timefeel.rxmovies.injection.app;

import android.content.Context;

import com.timefeel.rxmovies.api.ApiInterface;
import com.timefeel.rxmovies.injection.app.builder.MoviesApiServiceModule;
import com.timefeel.rxmovies.injection.app.builder.NetworkModule;
import com.timefeel.rxmovies.injection.app.builder.RxModule;
import com.timefeel.rxmovies.utils.rx.RxSchedulers;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by test on 01/05/2017.
 */

/**
 * https://google.github.io/dagger/users-guide
 * The @Inject and @Provides-annotated classes form a graph of objects,linked by their dependencies.
 * Calling code like an application’s main method or an Android Application accesses that graph via a well-defined set of roots.
 * In Dagger 2, that set is defined by an interface with methods that have no arguments and return the desired type.
 * By applying the @Component annotation to such an interface and passing the module types to the modules parameter,
 * Dagger 2 then fully generates an implementation of that contract.
 * **/

@Singleton
@Component(modules = {NetworkModule.class, ApplicationModule.class, MoviesApiServiceModule.class, RxModule.class})


public interface ApplicationComponent {

    // remove injection methods if downstream modules will perform injection
    // void inject(MainActivity activity);
    // void inject(MyFragment fragment);
    // void inject(MyService service);

    // downstream components need these exposed
    // the method name does not matter, only the return type
    Context getAppContext();
    RxSchedulers rxSchedulers();
    ApiInterface ApiService();

}

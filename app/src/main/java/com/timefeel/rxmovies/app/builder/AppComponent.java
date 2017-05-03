package com.timefeel.rxmovies.app.builder;

import com.timefeel.rxmovies.api.ApiInterface;
import com.timefeel.rxmovies.utils.rx.RxSchedulers;

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

@Component(modules = {NetworkModule.class, AppContextModule.class, MoviesApiServiceModule.class, RxModule.class})

@AppScope
public interface AppComponent {

    ApiInterface apiInterf();
    RxSchedulers rxSchedulers();


}

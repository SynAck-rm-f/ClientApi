package com.timefeel.rxmovies.utils.rx;

import rx.Scheduler;

/**
 * Created by test on 28/02/2017.
 */

public interface RxSchedulers {


    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();



}

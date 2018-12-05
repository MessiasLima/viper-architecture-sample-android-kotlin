package com.raywenderlich.chuckyfacts.presenter

import com.raywenderlich.chuckyfacts.SplashContract
import com.raywenderlich.chuckyfacts.view.activities.MainActivity

class SplashPresenter(var view: SplashContract.View?) : SplashContract.Presenter {
    override fun onViewCreated() {
        view?.getRouterInstance()?.navigateTo(MainActivity.TAG)
    }

    override fun onDestroy() {
        view = null
    }
}
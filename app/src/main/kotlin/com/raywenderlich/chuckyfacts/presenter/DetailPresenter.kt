package com.raywenderlich.chuckyfacts.presenter

import com.raywenderlich.chuckyfacts.DetailContract
import com.raywenderlich.chuckyfacts.entity.Joke
import com.raywenderlich.chuckyfacts.view.activities.MainActivity

class DetailPresenter(var view: DetailContract.View?): DetailContract.Presenter {

    override fun backButtonClicked() {
        view?.getRouterInstance()?.backTo(MainActivity.TAG)
    }

    override fun onViewCreated(joke: Joke) {
        view?.showJokeData(joke.id.toString(), joke.text)
    }

    override fun onDestroy() {
        view = null
    }
}
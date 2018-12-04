package com.raywenderlich.chuckyfacts.presenter

import com.raywenderlich.chuckyfacts.DetailContract
import com.raywenderlich.chuckyfacts.entity.Joke

class DetailPresenter(var view: DetailContract.View?): DetailContract.Presenter {

    override fun backButtonClicked() {
        view?.getRouterInstance()?.exit()
    }

    override fun onViewCreated(joke: Joke) {
        view?.showJokeData(joke.id.toString(), joke.text)
    }

    override fun onDestroy() {
        view = null
    }
}
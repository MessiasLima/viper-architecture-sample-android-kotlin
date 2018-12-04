package com.raywenderlich.chuckyfacts.presenter

import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raywenderlich.chuckyfacts.MainContract
import com.raywenderlich.chuckyfacts.entity.Joke
import com.raywenderlich.chuckyfacts.interactor.MainInteractor
import com.raywenderlich.chuckyfacts.view.activities.DetailActivity

class MainPresenter(private var view: MainContract.View?) : MainContract.Presenter, MainContract.InteractorOutput {



    var interactor: MainContract.Interactor? = MainInteractor()

    override fun listItemClicked(joke: Joke?) {
        view?.getRouterInstance()?.navigateTo(DetailActivity.TAG, joke)
    }

    override fun onViewCreated() {
        view?.showLoading()
        interactor?.loadJokesList { result ->
            when (result) {

                is Result.Failure -> {
                    this.onQueryError()
                }

                is Result.Success -> {
                    val jokesJsonObject = result.get().obj()
                    val type = object : TypeToken<List<Joke>>() {}.type
                    val jokesList: List<Joke> = Gson().fromJson(jokesJsonObject.getJSONArray("value").toString(), type)
                    this.onQuerySuccess(jokesList)
                }
            }
        }
    }

    override fun onDestroy() {
        view = null
        interactor = null
    }

    override fun onQuerySuccess(data: List<Joke>) {
        view?.hideLoading()
        view?.publishDataList(data)
    }

    override fun onQueryError() {
        view?.hideLoading()
        view?.showInfoMessage("An error has occurred on fetching data")
    }
}
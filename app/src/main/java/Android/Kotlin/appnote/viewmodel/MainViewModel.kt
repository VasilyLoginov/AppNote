package Android.Kotlin.appnote.viewmodel

import Android.Kotlin.appnote.data.model.Repository
import Android.Kotlin.appnote.ui.main.MainViewState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    private val viewStateLiveData:MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainViewState (Repository.getNotes())
    }

    fun viewState():LiveData<MainViewState> = viewStateLiveData

}
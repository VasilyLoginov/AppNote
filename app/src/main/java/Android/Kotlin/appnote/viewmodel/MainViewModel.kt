package Android.Kotlin.appnote.viewmodel

import Android.Kotlin.appnote.data.model.Note
import Android.Kotlin.appnote.data.model.Repository
import Android.Kotlin.appnote.ui.main.MainViewState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    private val viewStateLiveData:MutableLiveData<MainViewState> = MutableLiveData()

    init {

        Repository.getNotes().observeForever {
            viewStateLiveData.value =
                    viewStateLiveData.value?.copy(notes = it as MutableList<Note>) ?: MainViewState(it as MutableList<Note>)
        }
    }

    /* Repository.getNotes().observeForever { notes ->
            viewStateLiveData.value = viewStateLiveData.value?.copy(notes = notes)
                    ?: MainViewState(notes)
        }
    }
*/
    fun viewState():LiveData<MainViewState> = viewStateLiveData

}
package Android.Kotlin.appnote.viewmodel

import Android.Kotlin.appnote.data.model.Note
import Android.Kotlin.appnote.data.model.Repository
import androidx.lifecycle.ViewModel

class NoteViewModel (private val repository: Repository = Repository):ViewModel(){
    private var pendingNote: Note? = null
    fun saveChanges(note:Note){
        pendingNote = note
    }
    override fun onCleared(){
        if (pendingNote != null){
            repository.saveNote(pendingNote!!)
        }
    }
}
package Android.Kotlin.appnote.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import java.util.*

//import java.util.Collections.singletonList

object Repository {
    private var notesLiveData = MutableLiveData<List<Note>>()
    private var notes: MutableList<Note> = mutableListOf(
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    bodyNote = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.WHITE),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    bodyNote = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.BLUE),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    bodyNote = "Kotlin очень краткий, но при этом выразительный zpык",
                    color = Color.GREEN),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    bodyNote = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.PINK),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    bodyNote = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.RED),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    bodyNote = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.YELLOW),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    bodyNote = "Kotlin очень краткий, но при этом выразительный зык",
                    color = Color.VIOLET))

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> = notesLiveData //fun getNotes(): LiveData<MutableList<Note>> = notesLiveData

    fun saveNote(note: Note){
        addOrReplace(note)
        notesLiveData.value = notes
    }
    private fun addOrReplace(note: Note){
        for (i in 0 until notes.size){
          if (notes[i] == note){
              notes[i] = note                   /// notes.set(i,note) notes[i] = note
              return
          }
        }
       notes.add(note)
    }
}


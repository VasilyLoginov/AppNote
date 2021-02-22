package Android.Kotlin.appnote.ui.main

import Android.Kotlin.appnote.data.model.Note
import Android.Kotlin.appnote.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import Android.Kotlin.appnote.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var ui: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setSupportActionBar(ui.toolbar)//Не забудьте поменять в Стилях приложения тему на Theme.AppCompat.Light.NoActionBar

        viewModel = ViewModelProvider( this ). get (MainViewModel:: class .java )
        adapter = MainAdapter(object:  OnItemClickListener{
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        ui.mainRecycler.adapter = adapter

        viewModel.viewState().observe( this , Observer <MainViewState> { state ->
            state?.let { adapter.notes = it.notes}
        })

        ui.fab.setOnClickListener { openNoteScreen(null) }

    }
    private fun openNoteScreen(note: Note? = null) {
        startActivity(NoteActivity.getStartIntent(this,note))
    }
}


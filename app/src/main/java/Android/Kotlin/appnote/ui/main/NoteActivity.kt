package Android.Kotlin.appnote.ui.main

import Android.Kotlin.appnote.R
import Android.Kotlin.appnote.data.model.Color
import Android.Kotlin.appnote.data.model.Note
import Android.Kotlin.appnote.databinding.ActivityNoteBinding
import Android.Kotlin.appnote.viewmodel.NoteViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import android.os.Handler as Handler

private const val SAVE_DELAY = 2000L

class NoteActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_NOTE = "NoteActivity.extra.NOTE"

        fun getStartIntent(context: Context, note: Note?): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            return intent
        }
    }

    private var note: Note? = null
    private lateinit var ui: ActivityNoteBinding
    private lateinit var viewModel: NoteViewModel
    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //triggerSaveNote()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //
        }

        override fun afterTextChanged(s: Editable?) {
            triggerSaveNote() //
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(ui.root)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        setSupportActionBar(ui.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (note != null) {
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(note!!.lastChange)
        } else {
            getString(R.string.new_note_title)
        }
        initView()
    }

    private fun initView() {
        //  if (note != null ) {
        ui.titleEt.setText(note?.title ?: "")
        ui.bodyEt.setText(note?.bodyNote ?: "")
        val color = when (note?.color) {
            Color.WHITE -> R.color.color_white
            Color.VIOLET -> R.color.color_violet
            Color.YELLOW -> R.color.color_yello
            Color.RED -> R.color.color_red
            Color.PINK -> R.color.color_pink
            Color.GREEN -> R.color.color_green
            Color.BLUE -> R.color.color_blue
            else -> R.color.color_white
        }
        ui.toolbar.setBackgroundColor(resources.getColor(color))
        ui.titleEt.addTextChangedListener(textChangeListener)
        ui.bodyEt.addTextChangedListener(textChangeListener)
    }

    // }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun createNewNote(): Note = Note(
            UUID.randomUUID().toString(),
            ui.titleEt.text.toString(),
            ui.bodyEt.text.toString()
    )

    private fun triggerSaveNote() {
        //if (ui.titleEt.text!!.length < 3) return // if (ui.titleEt.text == null || ui.titleEt.text!!.length < 3) return

        Handler(Looper.getMainLooper()).postDelayed({ //Looper.getMainLooper()
            note = note?.copy(
                    title = ui.titleEt.text.toString(),
                    bodyNote = ui.bodyEt.text.toString(),
                    lastChange = Date()
            ) ?: createNewNote()
            if (note != null) viewModel.saveChanges(note!!)

        }, SAVE_DELAY)


        /*Handler(Looper.getMainLooper()).postDelayed(object : Runnable { //Looper.getMainLooper()
            override fun run() {
                note = note?.copy(title = ui.titleEt.text.toString(),
                                  bodyNote = ui.bodyEt.text.toString(),
                                  lastChange = Date()
                ) ?: createNewNote()
                if (note != null) viewModel.saveChanges(note!!)
            }
        }, SAVE_DELAY)
    }

     */

    }
}
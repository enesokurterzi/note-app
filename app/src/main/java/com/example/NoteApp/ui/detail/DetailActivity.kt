package com.example.NoteApp.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.NoteApp.R
import com.example.NoteApp.configs.Util
import com.example.NoteApp.models.Note
import com.example.NoteApp.services.DB
import com.example.NoteApp.ui.main.MainActivity

class DetailActivity : AppCompatActivity() {
    lateinit var txtDetailTitle: TextView
    lateinit var txtDetailDate: TextView
    lateinit var txtDetailContent: TextView
    lateinit var note: Note
    lateinit var db: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
        setView()
    }

    private fun initView() {
        txtDetailTitle = findViewById(R.id.txtDetailTitle)
        txtDetailDate = findViewById(R.id.txtDetailDate)
        txtDetailContent = findViewById(R.id.txtDetailContent)
        note = Util.choosen!!
    }

    private fun setView() {
        txtDetailTitle.text = note.title
        txtDetailDate.text = note.date
        txtDetailContent.text = note.content
        db = DB(this)
    }

    fun btnDeleteOnClick(view: View) {
        db.deleteNote(note.id)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
package com.example.NoteApp.ui.main

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.example.NoteApp.R
import com.example.NoteApp.configs.Util
import com.example.NoteApp.models.Note
import com.example.NoteApp.services.DB
import com.example.NoteApp.ui.detail.DetailActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var btnSave: Button
    lateinit var txtTitle: TextInputEditText
    lateinit var txtContent: TextInputEditText
    lateinit var noteListView: ListView
    lateinit var adapter: MainAdapter
    lateinit var allNote: List<Note>
    lateinit var db: DB
    private var selectDate = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        adapterSet()

        noteListView.setOnItemClickListener { adapterView, view, i, l ->
            Util.choosen = allNote[i]
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun initView() {
        btnSave = findViewById(R.id.btnSave)
        txtTitle = findViewById(R.id.txtTitle)
        txtContent = findViewById(R.id.txtContent)
        noteListView = findViewById(R.id.noteListView)
        db = DB(this)
    }

    private fun adapterSet() {
        allNote = db.allNote()
        adapter = MainAdapter(this@MainActivity, allNote)
        noteListView.adapter = adapter
    }

    fun btnDateOnClick(view: View) {
        val calender = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, i, i2, i3 ->
                val month = if (i2 + 1 < 10) "0${i2 + 1}" else "${i2 + 1}"
                selectDate = "$i3.$month.$i"
            },
            calender.get(Calendar.YEAR),
            calender.get(Calendar.MONTH),
            calender.get(Calendar.DAY_OF_MONTH),
        )
        datePickerDialog.show()
    }
    fun btnSaveOnClick(view: View) {
        val title = txtTitle.text.toString()
        val content = txtContent.text.toString()
        if (selectDate != "" && title.isNotEmpty() && content.isNotEmpty()) {
            db.addNote(title, content, selectDate)
            adapterSet()
            inputReset()
        }else {
            Toast.makeText(this, "Please check your inputs!", Toast.LENGTH_LONG).show()
        }
    }



    private fun inputReset() {
        selectDate = ""
        txtTitle.setText("")
        txtContent.setText("")
        noteListView.requestFocus()
    }


}
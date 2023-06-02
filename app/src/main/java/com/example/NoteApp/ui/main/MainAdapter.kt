package com.example.NoteApp.ui.main

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.NoteApp.R
import com.example.NoteApp.models.Note

class MainAdapter(private val context: Activity, private val list: List<Note>) :
    ArrayAdapter<Note>(context, R.layout.note_list_item, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.note_list_item, null, true)

        val txtListItemTitle = rootView.findViewById<TextView>(R.id.txtListItemTitle)

        val note = list[position]

        txtListItemTitle.text = note.title

        return rootView
    }
}
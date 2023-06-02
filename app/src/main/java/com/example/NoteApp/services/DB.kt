package com.example.NoteApp.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.NoteApp.models.Note

class DB(context: Context) : SQLiteOpenHelper(context, DBName, null, Version  ) {

    companion object {
        private val DBName = "notes.db"
        private val Version = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val noteTable = "CREATE TABLE \"note\" (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"title\"\tTEXT,\n" +
                "\t\"content\"\tTEXT,\n" +
                "\t\"date\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                ");"
        p0?.execSQL(noteTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val noteTableDrop = "DROP TABLE IF EXISTS note"
        p0?.execSQL(noteTableDrop)
        onCreate(p0)
    }


    fun addNote( title:String, content: String, date: String) : Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("title", title)
        values.put("content", content)
        values.put("date", date)

        val effectRow = db.insert("note", null, values)
        db.close()
        return effectRow
    }

    fun deleteNote(id: Int) : Int {
        val db = this.writableDatabase
        val status = db.delete("note", "id = $id", null )
        db.close()
        return status
    }

    fun updateNote(title:String, content: String, id: Int) : Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("title", title)
        contentValues.put("content", content)
        val status = db.update("note", contentValues, "nid = $id", null)
        db.close()
        return status
    }

    fun allNote() : List<Note> {
        val db = this.readableDatabase
        val arr = mutableListOf<Note>()
        
        val cursor = db.query("note",null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val nid = cursor.getInt(0)
            val title = cursor.getString(1)
            val content = cursor.getString(2)
            val date = cursor.getString(3)
            val note = Note(nid, title, content, date)
            arr.add(note)
        }
        db.close()
        return arr
    }

}
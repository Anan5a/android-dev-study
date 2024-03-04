package com.sayempro.noteapproomdatabase.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sayempro.noteapproomdatabase.Model.Note
import com.sayempro.noteapproomdatabase.R
import com.sayempro.noteapproomdatabase.View.MainActivity
import com.sayempro.noteapproomdatabase.View.UpdateActivity

class NoteAdapter(val activity:MainActivity):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    var notes:List<Note> = ArrayList()
    class NoteViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val textViewNoteTitle: TextView = itemView.findViewById(R.id.textViewNoteTitle)
        val textViewNoteText: TextView = itemView.findViewById(R.id.textViewNoteText)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.textViewNoteText.text = notes[position].description
        holder.textViewNoteTitle.text = notes[position].title

        holder.cardView.setOnClickListener {
            val intent = Intent(activity, UpdateActivity::class.java)
            intent.putExtra("currentTitle", notes[position].title)
            intent.putExtra("currentDesc", notes[position].description)
            intent.putExtra("currentId", notes[position].id)
            //activity result launcher
            activity.updateActivityResultLauncher.launch(intent)
        }

    }

    fun setNote(myNotes: List<Note>) {
        notes = myNotes
        notifyDataSetChanged()
    }
    fun getNote(position: Int): Note {
        return notes[position]
    }
}
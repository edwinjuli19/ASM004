package id.ac.polman.astra.afm004.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import id.ac.polman.astra.afm004.R

class NotesFragment : DialogFragment() {

    private lateinit var editTextNotes: EditText
    private lateinit var saveButton: Button
    private var listener: OnNotesSavedListener? = null

    interface OnNotesSavedListener {
        fun onNotesSaved(notes: String, notes_type: String)
    }

    fun setOnNotesSavedListener(listener: OnNotesSavedListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        // Initialize EditText and Button
        editTextNotes = view.findViewById(R.id.notes_edit_text)
        saveButton = view.findViewById(R.id.save_button)

        // Retrieve the passed argument and set it to the EditText
        val currentNotes = arguments?.getString("current_notes")
        editTextNotes.setText(currentNotes)
        val notesType = arguments?.getString("notes_type").toString()

        // Set up the save button click listener
        saveButton.setOnClickListener {
            val notes = editTextNotes.text.toString()
            // Send the notes back to the parent fragment
            listener?.onNotesSaved(notes, notesType)
            dismiss()  // Close the fragment
        }

        return view
    }
}
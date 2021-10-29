package site.madcat.appnotes.UI

import android.content.Context
import android.widget.EditText
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.mlkit.vision.text.Text
import site.madcat.appnotes.R
import site.madcat.appnotes.UI.EditNoteFragment
import site.madcat.appnotes.domain.Note

class EditNoteFragment : Fragment() {
    private var controller: Controller? = null //для того чтоб убить в onDestroy
    private lateinit var titleEditText: EditText
    private lateinit var detailEditText: EditText
    private lateinit var saveChangeButton: Button

    internal var id = 0
    private var oldTitle: String? = null
    private var oldDetail: String? = null


    internal interface Controller {
        fun loadList()
        val screenOrientation: Boolean
        fun refreshAdapter()
        fun editNote(id: Int, title: String?, detail: String?)
        fun addNewNote(title: String?, detail: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Controller) {
            controller = context
        } else {
            //todo
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialsView(view)
        params
        saveChangeButton.setOnClickListener { v: View? ->
            if ((titleEditText.text.isNotEmpty()) or (detailEditText.text.isNotEmpty())) {

                if ((requireActivity() as ListActivity).newRecord == true) {
                    controller!!.addNewNote(
                        titleEditText.text.toString(),
                        detailEditText.text.toString()
                    )
                    (requireActivity() as ListActivity).newRecord = false
                    clearView()
                } else {
                    if ((oldTitle != titleEditText.text.toString()) or (oldTitle != titleEditText.text.toString())) {
                        controller!!.editNote(
                            id,
                            titleEditText.text.toString(),
                            detailEditText.text.toString()
                        )
                    }
                }
                if (controller!!.screenOrientation == true) {
                    controller!!.loadList()
                }
            }
        }
    }

    override fun onDestroy() {
        controller = null
        super.onDestroy()
    }

    val params: Unit
        get() {
            val argument = arguments
            if (argument != null) {
                val note = argument.getSerializable(Note::class.java.simpleName) as Note?
                (requireActivity() as ListActivity).newRecord = false
                getNoteFromList(note)
            } else {
                (requireActivity() as ListActivity).newRecord = true
            }
        }

    private fun clearView() {
        titleEditText.text = null
        detailEditText.text = null
    }

    private fun initialsView(view: View) {
        titleEditText = view.findViewById(R.id.title_edit_text)
        detailEditText = view.findViewById(R.id.body_edit_text)
        saveChangeButton = view.findViewById(R.id.save_change_button)

    }

    private fun getNoteFromList(note: Note?) {
        if (note != null) {
            oldTitle = note.title
            oldDetail = note.noteDetail
            titleEditText.setText(note.title)
            detailEditText.setText(note.noteDetail)
            id = note.id
        }
    }

    fun setInputArgumentsNoteFrames(note: Note?): EditNoteFragment {
        val editNoteFragment = EditNoteFragment()
        val bundle = Bundle()
        bundle.putSerializable(Note::class.java.simpleName, note)
        editNoteFragment.arguments = bundle
        return editNoteFragment
    }
}
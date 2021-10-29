package site.madcat.appnotes.UI

import android.content.Context
import site.madcat.appnotes.domain.NotesRepo
import androidx.recyclerview.widget.RecyclerView
import site.madcat.appnotes.UI.NotesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import site.madcat.appnotes.R
import androidx.recyclerview.widget.LinearLayoutManager
import site.madcat.appnotes.UI.NotesAdapter.onItemClickListener
import site.madcat.appnotes.domain.Note

class ListFragment : Fragment() {
    private lateinit var listRepo: NotesRepo
    private lateinit var recyclerView: RecyclerView
    private val adapter = NotesAdapter()
    private var controller: Controller? = null //для того чтоб убить в onDestroy

    internal interface Controller {
        fun loadNote(note: Note?)
        fun addNewNote(title: String?, detail: String?)
        val repo: NotesRepo?
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Controller) {
            controller = context
        } else {
            //todo
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        initData()
    }

    override fun onDestroy() {
        controller = null
        super.onDestroy()
    }

    fun initData() {
        listRepo = controller!!.repo!!
        initRecyclerView()
    }

    fun initRecyclerView() {
        recyclerView = requireActivity().findViewById(R.id.recycler_view)
        recyclerView.setLayoutManager(LinearLayoutManager(context))
        recyclerView.setAdapter(adapter)
        adapter.setData(listRepo.notes)
        adapter.setOnItemClickListener { note: Note -> onItemClick(note) }
    }

    fun refreshAdapter() {
        adapter.setData(listRepo.notes)
    }

    private fun onItemClick(note: Note) {
        controller?.loadNote(note)
    }
}
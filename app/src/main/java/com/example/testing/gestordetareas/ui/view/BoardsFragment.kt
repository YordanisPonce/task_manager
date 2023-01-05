package com.example.testing.gestordetareas.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.gestordetareas.data.model.DashboardModel
import com.example.testing.gestordetareas.databinding.FragmentBoardsBinding
import com.example.testing.gestordetareas.ui.home.HomeFragment
import com.example.testing.gestordetareas.ui.home.HomeViewModel
import com.example.testing.gestordetareas.ui.view.Adapter.DashboardRecyclerAdapter
import com.example.testing.gestordetareas.ui.view.interfaces.onChange

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BoardsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BoardsFragment : Fragment(), onChange {
    var adp: DashboardRecyclerAdapter? = null
    private var _binding: FragmentBoardsBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var items = mutableListOf<DashboardModel>()
        adp = DashboardRecyclerAdapter(items, this, homeViewModel)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoardsBinding.inflate(inflater, container, false)
        homeViewModel.board.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adp!!.addBoard(it)
                Log.i("Element", it.title)
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.empty.observe(viewLifecycleOwner, Observer {
            binding.notFoundSection.isVisible = it
        })
        binding!!.apply {
            recycler.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = adp
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (dy > 0) {
                            (parentFragment as HomeFragment).getFab().hide()

                        } else {
                            (parentFragment as HomeFragment).getFab().show()
                            super.onScrolled(recyclerView, dx, dy)
                        }
                    }
                })
            }
            var empty = this@BoardsFragment.adp!!.getElements().isEmpty()
            binding.notFoundSection.isVisible = empty
        }
    }

    override fun onPause() {
        super.onPause()
        (parentFragment as HomeFragment).getFab().show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun test(it: MutableList<DashboardModel>) {
        (parentFragment as HomeFragment).getViewModel().setValues(it)
    }

    override fun onDelete(position: Int) {
        this.homeViewModel.notifyElementDeleted(position)
    }


}
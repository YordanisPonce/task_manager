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
import com.example.testing.gestordetareas.databinding.FragmentFavoriteBoardsBinding
import com.example.testing.gestordetareas.ui.home.HomeFragment
import com.example.testing.gestordetareas.ui.home.HomeViewModel
import com.example.testing.gestordetareas.ui.view.Adapter.DashboardRecyclerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteBoards.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoriteBoards : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentFavoriteBoardsBinding? = null
    private val binding get() = _binding!!
    private var adp: DashboardRecyclerAdapter? = null
    private var items: MutableList<DashboardModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBoardsBinding.inflate(inflater, container, false)
        homeViewModel!!.elements.observe(viewLifecycleOwner, Observer {
            binding.notFoundSection.isVisible = it.isEmpty()
             adp!!.setElements(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        items = mutableListOf()
        adp = DashboardRecyclerAdapter(items!!)

        binding.recycler.apply {
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


    }


    override fun onPause() {
        super.onPause()
        (parentFragment as HomeFragment).getFab().show()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoriteBoards.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoriteBoards().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}
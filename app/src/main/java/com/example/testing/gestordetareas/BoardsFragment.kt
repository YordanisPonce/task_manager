package com.example.testing.gestordetareas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testing.gestordetareas.data.model.DashboardModel
import com.example.testing.gestordetareas.databinding.FragmentBoardsBinding
import com.example.testing.gestordetareas.ui.view.Adapter.DashboardRecyclerAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BoardsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BoardsFragment : Fragment() {
    private var adp: DashboardRecyclerAdapter? = null
    private var _binding: FragmentBoardsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var items = arrayListOf(
            DashboardModel("Ha'Bici", "Roberto Espi"),
            DashboardModel("CubanTechJobs", "Yordanis"),
            DashboardModel("Know", "Esteban"),
            DashboardModel("Taste.It", "Pefis"),
        )
        adp = DashboardRecyclerAdapter(items)
        binding!!.apply {
            recycler.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = adp
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
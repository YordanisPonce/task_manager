package com.example.testing.gestordetareas.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.testing.gestordetareas.R
import com.example.testing.gestordetareas.databinding.FragmentHomeBinding
import com.example.testing.gestordetareas.ui.view.Adapter.PagerAdapter
import com.example.testing.gestordetareas.ui.view.HomeActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private var viewPager2: ViewPager2? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.board.observe(viewLifecycleOwner, Observer {
            if(it != null){
                //Log.i("Element",it.title)
            }

        })
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var items =
            arrayOf(resources.getString(R.string.boards), resources.getString(R.string.favorites))
        viewPager2 = binding.vp2
        viewPager2!!.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabs, viewPager2!!) { tab, position ->
            tab.text = items[position]
        }.attach()

        viewPager2!!.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                (context as HomeActivity).animateFab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                //   fab.show()

            }
        })
    }

    fun getFab(): FloatingActionButton =
        (context as HomeActivity).findViewById(R.id.fb)


    fun getViewModel() = homeViewModel
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.testing.gestordetareas.ui.view.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testing.gestordetareas.BoardsFragment
import com.example.testing.gestordetareas.FavoriteBoards
import java.lang.ref.WeakReference

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private var items = 2
    private var fragments = arrayListOf<Fragment>()
    override fun getItemCount() = items

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BoardsFragment()
            else -> FavoriteBoards()
        }
    }
}
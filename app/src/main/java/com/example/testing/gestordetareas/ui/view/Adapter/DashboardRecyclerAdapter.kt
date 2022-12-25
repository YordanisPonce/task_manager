package com.example.testing.gestordetareas.ui.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.gestordetareas.R
import com.example.testing.gestordetareas.data.model.DashboardModel
import com.example.testing.gestordetareas.databinding.DashboardLayoutBinding

class DashboardRecyclerAdapter(private var boards: List<DashboardModel>) :
    RecyclerView.Adapter<DashboardRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.dashboard_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(boards[position])
    }

    override fun getItemCount(): Int {
        return this.boards.size
    }

    inner class ViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        private val binding by lazy { DashboardLayoutBinding.bind(v) }

        fun bind(dashboardModel: DashboardModel) {

            binding.apply {
                author.text = dashboardModel.author
                title.text = dashboardModel.title
                heart.tag = R.drawable.ic_heart1
                heart.setOnClickListener {
                    heart.apply {
                        var t = heart.tag as Int
                        if (t == R.drawable.ic_heart1) {
                            setImageResource(R.drawable.ic_heart2)
                            tag = R.drawable.ic_heart2
                            dashboardModel.favorite = true
                        } else {
                            setImageResource(R.drawable.ic_heart1)
                            tag = R.drawable.ic_heart1
                            dashboardModel.favorite = false
                        }


                    }

                }

            }
        }

    }

    fun getFavorites(): List<DashboardModel> {
        return this.boards.filter { it.favorite }
    }


}

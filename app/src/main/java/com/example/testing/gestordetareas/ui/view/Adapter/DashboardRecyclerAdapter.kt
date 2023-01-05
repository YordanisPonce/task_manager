package com.example.testing.gestordetareas.ui.view.Adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.gestordetareas.R
import com.example.testing.gestordetareas.data.model.DashboardModel
import com.example.testing.gestordetareas.databinding.DashboardLayoutBinding
import com.example.testing.gestordetareas.ui.home.HomeViewModel
import com.example.testing.gestordetareas.ui.view.interfaces.onChange

class DashboardRecyclerAdapter(
    private var boards: MutableList<DashboardModel>,
    private var change: onChange? = null,
    private var viewModel: HomeViewModel? = null
) :
    RecyclerView.Adapter<DashboardRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.dashboard_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(boards[position], position) {
            return@bind getFavorites() as MutableList<DashboardModel>
        }

    }

    override fun getItemCount(): Int {
        return this.boards.size
    }

    inner class ViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        private val binding by lazy { DashboardLayoutBinding.bind(v) }

        fun bind(
            dashboardModel: DashboardModel,
            position: Int,
            f: () -> MutableList<DashboardModel>
        ) {
            binding.apply {
                author.text = dashboardModel.author
                title.text = dashboardModel.title
                if (dashboardModel.photo != null) {
                    image.setImageURI(dashboardModel.photo)
                }
                if (change != null) {
                    heart.tag = R.drawable.ic_heart1
                    heart.setOnClickListener {
                        heart.apply {
                            var t = heart.tag as Int
                            if (t == R.drawable.ic_heart1) {
                                setImageResource(R.drawable.ic_heart2)
                                tag = R.drawable.ic_heart2
                                dashboardModel.favorite = true
                                showToast()
                            } else {
                                setImageResource(R.drawable.ic_heart1)
                                tag = R.drawable.ic_heart1
                                dashboardModel.favorite = false
                            }
                        }
                        change!!.test(f())

                    }
                    itemView.setOnLongClickListener {
                        var popupMenu = PopupMenu(itemView.context, itemView)
                        popupMenu.inflate(R.menu.board_menu)
                        popupMenu.show()
                        popupMenu.setOnMenuItemClickListener {
                            when (it.itemId) {
                                R.id.delete -> {
                                    AlertDialog.Builder(itemView.context).apply {
                                        setMessage(itemView.resources.getString(R.string.question))
                                        setNegativeButton(
                                            itemView.resources.getString(R.string.cancel),
                                            null
                                        )
                                        setPositiveButton(
                                            itemView.resources.getString(R.string.accept),
                                            DialogInterface.OnClickListener { dialogInterface, i ->
                                                change!!.onDelete(position)
                                                deleteBoard(position)
                                            })
                                        show()
                                    }

                                }
                                R.id.details -> {
                                    var builder =
                                        androidx.appcompat.app.AlertDialog.Builder(itemView.context)
                                    var dialog = builder.create()
                                    dialog.apply {
                                        var view =
                                            layoutInflater.inflate(R.layout.details_dialog, null)
                                        setView(view)
                                        var back = ColorDrawable(Color.TRANSPARENT)
                                        var inset = InsetDrawable(back, 100, 0, 100, 0)
                                        this.window!!.setBackgroundDrawable(inset)
                                        view.findViewById<AppCompatTextView>(R.id.details_tv).text =
                                            dashboardModel.description
                                        view.findViewById<AppCompatImageView>(R.id.btnclose)
                                            .setOnClickListener {
                                                this.dismiss()
                                            }
                                        view.findViewById<AppCompatButton>(R.id.btnAccept)
                                            .setOnClickListener {
                                                this.dismiss()
                                            }
                                        show()
                                    }
                                }
                            }
                            true
                        }
                        true
                    }
                } else {
                    heart.isVisible = false
                }

            }
        }

        private fun showToast() {
            Toast.makeText(
                itemView.context,
                itemView.resources.getString(R.string.favorites_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getFavorites() = this.boards.filter { it.favorite }
    fun setElements(it: MutableList<DashboardModel>) {
        boards = it
        notifyDataSetChanged()
    }

    fun getElements() = boards

    fun addBoard(board: DashboardModel) {
        this.boards.add(board)
        notifyDataSetChanged()
    }

    private fun deleteBoard(position: Int) {
        this.boards.removeAt(position)
        notifyDataSetChanged()
        viewModel!!.empty.postValue(this.boards.isEmpty())
    }
}

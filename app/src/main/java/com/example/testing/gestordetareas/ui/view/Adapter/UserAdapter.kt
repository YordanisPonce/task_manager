package com.example.testing.gestordetareas.ui.view.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.gestordetareas.R
import com.example.testing.gestordetareas.data.model.User
import com.example.testing.gestordetareas.databinding.UserLayoutBinding

class UserAdapter(private var context: Context, private var users: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    inner class ViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {
        private val binding by lazy { UserLayoutBinding.bind(v) }

        fun bind(user: User) {
            binding.username.text = user.username
            binding.btnfollow.setOnClickListener {
                Toast.makeText(
                    context,
                    "Esperando respuesta de ${user.username} con id ${user.id}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    "Esperando respuesta de ${user.username} con id ${user.id}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(context).inflate(R.layout.user_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}
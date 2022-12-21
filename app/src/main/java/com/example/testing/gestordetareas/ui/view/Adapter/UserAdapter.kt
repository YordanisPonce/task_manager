package com.example.testing.gestordetareas.ui.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.gestordetareas.R
import com.example.testing.gestordetareas.data.model.User
import com.example.testing.gestordetareas.databinding.UserLayoutBinding

class UserAdapter(private var users: List<User>, private var snackbar: (message: String) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    inner class ViewHolder(private var v: View) : RecyclerView.ViewHolder(v) {
        private val binding by lazy { UserLayoutBinding.bind(v) }

        fun bind(user: User, users: ArrayList<User>) {
            binding.username.text = user.username
            binding.deleteUser.setOnClickListener {
                users.remove(users.find { user.id == it.id })
                notifyDataSetChanged()
            }
            binding.btnfollow.setOnClickListener {
                var tv = it as AppCompatTextView
                tv.apply {
                    when (compoundDrawables[2]) {
                        null ->
                            setCompoundDrawablesRelativeWithIntrinsicBounds(
                                null,
                                null,
                                ContextCompat.getDrawable(this.context, R.drawable.ic_follow_icon),
                                null
                            )


                        else -> {
                            setCompoundDrawablesRelativeWithIntrinsicBounds(
                                ContextCompat.getDrawable(this.context, R.drawable.ic_following),
                                null,
                                null,
                                null
                            )
                            snackbar("${resources.getString(R.string.follow_message)} ${user.username}")
                        }

                    }

                }

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position], users as ArrayList<User>)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }
}
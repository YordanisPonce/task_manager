package com.example.testing.gestordetareas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.gestordetareas.data.model.User
import com.example.testing.gestordetareas.databinding.ActivityFriendsBinding
import com.example.testing.gestordetareas.ui.view.Adapter.UserAdapter

class FriendsActivity : AppCompatActivity() {
    private lateinit var users: MutableList<User>
    private val binding by lazy { ActivityFriendsBinding.inflate(layoutInflater) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var useradapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            setSupportActionBar(toolbar)
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        users = mutableListOf()

        users.add(User(2, "Pepe", "yordisponce@gmail.com", null, 2))
        users.add(User(1, "Yorrdanis", "yordisponce@gmail.com", null, 2))

        useradapter = UserAdapter(this, users)
        var lLayout = LinearLayoutManager(this)
        val decorator = DividerItemDecoration(this, lLayout.orientation)
        recyclerView = binding.users
        recyclerView.apply {
            layoutManager = lLayout
            adapter = useradapter
            addItemDecoration(decorator)
        }

    }
}
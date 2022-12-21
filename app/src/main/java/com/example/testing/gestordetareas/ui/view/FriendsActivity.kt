package com.example.testing.gestordetareas.ui.view

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.gestordetareas.R
import com.example.testing.gestordetareas.data.model.User
import com.example.testing.gestordetareas.databinding.ActivityFriendsBinding
import com.example.testing.gestordetareas.ui.view.Adapter.UserAdapter
import com.google.android.material.snackbar.Snackbar

class FriendsActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
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
        users.add(User(3, "Alberto", "yordisponce@gmail.com", null, 2))
        users.add(User(4, "Francis", "yordisponce@gmail.com", null, 2))
        users.add(User(5, "Cuki", "yordisponce@gmail.com", null, 2))
        users.add(User(6, "Jennifer", "yordisponce@gmail.com", null, 2))

        useradapter = UserAdapter(users) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setBackgroundTintList(getColorStateList(R.color.blue_200))
                    setTextColor(getColorStateList(R.color.white))
                }
                setAction("Dismiss") {
                    dismiss()
                }
                show()

            }
        }
        var lLayout = LinearLayoutManager(this)
        val decorator = DividerItemDecoration(this, lLayout.orientation)
        recyclerView = binding.users
        recyclerView.apply {
            layoutManager = lLayout
            adapter = useradapter
            addItemDecoration(decorator)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        var searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(text: String?): Boolean {
        if (!text.isNullOrEmpty()) {
            var newUsers = users.filter { text.lowercase() in it.username.lowercase() }
            useradapter.setUsers(newUsers)
        } else {
            useradapter.setUsers(users)
        }
        return false
    }

}
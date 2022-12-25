package com.example.testing.gestordetareas.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testing.gestordetareas.R
import com.example.testing.gestordetareas.databinding.ActivityHomeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private var currenTime = 0L
    private var time = 2000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        binding.appBarHome.fab.setOnClickListener { view ->
            var currentItem = navView.checkedItem!!.itemId
            when (currentItem) {
                R.id.nav_home -> startAction(s = "This is the home page", 1)
                R.id.nav_gallery -> startAction(s = "This is the task page", 2)
                R.id.nav_slideshow -> startAction("This is the friends page", 3)
            }
        }
    }

    private fun startAction(s: String?, action: Int = 0) {


        when (action) {
            1 -> {
                var dialog = BottomSheetDialog(this)
                dialog.apply {
                    var v = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
                    setContentView(v)
                    show()
                }
            }
            2 -> Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
            3 -> startActivity(Intent(this, FriendsActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    @SuppressLint("ResourceAsColor")
    override fun onBackPressed() {
        if (time + currenTime > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            currenTime = System.currentTimeMillis()
            Snackbar.make(
                binding.appBarHome.rootElement,
                "Pulse de nuevo para salir",
                Snackbar.LENGTH_LONG
            ).apply {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextColor(getColorStateList(R.color.white))
                    setBackgroundTintList(getColorStateList(R.color.blue_200))
                }
                show()
            }

        }
    }
}
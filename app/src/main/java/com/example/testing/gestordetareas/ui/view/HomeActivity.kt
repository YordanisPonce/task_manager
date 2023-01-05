package com.example.testing.gestordetareas.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.animation.*
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testing.gestordetareas.R
import com.example.testing.gestordetareas.data.model.DashboardModel
import com.example.testing.gestordetareas.databinding.ActivityHomeBinding
import com.example.testing.gestordetareas.ui.home.HomeViewModel
import com.example.testing.gestordetareas.ui.view.interfaces.OnAnimatedExtendedFab
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout


class HomeActivity : AppCompatActivity(), OnAnimatedExtendedFab, Animation.AnimationListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private var currenTime = 0L
    private var time = 2000L
    private lateinit var fab: FloatingActionButton
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var loadImage: ActivityResultLauncher<String>
    private var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)
        fab = binding.appBarHome.fb

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

        fab.setOnClickListener { view ->
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
                var builder = AlertDialog.Builder(this)
                var dialog = builder.create()
                dialog.apply {
                    var next = true
                    var view = layoutInflater.inflate(R.layout.custom_dialog, null)
                    var title =
                        view.findViewById<TextInputLayout>(R.id.title).editText!!
                    var description =
                        view.findViewById<TextInputLayout>(R.id.description).editText!!
                    var btnAtach = view.findViewById<AppCompatTextView>(R.id.atachButton)


                    btnAtach.setOnClickListener {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_DENIED){
                                //permission denied
                                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                                //show popup to request runtime permission
                                requestPermissions(permissions, PERMISSION_CODE);
                            }
                            else{
                                //permission already granted
                                pickImageFromGallery();
                            }
                        }
                        else{
                            //system OS is < Marshmallow
                            pickImageFromGallery();
                        }
                    }
                    //Adding event on all editTExts
                    addOnChaneListener(title)
                    addOnChaneListener(description)

                    view.findViewById<AppCompatButton>(R.id.btnAdd).setOnClickListener {


                        validate(title)
                        validate(description)
                        if (title.error.isNullOrEmpty() && description.error.isNullOrEmpty()) {
                            var board = DashboardModel(
                                title = title.text.toString(),
                                author = "Yordis",
                                photo = uri,
                                false,
                                description.text.toString()
                            )
                            viewModel.addBoard(board)
                            uri = null
                            dismiss()
                        }

                    }
                    setView(view)
                    var back = ColorDrawable(Color.TRANSPARENT)
                    var inset = InsetDrawable(back, 100, 0, 100, 0)
                    this.window!!.setBackgroundDrawable(inset)
                    view.findViewById<AppCompatImageView>(R.id.btnclose).setOnClickListener {
                        this.dismiss()
                    }
                    setCancelable(false)
                    show()
                }
            }
            2 -> Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
            3 -> startActivity(Intent(this, FriendsActivity::class.java))
        }
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }
    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
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


    override fun animateFab(position: Int) {
        fab.clearAnimation()
        var shrink = ScaleAnimation(
            1f, 0.2f, 1f, 0.2f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        shrink.apply {
            duration = 200 // animation duration in milliseconds
            interpolator = DecelerateInterpolator()
            setAnimationListener(this@HomeActivity)
        }
        fab.startAnimation(shrink)

    }

    override fun onAnimationStart(p0: Animation?) {

    }

    override fun onAnimationEnd(p0: Animation?) {
        var rotate = RotateAnimation(
            60.0f, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.apply {
            duration = 150
            interpolator = DecelerateInterpolator()
        }
        var expand = ScaleAnimation(
            0.1f, 1f, 0.1f,
            1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
            0.5f
        )
        expand.apply {
            duration = 200
            interpolator = DecelerateInterpolator()
        }

        var s = AnimationSet(false);
        s.addAnimation(rotate);
        s.addAnimation(expand);
        fab.startAnimation(s);
    }

    override fun onAnimationRepeat(p0: Animation?) {

    }

    private fun validate(et: EditText) {
        if (et.text.isEmpty()) {
            et.error = resources.getString(R.string.empty_field)
        }
    }

    private fun addOnChaneListener(et: EditText) {
        et.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotEmpty()) {
                et.error = null
            } else {
                et.error = resources.getString(R.string.empty_field)
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
           uri = data?.data
        }
    }


    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
package org.dpsteacher.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.teacher_fragment_home.*
import org.dpsteacher.R
import org.dpsteacher.model.DashboardData
import org.dpsteacher.mvvm.DashboardViewModel
import org.dpsteacher.ui.authenticaton.LoginActivity
import org.dpsteacher.ui.home.fragment.AttendanceFragment
import org.dpsteacher.ui.home.fragment.HomeFragment
import org.dpsteacher.ui.profile.ProfileMainFragment
import org.dpsteacher.utils.TeacherSharedPref
import org.dpsteacher.utils.log
import org.dpsteacher.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val viewModel: DashboardViewModel by viewModel()
    private var toolbar: Toolbar? = null
    private val sp by lazy { TeacherSharedPref.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        initViews()
        initComponentsNavHeader()
        loadFragment(HomeFragment.instance())


        setupViewModel()
        viewModel.dashboard(sp.teacherId!!)
        hideShowProgress(true)
    }

    private fun setToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        Objects.requireNonNull(supportActionBar)!!.setTitle(0)
    }

    private fun initViews() {
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(this)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.isDrawerIndicatorEnabled = false
        toggle.toolbarNavigationClickListener =
            View.OnClickListener { view: View? -> drawer.openDrawer(GravityCompat.START) }
        toggle.setHomeAsUpIndicator(R.drawable.ic_drawer)
        toggle.syncState()


        Glide.with(this).load(sp.teacherAvatar)
            .placeholder(R.drawable.ic_user)
            .into(profile_image)
        tv_name.text = sp.fullname  +" "+ sp.sname
        tv_reg.text ="Assign:-"+ sp.class_name +"/"+sp.section
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
            return true
        }
        return false
    }

    @SuppressLint("NonConstantResourceId")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_menu_home -> fragment = HomeFragment.instance()
            R.id.nav_menu_atten -> fragment = AttendanceFragment.instance()
            R.id.nav_menu_notification -> fragment = HomeFragment.instance()
            R.id.nav_menu_message -> fragment = ProfileMainFragment.instance()
        }
        return loadFragment(fragment)
    }

    private fun initComponentsNavHeader() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        //        navigationView.setItemIconTintList(null); //disable tint on each icon to use color icon svg
        navigationView.setNavigationItemSelectedListener(object :
            NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.nav_application -> Pesan("My Account")
                    R.id.nav_subject -> Pesan("My Account")
                    R.id.nav_notice -> Pesan("My Account")
                    R.id.nav_fee -> Pesan("My Account")
                    R.id.nav_result -> Pesan("My Account")
                    R.id.nav_support -> Pesan("Support")
                    R.id.nav_help -> Pesan("Help")
                    R.id.nav_logout -> {
                        TeacherSharedPref.instance.logOutTeacher()
                        val intent = Intent(this@MainActivity, LoginActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
                val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
                drawer.closeDrawer(GravityCompat.START)
                return true
            }

            private fun Pesan(pesan: String) {
                Toast.makeText(this@MainActivity, pesan, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupViewModel() {
        viewModel.dashboardData.observe(this, Observer {
            hideShowProgress(false)
            log("TAGS => Parent Login Data:   $it")
            updateSharePref(it)
        })


        viewModel.errorMsg.observe(this, Observer {
            hideShowProgress(false)
            log("TAGS => Parent/Teacher Login Error :   $it")
            toast(it)
        })
    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
            View.GONE
    }

    private fun updateSharePref(it: DashboardData) {
        it.let {
            sp.run {
                teacherAvatar = it.teacher?.teacherPicture
                phone1 = it.teacher?.phone
                phone2 = it.teacher?.alternate_no
                email = it.teacher?.email
                class_name = it.classInfo?.className
                section = it.classInfo?.section
                class_id = it.classInfo?.classId
            }
        }
        //initLoadViewPager()
    }
}
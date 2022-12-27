package com.example.navigationdrawer

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private var toggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFirstFragment()
        setupNavigationDrawer()
    }

    private fun showFirstFragment() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, Fragment1())
        ft.commit()
    }

    private fun setupNavigationDrawer() {
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.drawer_open, R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle!!)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onPostCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle!!.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_item_one -> {
                Toast.makeText(this, "one", Toast.LENGTH_SHORT).show()
                toolbar.title = "Fragment 1"
                fragment = Fragment1()
            }
            R.id.nav_item_two -> {
                Toast.makeText(this, "two", Toast.LENGTH_SHORT).show()
                toolbar.title = "Fragment 2"
                fragment = Fragment2()
            }
            R.id.nav_item_three -> {
                Toast.makeText(this, "three", Toast.LENGTH_SHORT).show()
                toolbar.title = "Fragment 3"
                fragment = Fragment3()
            }
            R.id.nav_item_four -> Toast.makeText(this, "four", Toast.LENGTH_SHORT).show()
            R.id.nav_item_five -> Toast.makeText(this, "five", Toast.LENGTH_SHORT).show()
            R.id.nav_item_six -> Toast.makeText(this, "six", Toast.LENGTH_SHORT).show()
            R.id.nav_item_seven -> Toast.makeText(this, "seven", Toast.LENGTH_SHORT).show()
        }

        //replacing the fragment
        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.container, fragment)
            ft.commit()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
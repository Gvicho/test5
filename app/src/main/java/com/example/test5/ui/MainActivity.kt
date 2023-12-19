package com.example.test5.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.test5.R
import com.example.test5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // for now this is useless
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navigationBottomGraph()
    }

    private fun navigationBottomGraph(){
        binding.bottomNavigation.apply {
            setOnItemSelectedListener { item ->
                val selectedFragment: Fragment = when (item.itemId) {
                    R.id.navigation_home -> CoursesFragment.newInstance()
                    R.id.navigation_dashboard -> HeartFragment.newInstance()
                    R.id.navigation_notifications -> MessagesFragment.newInstance()
                    else -> throw IllegalStateException("Unexpected value: " + item.itemId)
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, selectedFragment)
                    .commit()
                true // Return true to display the item as the selected item
            }

            selectedItemId = R.id.navigation_home
        }

    }

}
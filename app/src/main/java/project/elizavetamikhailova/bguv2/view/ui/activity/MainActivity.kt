package project.elizavetamikhailova.bguv2.view.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector

import kotlinx.android.synthetic.main.activity_main.*
import project.elizavetamikhailova.bguv2.R
import project.elizavetamikhailova.bguv2.helpers.add_food.Dayble
import project.elizavetamikhailova.bguv2.view.uimodels.Day




class MainActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector,
    Dayble {

    var currentDay = Day()
    override fun getDay(): Day {
        return currentDay
    }

    override fun setDay(day: Day) {
        currentDay = day
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bar)

        fab.setOnClickListener { view ->
            findNavController(R.id.main_nav_fragment).navigate(R.id.action_global_categoryFragment)
        }

        //NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.main_nav_fragment))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        NavigationUI.onNavDestinationSelected(item, findNavController(R.id.main_nav_fragment))
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.main_nav_fragment).navigateUp()
}

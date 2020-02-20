package bv.dev.dynamicsidemenu

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bv.dev.dynamicsidemenu.fragments.ImageFragment
import bv.dev.dynamicsidemenu.fragments.ProgressFragment
import bv.dev.dynamicsidemenu.fragments.TextFragment
import bv.dev.dynamicsidemenu.fragments.WebFragment
import bv.dev.dynamicsidemenu.models.MainViewModel
import bv.dev.dynamicsidemenu.models.menu.Menu
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        drawerLayout = findViewById(R.id.root)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(navItemSelListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, ProgressFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(this, Observer {
            if (it == null) {
                Toast.makeText(this, R.string.err_load, Toast.LENGTH_LONG).show()
                finish()
                return@Observer
            }

            for (mi in it.indices) {
                if (!it[mi].name.isNullOrEmpty()) {
                    navView.menu.add(0, mi, mi, it[mi].name)
                }
            }
            navView.menu.setGroupCheckable(0, true, true)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

            navView.setCheckedItem(viewModel.index)
            navView.menu.performIdentifierAction(viewModel.index, 0)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START, true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val navItemSelListener = NavigationView.OnNavigationItemSelectedListener { item ->
        Log.d(LOG_TAG, "menu item: ${item.itemId}")

        item.isChecked = true
        drawerLayout.closeDrawers()
        viewModel.index = item.itemId

        viewModel.getLiveData().observe(this@MainActivity, Observer {
            var frag = Fragment()
            if (it != null) {
                when (it[item.itemId].function) {
                    Menu.FUNC_TEXT -> frag = TextFragment()
                    Menu.FUNC_IMAGE -> frag = ImageFragment()
                    Menu.FUNC_URL -> frag = WebFragment()
                }
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, frag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        })

        true
    }
}

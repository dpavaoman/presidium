package com.presidium.smashtourney


import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.presidium.smashtourney.R
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import android.content.pm.PackageInfo
import android.content.pm.PackageManager.NameNotFoundException
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    var versionView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController
        versionView = findViewById(R.id.versionNumber)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(
            toolbar, navController, appBarConfiguration
        )

    }

    override fun onStart() {
        super.onStart()
        var versionName = ""
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            versionName = packageInfo.versionName
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
        }
        versionView!!.text = versionName
    }
}
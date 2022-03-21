package com.presidium.smashtourney;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

	TextView versionView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
		NavController navController = navHostFragment.getNavController();

		versionView = findViewById(R.id.versionNumber);
		Toolbar toolbar = findViewById(R.id.toolbar);
		AppBarConfiguration appBarConfiguration =
				new AppBarConfiguration.Builder(navController.getGraph()).build();
		NavigationUI.setupWithNavController(
				toolbar, navController, appBarConfiguration);
	}

	@Override
	protected void onStart() {
		super.onStart();
		String versionName = "";
		try {
			final PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			versionName = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		versionView.setText(versionName);
	}
}



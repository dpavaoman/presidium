package com.presidium.smashtourney;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.presidium.smashtourney.domain.QueryRetrieval;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity {



	Button searchByTitleButton;
	TextView versionView;
	Button searchByEventButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		searchByTitleButton = findViewById(R.id.titleButton);
		searchByEventButton = findViewById(R.id.eventButton);
		versionView = findViewById(R.id.versionNumber);



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

	@Override
	protected void onResume() {
		super.onResume();
		searchByTitleButton.setOnClickListener(v -> {
			Intent intent = new Intent(this, TitleSearch.class);
			startActivity(intent);
		});
		searchByEventButton.setOnClickListener(v -> {
			Intent intent = new Intent(this, EventSearch.class);
			startActivity(intent);
		});
	}

}



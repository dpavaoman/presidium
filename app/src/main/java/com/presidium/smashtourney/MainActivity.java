package com.presidium.smashtourney;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.presidium.smashtourney.Util.ProjectConstants;
import com.presidium.smashtourney.dao.EventStandingsQuery;
import com.presidium.smashtourney.domain.QueryRetrieval;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

	private final OkHttpClient httpClient = new OkHttpClient();
	private final QueryBuilder queryBuilder = new QueryBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView = findViewById(R.id.helloWorldTextView);
		textView.setText("hoes");
		TextView versionView = findViewById(R.id.versionNumber);
		String versionName = "";
		try {
			final PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			versionName = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		//Getting the button view
		Button button = findViewById(R.id.button);
		//Setting a listener to execute when the button is pressed
		button.setOnClickListener(v -> {

			//Executing the query retrieval on a different thread
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			Future<String> future = executorService.submit(new QueryRetrieval());
			executorService.shutdown();
			//Shutting down the executor service because we don't need it any more
			try {
				if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
					System.out.println("timeout");
					executorService.shutdownNow();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				executorService.shutdownNow();
			}
			String result = null;
			try {
				result = future.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			//updating the UI on the UI thread because that's what it's for
			String finalResult = result;
			runOnUiThread(() -> {
					textView.setText(finalResult);

			});


		});

		versionView.setText(versionName);


	}

}



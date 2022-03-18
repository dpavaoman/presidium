package com.presidium.smashtourney;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.presidium.smashtourney.dao.queries.TournamentsByTitleQuery;
import com.presidium.smashtourney.databinding.ActivityTournamentSearchBinding;
import com.presidium.smashtourney.domain.QueryRetrieval;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TitleSearch extends AppCompatActivity {

	Button button;
	EditText editText;
	TextView display;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_search);

		button = findViewById(R.id.search_button);
		editText = findViewById(R.id.search_box);
		display = findViewById(R.id.helloWorldTextView);

		//Setting a listener to execute when the button is pressed


	}

	@Override
	protected void onStart() {
		super.onStart();
		editText.setHint("enter title name");
		button.setText("Search Title");
	}

	@Override
	protected void onResume() {
		super.onResume();
		button.setOnClickListener(v -> {
			String eventId = editText.getText().toString();
			//Executing the query retrieval on a different thread
			ExecutorService executorService = Executors.newSingleThreadExecutor();
			Map<String, String> variables = new HashMap<>();
			variables.put("videogameid", eventId);
			String query = TournamentsByTitleQuery.query;
			Future<String> future = executorService.submit(new QueryRetrieval(variables, query, "tournament"));
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
				display.setText("EventId" + eventId + "\n" + finalResult);

			});


		});
	}
}
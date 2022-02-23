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

import java.io.IOException;

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
		Button button = findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Thread thread = new Thread(new Runnable() {
					EventStandingsQuery.Data queryData = null;

					@Override
					public void run() {
//						String query = queryBuilder.getEventQueryString();
						String query = "query EventStandings {\n" +
								"  event(id: 78790) {\n" +
								"    id\n" +
								"    name\n" +
								"    standings(query: {\n" +
								"      perPage: 3,\n" +
								"      page: 1\n" +
								"    }){\n" +
								"      nodes {\n" +
								"        placement\n" +
								"        entrant {\n" +
								"          id\n" +
								"          name\n" +
								"        }\n" +
								"      }\n" +
								"    }\n" +
								"  }\n" +
								"}\n";
						RequestBody body = new FormBody.Builder()
								.add("query", query)
								.build();

						Request request = new Request.Builder()
								.url(ProjectConstants.API_URL)
								.addHeader("Authorization", ProjectConstants.API_TOKEN)
								.post(body)
								.build();
						Response response = null;

						try {
							response = httpClient.newCall(request).execute();
							queryData = queryBuilder.parseResponse(response.body().string());
							System.out.println(queryData.toString());
						} catch (IOException | NullPointerException e) {
							e.printStackTrace();
						}
					}

				});
				thread.start();


			}
		});

		versionView.setText(versionName);


	}

}



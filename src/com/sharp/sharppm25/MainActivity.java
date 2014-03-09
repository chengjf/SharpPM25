package com.sharp.sharppm25;

import com.sharp.sharppm25.bean.Result;
import com.sharp.sharppm25.util.UtilFactory;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends RoboActivity {

	protected static final int STOP = 1; // 停止消息
	protected static final int CONTINUE = 2; // 继续消息

	@InjectView(R.id.pm25)
	TextView pm25;
	@InjectView(R.id.pm10)
	TextView pm10;
	@InjectView(R.id.o3)
	TextView o3;
	@InjectView(R.id.no2)
	TextView no2;
	@InjectView(R.id.so2)
	TextView so2;
	@InjectView(R.id.co)
	TextView co;
	@InjectView(R.id.aqi)
	TextView aqi;
	@InjectView(R.id.refresh)
	Button refresh;
	@InjectView(R.id.tips)
	TextView tips;
	@InjectView(R.id.progressBar)
	ProgressBar bar;

	int width;
	int height;

	final private int ABOUT = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		width = wm.getDefaultDisplay().getWidth();// 屏幕宽度
		height = wm.getDefaultDisplay().getHeight();
		refresh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bar.setVisibility(View.VISIBLE);
				refresh.setEnabled(false);
				InitTask child = new InitTask();
				child.execute("");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ABOUT, 0, "About");
		return true;
	}

	private class InitTask extends AsyncTask<String, Integer, String> {

		CityWeatherParser parser = new CityWeatherParser("xian");
		Result result;

		@Override
		protected String doInBackground(String... params) {
			Log.i(this.getClass().getName(), "DoInBackground Start!");
			result = parser.parseByJerry();
			Log.i(this.getClass().getName(), "DoInBackground End!");
			return "";
		}

		@Override
		protected void onPostExecute(String resultString) {
			Log.i(this.getClass().getName(), "PostExecute!");
			if (result.getStatus()) {
				Log.i(this.getClass().getName(), "Init UI ......");
				pm25.setText(String.valueOf(result.getIndex().getPm25()));
				pm10.setText(String.valueOf(result.getIndex().getPm10()));
				o3.setText(String.valueOf(result.getIndex().getO3()));
				no2.setText(String.valueOf(result.getIndex().getNo2()));
				so2.setText(String.valueOf(result.getIndex().getSo2()));
				co.setText(String.valueOf(result.getIndex().getCo()));
				aqi.setText(String.valueOf(result.getIndex().getAqi()));
				tips.setText(UtilFactory.getZhTip(result.getIndex().getAqi()));
				Log.i(this.getClass().getName(), "Init UI END!");
			} else {
				Toast.makeText(getApplicationContext(), result.getDetail(),
						Toast.LENGTH_LONG).show();
			}
			bar.setVisibility(View.GONE);
			refresh.setEnabled(true);
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			Log.i(this.getClass().getName(), "ProgressUpdate!");
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case ABOUT:
			AboutDialog dialog = new AboutDialog(this);
			dialog.setTitle("About");
			dialog.show();
			break;

		default:
			break;
		}
		return true;
	}
}

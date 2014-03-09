package com.sharp.sharppm25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.view.MotionEvent;
import android.widget.TextView;

public class AboutDialog extends Dialog {

	private static Context mContext = null;

	public AboutDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		AboutDialog.mContext = context;
		this.setCancelable(true);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.about);
		TextView tv = (TextView) findViewById(R.id.legal_text);
		tv.setText(readRawTextFile(R.raw.legal));

		tv = (TextView) findViewById(R.id.info_text);
		tv.setText(Html.fromHtml(readRawTextFile(R.raw.info)));
		tv.setLinkTextColor(Color.WHITE);
		Linkify.addLinks(tv, Linkify.ALL);
	}

	public static String readRawTextFile(int id) {
		InputStream inputStream = mContext.getResources().openRawResource(id);
		InputStreamReader reader = new InputStreamReader(inputStream);
		BufferedReader buf = new BufferedReader(reader);

		String line;
		StringBuilder text = new StringBuilder();
		try {
			while ((line = buf.readLine()) != null) {
				text.append(line);
			}
		} catch (IOException e) {
			// TODO: handle exception
			return null;
		}
		return text.toString();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			if (this.isShowing()) {
				this.dismiss();
			}
			break;

		default:
			break;
		}
		return true;
	}

}

package org.dda.tribhasha;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash);

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				Intent intent = new Intent(SplashScreen.this,
						Tri_Bhasha_Learner_Activity.class);
				SplashScreen.this.startActivity(intent);
				SplashScreen.this.finish();

			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 2000);

	}

}

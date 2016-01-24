package com.kerlinmichel.phonectrl;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.kerlinmichel.phonectrl.MyGame;

import java.io.PrintWriter;

public class AndroidLauncher extends AndroidApplication {
	static PrintWriter writer;
	boolean first = true;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		initialize(new MyGame(), config);
		//runnable.run();
		//new Handler().post(runnable);
		//this.runOnUiThread(runnable);
	}

	public static void setWriter(PrintWriter writer) {
		AndroidLauncher.writer = writer;
	}

	public Runnable runnable = new Runnable() {
		public void run() {
			runOnUiThread(new Runnable() {
				public void run() {
					while (true) {
						if (MyGame.readytoWrite && first) {
							writer = MyGame.writer;
							first = false;
							return;
						}
						if (MyGame.readytoWrite)
							writer.println(Gdx.input.getPitch() + "");
						try {
							Thread.sleep((long)((1f/60f)*1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	};
}

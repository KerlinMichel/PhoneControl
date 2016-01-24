package com.kerlinmichel.phonectrl;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class MyGame extends ApplicationAdapter {
	public static SpriteBatch batch;
	Texture img;
	BitmapFont font;
	ServerSocket socket;
	BufferedReader buffer;

	TextButton button;

	public static PrintWriter writer;
	public static boolean readytoWrite = false;

	Screens scr;

	public static int c = 1;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		//font = new BitmapFont();
		//ServerSocketHints hints = new ServerSocketHints();
		//hints.acceptTimeout = 0;
		//socket = Gdx.net.newServerSocket(Net.Protocol.TCP, 7100, hints);
		scr = new Screens();
		scr.show();
		/*TextButton.TextButtonStyle style =  new TextButton.TextButtonStyle();
		style.font = font;
		button =  new TextButton("Send", style);
		button.setPosition(200, 200);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent e, float x, float y) {
				//sendMsg();
				Gdx.app.log("x", "TESTING");
			}
		});*/
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(c, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		scr.render(Gdx.graphics.getDeltaTime());
		//font.draw(batch, "Azimuth: " + Gdx.input.getAzimuth(), 300, 300);
		//font.draw(batch, "Pitch: " + Gdx.input.getPitch(), 300, 200);
		//font.draw(batch, "Roll: " + Gdx.input.getRoll(), 300, 100);
		//button.draw(batch, 1);
		batch.end();
	}

	void sendMsg() {
		Socket s = socket.accept(null);
		try {
			s.getOutputStream().write(("" + Gdx.input.getRoll()).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		buffer = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
}

package com.kerlinmichel.phonectrl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.net.ServerSocket;
//import com.badlogic.gdx.net.ServerSocketHints;
//import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Kerlin Michel on 1/23/2016.
 */
public class Screens implements Screen {
    public static TextButton button;
    Stage stage;
    ServerSocket socket;
    public static String str = "Send";
    BitmapFont font;

    ImageButton buttonI;

    public static boolean fire;

    float delta;

    public Screens() {
        font = new BitmapFont();

        TextButton.TextButtonStyle style =  new TextButton.TextButtonStyle();
        style.font = font;
        button =  new TextButton(str, style);
        button.setScale(5);
        button.setPosition(200, 200);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                sendMsg();
                //Gdx.app.log("x", "TESTING");
            }
        });

        //ServerSocketHints hints = new ServerSocketHints();
        //hints.acceptTimeout = 0;
        //socket = Gdx.net.newServerSocket(Net.Protocol.TCP, 7100, hints);
        //socket = new ServerSocket(7100);
        try{
            socket = new ServerSocket(7100);
        } catch (IOException e){
            e.printStackTrace();
        }

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture("badlogic.jpg")));

        buttonI = new ImageButton(drawable);
        buttonI.setWidth(300);
        buttonI.setHeight(300);
        buttonI.setPosition(600, 300);
        buttonI.setScale(5);

        buttonI.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y) {
                fire = true;
            }
        });
    }
    @Override
    public void show() {
        stage = new Stage();
        stage.addActor(button);
        stage.addActor(buttonI);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
        /*if(font != null)
            font.draw(MyGame.batch, "Azimuth: " + Gdx.input.getAzimuth(), 300, 300);*/
        this.delta += delta;
        //if(this.delta > 3.5f)
            //fire = false;
    }

    void sendMsg() {
        //Screens.button.setText(Screens.str += "1");
        /*Socket s = null;
        try{
            s = socket.accept();
        } catch (IOException e){
            e.printStackTrace();
        }
        Thread th = new Thread(new ClientHandler(s));
        th.start();*/
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    //Screens.button.setText(Screens.str += "1");
                    Socket s = null;
                    try{
                        s = socket.accept();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    //Screens.button.setText(Screens.str += "1");
                    Thread th = new Thread(new ClientHandler(s));
                    th.start();
                }
            }
        });
        t.start();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

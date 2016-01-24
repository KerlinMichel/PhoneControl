package com.kerlinmichel.phonectrl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.net.Socket;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Kerlin Michel on 1/23/2016.
 */
public class ClientHandler implements Runnable {

    Socket socket;
    PrintWriter writer;
    DataOutputStream dataOut;
    //ImageButton button;
    float lastDegree;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        //writer = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
        //writer = new PrintWriter(new DataOutputStream(socket.getOutputStream()), true);
        try{
            writer = new PrintWriter(new DataOutputStream(socket.getOutputStream()), true);
        } catch (IOException e){
            e.printStackTrace();
        }
        //dataOut = new DataOutputStream(socket.getOutputStream());

    }

    @Override
    public void run() {
        //MyGame.writer = writer;
       // MyGame.readytoWrite = true;

        while(true){
            //writer.println(Gdx.input.getAzimuth() + "");
            /*try {
                dataOut.writeFloat(Gdx.input.getPitch());
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            //Screens.str += "1";
            //Screens.button.setText(Screens.str += "1");
            float pitch = Gdx.input.getPitch();
            if(Math.abs(lastDegree - pitch) > 0.5f) {
                writer.println(pitch + "");
                if (Screens.fire) {
                    writer.println("f");
                    Screens.fire = false;
                } else {
                    writer.println("n");
                }
                lastDegree = pitch;
            }
            //writer.println(Gdx.input.getRoll() + "");
            /*try {
                Thread.sleep((long)((1f/60f)*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}

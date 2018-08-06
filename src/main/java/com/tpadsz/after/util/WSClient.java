package com.tpadsz.after.util;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.*;
import org.java_websocket.handshake.ServerHandshake;

import javax.swing.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by chenhao.lu on 2016/9/22.
 */
public class WSClient {
    public Map<String, Queue> client;

    public WSClient(URI serverURI) {
    }

    public void connect(final String defaultLocation) {
        WebSocketClient cc = null;
//        WebSocketImpl.DEBUG = true;


        Draft[] drafts = {new Draft_17(), new Draft_10(), new Draft_76(), new Draft_75()};
        JComboBox draft = new JComboBox(drafts);

        try {
            cc = new WebSocketClient(new URI(defaultLocation), (Draft) draft.getSelectedItem()) {
                @Override
                public void onMessage(String message) {
                    System.out.println("WSClient:"+message);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    this.send("++++++++++++++++++123");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (cc != null) {
            cc.connect();
        }
    }

    public static void main(String[] args) {
        String location1 = "ws://localhost:8080/blt_light/websocket";
        String str="ws://ctc-hq.tpadsz.com/blt_light/websocket";
        URI location = URI.create(Constants.TEST_URL.value());
        WSClient ws = new WSClient(location);
        ws.connect(location1);
    }
}

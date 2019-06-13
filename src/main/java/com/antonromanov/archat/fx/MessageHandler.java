package com.antonromanov.archat.fx;

import javax.websocket.Session;

public class MessageHandler implements javax.websocket.MessageHandler.Whole<String> {

	String msg = null;

	public MessageHandler(String message) {
		this.msg = message;
	}

	public void onMessage(String s) {
		msg = s;
		System.out.println(s);
	}
}

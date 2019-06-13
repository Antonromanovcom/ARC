package com.antonromanov.archat.fx;



import org.glassfish.tyrus.core.wsadl.model.Endpoint;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@javax.websocket.ClientEndpoint
public class ClientEndpoint extends Endpoint {
	Session session = null;
	private static final Logger log = LoggerFactory.getLogger(ClientEndpoint.class);

	public ClientEndpoint() throws URISyntaxException, IOException, DeploymentException {
//		URI uri = new URI("ws://localhost:8080/example/hello");
//		ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
//		session.addMessageHandler(new MessageHandler(msg));
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) throws IOException {
		this.session = session;
		session.getBasicRemote().sendText("test");
	}


	@OnMessage
	public void processResponse(Session session, String message) {
		log.debug("Received response: '" + message + "' for request: '" + "' with session " + session.getId());

		//response = message;
	//	messageLatch.countDown();
	}

}

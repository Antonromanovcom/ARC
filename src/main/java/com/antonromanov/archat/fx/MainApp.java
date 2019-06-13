package com.antonromanov.archat.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.glassfish.tyrus.client.ClientManager;

import java.net.URI;

public class MainApp extends Application {

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		String fxmlFile = "/fxml/hello.fxml";
		FXMLLoader loader = new FXMLLoader();
		Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
		stage.setTitle("JavaFX and Maven");
		stage.setScene(new Scene(root));
		String message = "test";
		String messageR = "test R";
		final ClientEndpoint clientEndpoint = new ClientEndpoint();
		ClientManager client = ClientManager.createClient();

		client.connectToServer(
				clientEndpoint,
				URI.create("ws://localhost:8080/example/hello")
		);
		//String response = clientEndpoint.getResponse();


		//clientEndpoint.sendMessage(messageR);
		stage.show();
	}


}

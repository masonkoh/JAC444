import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Server extends Application {

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Text area for displaying contents
		TextArea ta = new TextArea();

		// Create a scene and place it in the stage
		Scene scene = new Scene(new ScrollPane(ta), 450, 200);
		primaryStage.setTitle("Server"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		new Thread(() -> {
			try {
				// Create a server socket
				ServerSocket serverSocket = new ServerSocket(8000);
				Platform.runLater(() ->
					ta.appendText("Server started at " 
						+ new Date() + '\n'));

				// Listen for a connection request
				Socket socket = serverSocket.accept();

				// Create data input and output streams
				DataInputStream inputFromClient = new DataInputStream(
					socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(
					socket.getOutputStream());

				while (true) {
					Date date = new Date();

					// Receive the weight and height from the server
					double weight = inputFromClient.readDouble();
					double height = inputFromClient.readDouble();


					double bmi = weight / Math.pow(height, 2);

					// Create string with BMI information
					StringBuilder strBMI = new StringBuilder("Your BMI is  " + 
						String.format("%.2f", bmi) + ". ");
					
					if (bmi < 18.5)
						strBMI.append("Underweight");
					else if (bmi < 25)
						strBMI.append("Normal");
					else if (bmi < 30)
						strBMI.append("Overweight");
					else
						strBMI.append("Obese");

					// Send string back to client
					outputToClient.writeUTF(strBMI.toString());

					Platform.runLater(() -> {
						ta.appendText("Connected to a client at " + date + '\n');
						ta.appendText("Weight: " + weight + '\n');
						ta.appendText("Height: " + height + '\n');
						ta.appendText(strBMI.toString() + '\n');
					});
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}).start();
	}
}
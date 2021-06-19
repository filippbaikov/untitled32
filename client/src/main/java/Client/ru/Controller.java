package Client.ru;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    TextField textField;
    @FXML
    TextArea textArea;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread thread = new Thread(() -> {

                try {
                    while (true) {
                        String inPutMess = in.readUTF();
                        textArea.appendText(inPutMess + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
            while (true) {
                String inPutMess = in.readUTF();
                textArea.appendText(inPutMess + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("интернет моросит чё-то, не работает короч");
            System.exit(0);

        }

    }

    public void send(ActionEvent actionEvent) {
        try {
            textArea.appendText(textField.getText() + "\n");
            out.writeUTF(textField.getText());
            textField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

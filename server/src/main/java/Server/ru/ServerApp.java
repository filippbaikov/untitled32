package Server.ru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args)  {
        try {
            ServerSocket serverSocket=new ServerSocket(8189);
            System.out.println("сервер запущен, ожидаем подключения");
            Socket socket=serverSocket.accept();
            DataInputStream in=new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            System.out.println("клиент подключился");
            while (true){
                String inPutMess= in.readUTF();
                System.out.println(inPutMess);
                out.writeUTF("EXO"+ inPutMess);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

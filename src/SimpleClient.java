import java.io.IOException;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        commWithServer();
    }
    private static void commWithServer() throws IOException {
        try(
                Socket socket = new Socket("localhost", SimpleServer.SERVER_PORT);
                ){
            Chat chat = new Chat("服务器你好",null,socket);
            chat.chatting();
        }
        System.out.println("程序退出！");
    }
}

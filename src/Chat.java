import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Chat {
    private String from;
    private String greetings;
    private Socket socket;

    public Chat(String from, String greetings, Socket socket) {
        this.from = from;
        this.greetings = greetings;
        this.socket = socket;
    }

    public void chatting(){
        Scanner in = new Scanner(System.in);
        try(
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), SimpleServer.SERVER_CHARSET)
                );
                PrintWriter pw = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream(), SimpleServer.SERVER_CHARSET)
                );
                ){
            System.out.println("Socket连接成功！建立输入输出");
            if(greetings != null){
                pw.println("你好" + from +"。"+ greetings);
                pw.flush();
            }
            while(true){
                String line = br.readLine();
                if (line.trim().equalsIgnoreCase(SimpleServer.BYE)){
                    System.out.println("对方要求断开连接");
                    pw.println(SimpleServer.BYE);
                    pw.flush();
                    break;
                }else{
                    System.out.println("来自\""+from+"\"的消息" + line);
                }
                line = in.nextLine();
                pw.println(line);
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("聊天结束");
    }
}

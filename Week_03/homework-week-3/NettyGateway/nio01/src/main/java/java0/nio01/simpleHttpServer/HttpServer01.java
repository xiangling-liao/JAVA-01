package java0.nio01.simpleHttpServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 单线程的socket程序, blocking, 效率太低
public class HttpServer01 {
    public static void main(String[] args) throws IOException{
        //create a server socket listening to port 8801
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                //System.out.println("socket connection accept");
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void service(Socket socket) {
        try {
            // sleep - 模拟业务操作(IO)
//            Thread.sleep(5);
            // 模拟输出 HTTP 报文头和 hello
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio1";
            //如果少了下面这句可能会报 'connection reset by peer' error. 因为 server 是不知道报文的长度的
            // TCP报文里没有结束符， 所以需要靠先拿到 body 长度来判断读多少行。
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.flush();
            printWriter.close();
            socket.close();
        } catch (IOException e) { // | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
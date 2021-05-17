package eak04_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    private Socket server = null; // СОКЕТ-СЕРВЕР
    private String serverName;
    private final BufferedReader in = null;
    private final PrintWriter out = null;

    public void start(String[] args) throws IOException {

        final int port = 7001; // НОМЕР ПОРТА СЕРВЕРА
        final String LOCAL_HOST = "127.0.0.1"; // АДРЕС ТЕКЦЩЕГО КОМПЬЮТЕРА В СЕТИ

        System.out.println("ПРОГРАММА КЛИЕНТ НА СОКЕТАХ TCP/IP");
        if (args.length == 0) {
            System.out.println("Не указан адрес сервера! Используется текущий хост " + LOCAL_HOST);
            serverName = LOCAL_HOST;
        } else {
            serverName = args[0];
        }

        System.out.println("Подключение к серверу " + serverName + " на порту " + port + " ...");

        try {
            server = new Socket(serverName, port);
            System.out.println("Подключение к серверу успешно");
        } catch (IOException e) {
            System.err.println("Не могу подключиться к серверу!");
            System.err.println("Завершение работы");
            System.exit(0);
        }

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter out = new PrintWriter(server.getOutputStream(), true);

            System.out.println("Передаем задание серверу:");
            double x = 7;
            double a = 8;
            double b = 9;

            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.out.println("x = " + x);

            out.println(x);
            out.println(a);
            out.println(b);

            out.println("stop");

            System.out.println("Ожидание ответа от сервера ...");

            String y = in.readLine();

            System.out.println("СЕРВЕР ПОСЛАЛ ОТВЕТ: ");
            System.out.println("y = " + y);

        } catch (IOException e) {
            System.err.println("Не могу получить ответ от сервера!");
            System.err.println("Завершение работы");
            System.exit(0);
        }
    }

    public void stop() throws IOException {
        out.close(); // Закрываем выходной поток на сервер
        in.close();  // Закрываем входной поток с сервера
        server.close(); // Закрываем клиента
        System.out.println("РАБОТА КЛИЕНТА ЗАВЕРШЕНА");
    }

}

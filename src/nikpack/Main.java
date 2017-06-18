package nikpack;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        URL myUrl = null;
        URLConnection connection = null;

        try {
            myUrl = new URL("http", "www.opennet.ru", "/");

            connection = myUrl.openConnection();

            if (connection instanceof HttpURLConnection) {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;

                for(Map.Entry entry: connection.getHeaderFields().entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                System.out.println(httpConnection.getRequestMethod());
                System.out.println(httpConnection.getContentLengthLong());

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(myUrl.openStream(), "koi8-r")
                );

                String line;
                while((line = in.readLine()) != null) {
                    System.out.println(line);
                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Invalid URL");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}

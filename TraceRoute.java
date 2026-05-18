import java.io.*;
import java.net.*;
import java.util.*;

public class TraceRoute {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Server IP/Host: ");
        String host = sc.nextLine();

        try {

            InetAddress addr = InetAddress.getByName(host);

            System.out.println("\nServer Host Name : " + addr.getHostName());
            System.out.println("Server IP Address: " + addr.getHostAddress());

            System.out.println("\nPath Details:");

            // Run tracert (Windows)
            Process p = Runtime.getRuntime().exec("tracert " + host);

            BufferedReader br =
                    new BufferedReader(
                            new InputStreamReader(p.getInputStream()));

            String line;

            while ((line = br.readLine()) != null) {

                // Print only lines containing IP addresses
                if (line.matches(".*\\d+\\.\\d+\\.\\d+\\.\\d+.*")) {
                    System.out.println(line);
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error");
        }

        sc.close();
    }
}
import java.io.*;
import java.net.*;
import java.util.*;

public class Trace {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // Local machine details
            InetAddress local = InetAddress.getLocalHost();

            System.out.println("Local Host Name : " + local.getHostName());
            System.out.println("Local IP Address: " + local.getHostAddress());

            // Input target
            System.out.print("\nEnter Server IP/Host: ");
            String host = sc.nextLine();

            InetAddress server = InetAddress.getByName(host);

            System.out.println("\nServer Host Name : " + server.getHostName());
            System.out.println("Server IP Address: " + server.getHostAddress());

            // Run Nmap
            System.out.println("\nScanning Path/Devices...\n");

            Process p = Runtime.getRuntime().exec(
                    "nmap -sn " + server.getHostAddress());

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        sc.close();
    }
}
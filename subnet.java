//wap in java to input an ip address is given. identify the new address divide the network into two subnetwork. find out 
// i) each subnetwork address 
// ii) no of the host in each subnetwork 
// iii) starting and ending ip address in each subnetwork 
// iv) no of usable ip address in each network

import java.util.*;

public class subnet {

    // Get default prefix based on class
    static int getPrefix(int firstOctet) {
        if (firstOctet < 128)
            return 8;
        else if (firstOctet < 192)
            return 16;
        else
            return 24;
    }

    // Convert IP to integer
    static long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        long res = 0;
        for (int i = 0; i < 4; i++) {
            res = (res << 8) + Integer.parseInt(parts[i]);
        }
        return res;
    }

    // Convert integer to IP
    static String longToIP(long ip) {
        return ((ip >> 24) & 255) + "." +
               ((ip >> 16) & 255) + "." +
               ((ip >> 8) & 255) + "." +
               (ip & 255);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter IP Address: ");
        String ip = sc.next();

        int firstOctet = Integer.parseInt(ip.split("\\.")[0]);

        int prefix = getPrefix(firstOctet);
        int newPrefix = prefix + 1; // divide into 2 subnets

        long ipVal = ipToLong(ip);

        long mask = ~((1L << (32 - prefix)) - 1);
        long network = ipVal & mask;

        long hosts = (1L << (32 - newPrefix));
        long usable = hosts - 2;

        System.out.println("\nDefault Prefix: /" + prefix);
        System.out.println("New Prefix: /" + newPrefix);
        System.out.println("Hosts per subnet: " + hosts);
        System.out.println("Usable hosts per subnet: " + usable);

        for (int i = 0; i < 2; i++) {
            long subnet = network + (i * hosts);
            long firstIP = subnet + 1;
            long lastIP = subnet + hosts - 2;
            long broadcast = subnet + hosts - 1;

            System.out.println("\nSubnet " + (i + 1) + ":");
            System.out.println("Network Address: " + longToIP(subnet));
            System.out.println("Starting IP: " + longToIP(firstIP));
            System.out.println("Ending IP: " + longToIP(lastIP));
            System.out.println("Broadcast Address: " + longToIP(broadcast));
            System.out.println("Usable IPs: " + usable);
        }

        sc.close();
    }
}
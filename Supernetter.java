import java.net.InetAddress;
import java.net.UnknownHostException;

public class Supernetter {
    public static void main(String[] args) throws UnknownHostException {
        String[] ips = {"192.168.0.0", "192.168.1.0", "192.168.2.0", "192.168.3.0"};
        
        // Convert to byte arrays
        byte[][] bytes = new byte[ips.length][];
        for (int i = 0; i < ips.length; i++) {
            bytes[i] = InetAddress.getByName(ips[i]).getAddress();
        }

        int commonBits = 0;
        outer: for (int i = 0; i < 32; i++) {
            int byteIdx = i / 8;
            int bitIdx = 7 - (i % 8);
            int bit = (bytes[0][byteIdx] >> bitIdx) & 1;
            
            for (int j = 1; j < bytes.length; j++) {
                if (((bytes[j][byteIdx] >> bitIdx) & 1) != bit) break outer;
            }
            commonBits++;
        }

        System.out.println("Supernet Prefix: /" + commonBits);
    }
}

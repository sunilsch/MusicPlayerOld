package data;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HTTPS_Helper {
    private final String sourceIP;

    public HTTPS_Helper(final String sourceIP){

        this.sourceIP = sourceIP;

        // Create a new trust manager that trust all certificates
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HostnameVerifier allHostsValid = (hostname, session) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    public void getSong(final String songName) {
        try {
            // create URL Connection
            URL url = new URL(sourceIP +"/files/"+songName+".wav");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // init auth methode
            String auth = "user:IbdUdRPI";
            byte[] encodedAuth = Base64.getUrlEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            connection.setRequestProperty("Authorization", authHeaderValue);

            // get result as stream
            InputStream inputStream = connection.getInputStream();

            // write result to file
            FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir")+"/audio/"+songName+".wav");
            ReadableByteChannel rbc = Channels.newChannel(inputStream);
            fileOutputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch(Exception e){
            System.out.println("Error while getting Song via HTTPS");
            System.out.println("Error: ");
            e.printStackTrace();
        }



    }

    public void postSong(final String source){
        String[] parts = source.split("/");
        String filename = parts[parts.length-1];
        System.out.println(filename);

        try {
            HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) new URL(sourceIP+"/upload/upload-java.php?filename="+filename).openConnection();
            httpsUrlConnection.setDoOutput(true);
            httpsUrlConnection.setRequestMethod("POST");

            String auth = "user:IbdUdRPI";
            byte[] encodedAuth = Base64.getUrlEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            httpsUrlConnection.setRequestProperty("Authorization", authHeaderValue);

            OutputStream os = httpsUrlConnection.getOutputStream();
            Thread.sleep(1000);
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(source));

            long totalByte = fis.available();
            for (int i = 0; i < totalByte; i++) {
                os.write(fis.read());
            }

            os.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpsUrlConnection.getInputStream()));

            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
            in.close();
            fis.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

package data;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HTTPS_Helper {
    private final String source;

    public HTTPS_Helper(final String sourceIP){

        this.source = sourceIP;

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
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    public void getSong(final String songName) {
        try {
            // create URL Connection
            URL url = new URL(source+"/files/"+songName+".wav");
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
}

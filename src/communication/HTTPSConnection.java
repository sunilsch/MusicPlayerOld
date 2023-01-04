package communication;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

import data.PATH_Helper;

public class HTTPSConnection {
    private final String serverIP;
    private final String getPath;
    private final String postPath;
    private String username;
    private String password;

    public HTTPSConnection(final String serverIP, final String getPath, final String postPath) {
        this.serverIP = "https://"+serverIP;
        this.getPath = getPath;
        this.postPath = postPath;

        // get credentials form /config/loginHTTPS.txt -> format user:password
        File file = new File(PATH_Helper.getConfigPath("loginHTTPS.txt"));
        try{
            Scanner scanner = new Scanner(file);
            String[] creds = scanner.nextLine().split(";");
            username = creds[0];
            password = creds[1];
        } catch(Exception e){
            System.out.println("Cant find config file for https login");
            e.printStackTrace();
        }

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

    public void auth(HttpsURLConnection connection){
        String auth = username+":"+password;
        byte[] encodedAuth = Base64.getUrlEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeaderValue = "Basic " + new String(encodedAuth);
        connection.setRequestProperty("Authorization", authHeaderValue);
    }

    public void getSong(final String fileName) {
        new Thread(() -> {
            try {
                // create URL Connection
                URL url = new URL(serverIP+getPath+fileName);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                // init auth methode
                auth(connection);

                // get result as stream
                InputStream inputStream = connection.getInputStream();

                // write result to file
                FileOutputStream fileOutputStream = new FileOutputStream(PATH_Helper.getAudioPath(fileName));
                ReadableByteChannel rbc = Channels.newChannel(inputStream);
                fileOutputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            } catch(Exception e){
                System.out.println("Error while getting Song via HTTPS");
                System.out.println("Error: ");
                e.printStackTrace();
            }
        }).start();
    }

    public void postSong(final String fileName){
        final String source = PATH_Helper.getAudioPath(fileName);
        new Thread(() -> {
            String[] parts = source.split("/");
            String filename = parts[parts.length-1];

            try {
                HttpsURLConnection connection = (HttpsURLConnection) new URL(serverIP+postPath+"?filename="+filename).openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");

                auth(connection);

                OutputStream os = connection.getOutputStream();
                Thread.sleep(1000);
                BufferedInputStream fis = new BufferedInputStream(new FileInputStream(source));

                long totalByte = fis.available();
                for (int i = 0; i < totalByte; i++) {
                    os.write(fis.read());
                }

                os.close();


                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String s;
                while ((s = in.readLine()) != null) {
                    System.out.println(s);
                }
                in.close();

                fis.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}

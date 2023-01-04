package data;

public class PATH_Helper {

    public static String getAudioPath(String filename){
        return System.getProperty("user.dir")+"/audio/"+filename;
    }

    public static String getImagePath(String filename){
        return System.getProperty("user.dir")+"/img/"+filename;
    }

    public static String getConfigPath(String filename){
        return System.getProperty("user.dir")+"/config/"+filename;
    }
}

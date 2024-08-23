package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {

    private static ReadConfig instance=null;
    private String logo;
    private String executionName;

    //Mobile configuration
    private String deviceName;
    private String platformVersion;
    private String appPackage;
    private String appActivity;
    private String platformName;

    private ReadConfig(){
        Properties properties = new Properties();
        String nameFile="qa.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nameFile);
        if (inputStream!= null ){
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logo=properties.getProperty("logo");
        executionName=properties.getProperty("executionName");
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ReadConfig getInstance(){
        if(instance == null)
            instance= new ReadConfig();
        return instance;
    }

    public String getLogo() {
        return logo;
    }

    public String getExecutionName() {
        return executionName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public String getPlatformName() {
        return platformName;
    }
}//end ReadConfig


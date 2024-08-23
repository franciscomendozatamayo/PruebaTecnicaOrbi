package config;

public class EnvironmentConfig {

    public static String Logo=ReadConfig.getInstance().getLogo();
    public static String executionName=ReadConfig.getInstance().getExecutionName();

    public static String deviceName=ReadConfig.getInstance().getDeviceName();
    public static String platformVersion=ReadConfig.getInstance().getPlatformVersion();
    public static String appPackage=ReadConfig.getInstance().getAppPackage();
    public static String appActivity=ReadConfig.getInstance().getAppActivity();
    public static String platformName=ReadConfig.getInstance().getPlatformName();
}

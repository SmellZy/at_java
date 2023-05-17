package itstep.course_tasks.task_22;

import io.appium.java_client.AppiumDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class DriverManager {
     private static AppiumDriver driver;
    // For Appium < 2.0, append /wd/hub to the APPIUM_SERVER_URL
    String APPIUM_SERVER_URL = "http://0.0.0.0:4726/wd/hub";

    public AppiumDriver getInstance(){
        try {
            return getAndroidDriver();
        } catch (IOException e) {

        }
        return null;
    }

    private AppiumDriver getAndroidDriver() throws IOException {
        File file = new File("appium"+File.separator+"config.json");
        String content = Files.readString(Path.of(file.getAbsolutePath()));
        return getDriver(new JSONObject(content).toMap());
    }

    private AppiumDriver getDriver(Map<String, Object> map) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities(map);

        try {
            driver = new AppiumDriver(
                    new URL(APPIUM_SERVER_URL), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}

package com.automation.selenium.core;

import com.automation.selenium.pages.LoginPage;
import com.automation.selenium.utils.ConfigLoader;
import com.automation.selenium.utils.Utilities;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.testng.annotations.Listeners(com.automation.selenium.core.Listeners.class)

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected LoginPage loginPage;

    protected WebDriver initializeDriver() throws IOException {

        String browserName = Utilities.get("browser");
        String runMode = Utilities.get("runMode");
        String gridUrl = Utilities.get("gridUrl");

        WebDriver webDriver;

//--------Start*************************

        if (runMode.equalsIgnoreCase("grid")) {

            switch (browserName.toLowerCase()) {

                case "chrome" -> {
                    ChromeOptions options = new ChromeOptions();
                    options.setAcceptInsecureCerts(true);
                    webDriver = new org.openqa.selenium.remote.RemoteWebDriver(
                            java.net.URI.create(gridUrl).toURL(),
                            options
                    );
                }

                case "firefox" -> {
                    FirefoxOptions options = new FirefoxOptions();
                    options.setAcceptInsecureCerts(true);
                    webDriver = new org.openqa.selenium.remote.RemoteWebDriver(
                            java.net.URI.create(gridUrl).toURL(),
                            options
                    );
                }

                case "edge" -> {
                    EdgeOptions options = new EdgeOptions();
                    options.setAcceptInsecureCerts(true);
                    webDriver = new org.openqa.selenium.remote.RemoteWebDriver(
                            java.net.URI.create(gridUrl).toURL(),
                            options
                    );
                }

                default -> throw new IllegalArgumentException(
                        "Browser not supported for Grid: " + browserName
                );
            }

        } else {

            switch (browserName.toLowerCase()) {

                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions options = new ChromeOptions();
                    options.setAcceptInsecureCerts(true);
                    webDriver = new ChromeDriver(options);
                }

                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions options = new FirefoxOptions();
                    options.setAcceptInsecureCerts(true);
                    webDriver = new FirefoxDriver(options);
                }

                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions options = new EdgeOptions();
                    options.setAcceptInsecureCerts(true);
                    webDriver = new EdgeDriver(options);
                }

                default -> throw new IllegalArgumentException(
                        "Browser not supported: " + browserName
                );
            }
        }

//---------End****************************





//        above code is we using as we have commented origenal switch code

//        switch (browserName.toLowerCase()) {
//
//            case "chrome" -> {
//                WebDriverManager.chromedriver().setup();
//
//                String downloadPath = System.getProperty("user.dir") + "/downloads";
//
//                Map<String, Object> prefs = new HashMap<>();
//                prefs.put("download.default_directory", downloadPath);
//                prefs.put("download.prompt_for_download", false);
//
//                ChromeOptions options = new ChromeOptions();
//                options.setAcceptInsecureCerts(true);
//                options.setExperimentalOption("prefs", prefs);
//                // options.addArguments("--headless=new");
//
//                webDriver = new ChromeDriver(options);
//            }
//
//            case "firefox" -> {
//                WebDriverManager.firefoxdriver().setup();
//                FirefoxOptions options = new FirefoxOptions();
//                options.setAcceptInsecureCerts(true);
//                webDriver = new FirefoxDriver(options);
//            }
//
//            case "edge" -> {
//                WebDriverManager.edgedriver().setup();
//                EdgeOptions options = new EdgeOptions();
//                options.setAcceptInsecureCerts(true);
//                webDriver = new EdgeDriver(options);
//            }
//
//            default -> throw new IllegalArgumentException(
//                    "Browser not supported: " + browserName
//            );
//        }



        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.set(webDriver);
        return webDriver;
    }



    //-------------Below is General methods


    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException {
        initializeDriver();
//        loginPage = new LoginPage(getDriver());
//        loginPage.goTo();
    }

    protected void open(String path) {
        getDriver().get(ConfigLoader.get("base.url") + path);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    public String getScreenshot(String testCaseName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        String screenshotPath = System.getProperty("user.dir")
                + "/reports/" + testCaseName + ".png";

        FileUtils.copyFile(source, new File(screenshotPath));
        return screenshotPath;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath)
            throws IOException {

        String jsonContent = FileUtils.readFileToString(
                new File(filePath), StandardCharsets.UTF_8
        );

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {}
        );
    }
}
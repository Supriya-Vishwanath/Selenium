package TestJava;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    public enum BrowserType{
        CHROME,
        FIREFOX,
        Edge,
    }
    public static WebDriver createDriver(BrowserType browserType){
        WebDriver driver = null;
        switch(browserType){
            case CHROME:
                ChromeOptions coptions=new ChromeOptions();
                coptions.addArguments("--start-maximized");
                driver = new ChromeDriver(coptions);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                break;
                case FIREFOX:
                    FirefoxOptions firefoxOptions=new FirefoxOptions();
                    firefoxOptions.addArguments("--start-maximized");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case Edge:
                    EdgeOptions edgeOptions=new EdgeOptions();;
                    edgeOptions.addArguments("--start-maximized");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser type " + browserType);
        }
        return driver;
    }
}

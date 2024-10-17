package TestJava;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class CuraProject {
    private static final String SITE = "https://katalon-demo-cura.herokuapp.com/profile.php#login";
    private WebDriver driver;
    private String year = "2024";
    private String month = "December";
    private String day = "20";

    //private static final String CART=SITE+"cart.html";
    @BeforeTest
    public void setUp() {
        driver = DriverFactory.createDriver(DriverFactory.BrowserType.CHROME);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void idLocatorsTest() throws IOException {
        driver.get(SITE);
        WebElement MakeApp = driver.findElement(By.xpath("//a[@id='btn-make-appointment']"));
        MakeApp.click();
        WebElement userId = driver.findElement(By.id("txt-username"));
        userId.sendKeys("John Doe");
        WebElement pass = driver.findElement(By.id("txt-password"));
        pass.sendKeys("ThisIsNotAPassword");
        delay(5000);
        WebElement login = driver.findElement(By.xpath("//button[@id='btn-login']"));
        login.click();
        delay(5000);
        WebElement dropdown = driver.findElement(By.id("combo_facility"));
        Select drop = new Select(dropdown);
        drop.selectByVisibleText("Hongkong CURA Healthcare Center");
         delay(5000);
        WebElement check = driver.findElement(By.xpath("//input[@id='chk_hospotal_readmission']"));
        check.click();
        delay(5000);
        WebElement radio = driver.findElement(By.xpath("//body/section[@id='appointment']/div[1]/div[1]/form[1]/div[3]/div[1]/label[3]"));
        radio.click();
        delay(5000);
        WebElement date = driver.findElement(By.xpath("//body/section[@id='appointment']/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[1]/span[1]"));
        date.click();
        while (true) {
            String monyear = driver.findElement(By.xpath("(//th[@class='datepicker-switch'])[1]")).getText();
            System.out.println(monyear);
            delay(5000);
            String arr[] = monyear.split(" ");
            String mon = arr[0];
            String yr = arr[1];
            if (mon.equalsIgnoreCase(month) && yr.equalsIgnoreCase(year)) {
                break;
            } else {
                driver.findElement(By.xpath("//body[1]/div[1]/div[1]/table[1]/thead[1]/tr[2]/th[3]")).click();
            }
        }
        //Assert.assertEquals(driver.getCurrentUrl(),SITE+"index.html");

        //Date selection
        List<WebElement> alldates = driver.findElements(By.xpath("//div[@class='datepicker-days']//td"));
        for (WebElement ele : alldates) {
            String dt = ele.getText();
            System.out.println("dt" + dt);
            if (dt.equals(day)) {
                ele.click();
                break;
            }
            ;
        }
        delay(5000);

        while(true) {
            String monyear1 = driver.findElement(By.xpath("(//th[@class='datepicker-switch'])[1]")).getText();
            delay(5000);
            String arr[] = monyear1.split(" ");
            String mon1 = arr[0];
            String yr1 = arr[1];
            if (mon1.equalsIgnoreCase(month) && yr1.equalsIgnoreCase(year)) {
                break;
            } else {
                WebElement newdate = driver.findElement(By.xpath("//input[@id='txt_visit_date']"));
                newdate.clear();
                newdate.sendKeys(day + "/" + month + "/" + year);
                break;
            }
        }
        delay(5000);
        WebElement comment=driver.findElement(By.id("txt_comment"));
        comment.sendKeys("Selenium project :)");
        WebElement submit=driver.findElement(By.id("btn-book-appointment"));
        submit.click();
        delay(5000);
     File screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), new File("D:\\Selenium\\sample.jpg").toPath());

    }
    public void delay(long millis){
        try{
                Thread.sleep(5000);
                }
        catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}



import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyClassTest {
    private static  WebDriver driver;

    @BeforeClass
    public static void setup(){

        System.setProperty("webdriver.chrome.driver","C:\\Users\\eacl\\Desktop\\TEST\\chromedriver_win32\\chromedriver.exe");

        
        driver = new ChromeDriver();
        //com.jayway.restassured.RestAssured.given().get("hhtp://localhost:3000/reset"); - caused errors
        driver.get("http://localhost:3000/reset");

        driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);
    }
    @AfterClass
    public static void tearDown(){

        driver.close();
        driver.quit();
    }
    @Test
    public void test1(){
        /**  1. Verify that data is loaded, and the DOM is constructed (Five rows in the table) */


        driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);


        driver.get("http://localhost:3000/");
        assertTrue(driver.getTitle().startsWith("Document"));
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        int tableSize = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(5, tableSize);

    }
    @Test
    public void test2(){
         /** 2. Write 2002 in the filter text and verify that we only see two rows*/
        driver.findElement(By.id("filter")).sendKeys("2002");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        int tableSize = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(2, tableSize);
    }
    @Test
    public void test3() {
        /** 3. Clear the text in the filter text and verify that we have the original five rows  */
        WebElement element = driver.findElement(By.id("filter"));
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);


        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        WebDriverWait wait2 = new WebDriverWait(driver,3);
        driver.findElements(By.id("tbodycars"));
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);


        int tableSize = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        assertEquals(5, tableSize);

    }
    @Test
    public void test4() {
        /** 4. Click the sort “button” for Year, and verify that the top row contains
         * the car with id 938 and the last row the car with id = 940.*/
         //todo find the correct element to click
        //todo query the two ids at top

        // Reset
        driver.get("http://localhost:3000/");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        driver.findElement(By.id("h_year")).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

       List<WebElement> tr = driver.findElements(By.xpath("//table[@class='table']/tbody/tr"));
        assertTrue(tr.get(0).getText().startsWith("938"));
        assertTrue(tr.get(tr.size()-1).getText().startsWith("940"));


    }
    @Test
    public void test5() {
        /** 5. Press the edit button for the car with the id 938.
         * Change the Description to "Cool car", and save changes.
         * Verify that the row for car with id 938 now contains "Cool car" in the Description column */
        //todo find car with i
        //todo press the button
        //todo Reset
        driver.get("http://localhost:3000/");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        driver.findElement(By.id("h_year")).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        WebElement edit_link = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[1],\"938\")]/td[8]")).get(0);
        edit_link.findElements(By.tagName("a")).get(0).click();

        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Cool car");
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
        driver.findElement(By.id("save")).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        System.out.println(driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[1],\"938\")]/td[6]")).get(0).getText());
        WebElement e = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[1],\"938\")]/td[6]")).get(0);
        assertTrue("Cool car".equals(e.getText()));

        //set description to cool car
        //verify that the cool car contains the description
    }
    @Test
    public void test6() {
        /** 6. Click the new “Car Button”, and click the “Save Car” button. Verify that we have an error message with the
         * text “All fields are required” and we still only have five rows in the all cars table. */
        //todo click new car button
        //todo click save btn
        //todo verify text
        driver.findElement(By.id("new")).click();
        driver.findElement(By.id("save")).click();

        WebElement e = driver.findElements(By.id("submiterr")).get(0);
        assertTrue("All fields are required".equals(e.getText()));


    }
    @Test
    public void test7() {
        /** 6. 7. Click the new Car Button, and add the following values for a new car a.
         *
         * Year:   2008
         * b. Registered:  2002-5-5
         * c. Make:   Kia
         * d. Model:   Rio
         * e. Description:  As new
         * f. Price:   31000
         *
         Click “Save car”, and verify that the new car was added to the table with all the other cars. */
        //todo
        driver.findElement(By.id("new")).click();
        driver.findElement(By.id("year")).sendKeys("2008");
        driver.findElement(By.id("registered")).sendKeys("2002-5-5");
        driver.findElement(By.id("make")).sendKeys("Kia");
        driver.findElement(By.id("model")).sendKeys("Rio");
        driver.findElement(By.id("description")).sendKeys("As new");
        driver.findElement(By.id("price")).sendKeys("31000");

        driver.findElement(By.id("save")).click();


        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        int tableSize = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(6, tableSize);


        System.out.println(driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[2],\"2008\")]/td")).get(1).getText());
        System.out.println(driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[2],\"2008\")]/td")).get(2).getText());
        System.out.println(driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[2],\"2008\")]/td")).get(3).getText());
        System.out.println(driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[2],\"2008\")]/td")).get(4).getText());
        System.out.println(driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[2],\"2008\")]/td")).get(5).getText());
        System.out.println(driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[2],\"2008\")]/td")).get(6).getText());
        List<WebElement> elms = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[2],\"2008\")]/td"));
        assertTrue("2008".equals(elms.get(1).getText()));
        assertTrue("5/5/2002".equals(elms.get(2).getText()));
        assertTrue("Kia".equals(elms.get(3).getText()));
        assertTrue("Rio".equals(elms.get(4).getText()));
        assertTrue("As new".equals(elms.get(5).getText()));
        assertTrue("31.000,00 kr.".equals(elms.get(6).getText()));


    }






}
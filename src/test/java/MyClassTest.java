import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    }
    @AfterClass
    public static void tearDown(){

        driver.close();
        driver.quit();
    }
    @Test
    public void test1(){
        /**  1. Verify that data is loaded, and the DOM is constructed (Five rows in the table) */
        driver.get("http://localhost:3000/");
        assertTrue(driver.getTitle().startsWith("Document"));
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        int tableSize = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(5, tableSize);

    }
    @Test
    public void test2(){
         /** 2. Write 2002 in the filter text and verify that we only see two rows*/


    }
    @Test
    public void test3() {
        /** 3. Clear the text in the filter text and verify that we have the original five rows  */
    }
    @Test
    public void test4() {
        /** 4. Click the sort “button” for Year, and verify that the top row contains
         * the car with id 938 and the last row the car with id = 940.*/
    }
    @Test
    public void test5() {
        /** 5. Press the edit button for the car with the id 938. Change the Description to "Cool car", and save changes.
         * Verify that the row for car with id 938 now contains "Cool car" in the Description column */
    }
    @Test
    public void test6() {
        /** 6. Click the new “Car Button”, and click the “Save Car” button. Verify that we have an error message with the
         * text “All fields are required” and we still only have five rows in the all cars table. */
    }
    @Test
    public void test7() {
        /** 6. 7. Click the new Car Button, and add the following values for a new car a. Year:   2008 b. Registered:  2002-5-5 c. Make:   Kia d. Model:   Rio e. Description:  As new f. Price:   31000
         Click “Save car”, and verify that the new car was added to the table with all the other cars. */
    }

}
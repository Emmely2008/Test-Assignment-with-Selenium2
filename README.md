
## Automated System Testing with Selenium2
*Emmely Lundberg cph-el69*

Demonstrate how to use Selenium to test a modern interactive (JavaScript driven) web application, and some of the problems involved with automated GUI testing1. 
This is a StudyPoint exercise and will end up as part of an exam-question as sketched below: 
 
_______________ 
#### 1) Discuss Pros and Cons with manual versus automated tests 

Manual tests takes longer time to implement but the time is gained be repeating the test again and again. 
We reuse the test in regression testing if the tests passes we verify that the software works when changes to the code is made.

Pros and cons with automated tests:

##### Pros
- Fast and effective when performing the test.
- Everyone can see results.
- Take away manual repetitive boring tasks.
- Take way the human factor of making mistakes in testing.

##### Cons
- Takes time to implement.
- Require competes (usually developers) to implement.
- In the case of a project it requires resources usually taken from developers that could be used for implementing new features.
- Less flexible than manual testing and has limitation. Limitations such as we can't automate everything like determined good visually layout and usability.
- Tools cost money for license and require knowledge.


Manual test can be cost effective in the short time. Easier to capture usability bugs using manual tests.
_______________ 
#### 2) Explain about the Test Pyramid and whether this exercise supports the ideas in the Test Pyramid 


*Mike Cohn came up with this concept in his book Succeeding with Agile. 
It's a great visual metaphor telling you to think about different layers of testing. 
It also tells you how much testing to do on each layer.* [source](https://martinfowler.com/articles/practical-test-pyramid.html)

[![https://gyazo.com/3b03458bfad91ee08975cfa348780fe1](https://i.gyazo.com/3b03458bfad91ee08975cfa348780fe1.png)](https://gyazo.com/3b03458bfad91ee08975cfa348780fe1)

I belive the Selenium tests in the exercise supports the ideas in the Test Pyramid the idea because:

Given that this is just a snap shot of the tests to be implemented for this application I would say we do tests according to the test pyramid.

For example in ReatJS we perfectly write unit test in all of these frameworks aswell. 
The test would probably move closer to Service test because such unit tests usually test front-end logic rather than the GUI.
 
In the exercise we make a few test to check that the fron-end is "healthy". 
This aligned with the test pyramid be cause we are doing tests in the higher level and the pyramid suggest us to do fewer tests there.
Checking for core behavior and that the GUI is healthy rather than checking lots of inputs with border values is an indication that the tests are at a reasonable
level like the test pyramid suggests.

_______________ 
#### 3) Discuss some of the problems with automated GUI tests and what makes such tests "vulnerable"  

I see two vulnerabilities:

1) We are limited in our testing such as testing for usability and that the layout hasn't changed.

*Once you want to test for usability and a "looks good" factor you leave the realms of automated testing. 
This is the area where you should rely on exploratory testing, usability testing (this can even be as simple as hallway testing) 
and showcases with your users to see if they like using your product and can use all features without getting frustrated or annoyed.*

2) The GUI is what the stakeholders/users see and they might be eager to change (for example to sat in fashion). 
It is also the easiest part to change. If it is changed often that might lead to the automated GUI test needing to be rewritten.
The tests then require more maintenance than they create value.

_______________ 
#### 4) Demonstrate details in how to create a Selenium Test using the code for the exercise 

Make sure the tests are run in name ascending order with the annotation *@FixMethodOrder(MethodSorters.NAME_ASCENDING)*.

Set up and tear down the WebDriver. I use Chrome as Selenium WebDriver to manipulate the DOM. I also reset the back-end stup 
by calling "hhtp://localhost:3000/reset" and make sure the DOM is ready before the tests run with *setScriptTimeout*. See code:

```

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyClassTest {
    private static  WebDriver driver;

    @BeforeClass
    public static void setup(){

        System.setProperty("webdriver.chrome.driver","C:\\Users\\eacl\\Desktop\\TEST\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();

        //com.jayway.restassured.RestAssured.given().get("hhtp://localhost:3000/reset"); - caused errors
        driver.get("http://localhost:3000/reset"); // We reset the back-end stub to initial state.

        driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);
    }
    @AfterClass
    public static void tearDown(){

        driver.close();
        driver.quit();
    }
	
``` 
This is the code that creates a new instance of car and verifies that is created by making sure that the list is increase by one and that we can find the item with the properties.
I use XPath, id and tagname to target the elements to manipulate and read.


Clicking a anchor tag inside a cell I need to use this command:

```
        WebElement edit_link = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[1],\"938\")]/td[8]")).get(0);
        edit_link.findElements(By.tagName("a")).get(0).click();
```

```
```



In this example I use the driver method findElements to find all the table rows given table with the class attribute = 'table'. I then assert that the number of table rows equal 6.  

```
        int tableSize = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
        assertEquals(6, tableSize);
```
In this example I use the driver method findElements to find all the table data of a given table row that contains the text "2008" in the second column.  

``` 
driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[2],\"2008\")]/td"));
```

```
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


        List<WebElement> elms = driver.findElements(By.xpath("//table[@class='table']/tbody/tr[contains(td[2],\"2008\")]/td"));
        assertTrue("2008".equals(elms.get(1).getText()));
        assertTrue("5/5/2002".equals(elms.get(2).getText()));
        assertTrue("Kia".equals(elms.get(3).getText()));
        assertTrue("Rio".equals(elms.get(4).getText()));
        assertTrue("As new".equals(elms.get(5).getText()));
        assertTrue("31.000,00 kr.".equals(elms.get(6).getText()));


    }

``` 


_______________ 
#### 5) Explain shortly about the DOM, and how you have read/manipulated DOM-elements in your test  

*The Document Object Model (DOM) is a programming interface for HTML and XML documents. 
It represents the page so that programs can change the document structure, style, and content. 
The DOM represents the document as nodes and objects. That way, programming languages can connect to the page.*

*A Web page is a document. This document can be either displayed in the browser window or as the HTML source. 
But it is the same document in both cases. The Document Object Model (DOM) represents that same document so it can be manipulated. 
The DOM is an object-oriented representation of the web page, which can be modified with a scripting language such as JavaScript.* [MDN Web Docs](https://developer.mozilla.org/en-US/docs/Web/API/Document_Object_Model/Introduction)

In the Selenium tests I use the Chrome Driver to read and manipulate the DOM.

_______________ 
#### 6) Explain how (and why it was necessary) you have solved "waiting" problems in your test 

When we perform a set of action on a web page the response time of the application might not be the same every time. 
The DOM has not been rendered before we start performing actions on the page.

*So, we need to inculcate a mechanism in our automation script that deals with such type of delays, this is nothing but Synchronization between our tool and the application under test.*

Synchronization/ Waits can be achieved in many different ways :

- Sleep method of Thread class
- Page Load timeout
- Script timeout
- Implicit Wait
- Explicit Wait

I use:

###### Implicit Wait :
Implicit wait is a mechanism, which is used to notify WebDriver instance to wait for specific time, if any element is readily not available on the webpage.

```
	driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

```
The synchronization mechanism is not associated with any particular web element, so it will be applicable for all the elements that our script interacts with, it is not highly appreciated.

##### Script timeout :
We can set the amount of time to wait for an asynchronous script to finish execution before throwing any error.

```
	driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);

```
Once added in the script, the WebDriver instance waits for 100 seconds for every asynchronous script to get executed on the web page before throwing an exception.

[seleniumatfingertips.wordpress.com](https://seleniumatfingertips.wordpress.com/tag/setscripttimeout/)
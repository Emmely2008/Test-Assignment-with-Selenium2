
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

Because this is an ReatJS application you're perfectly able to unit test your UI in all of these frameworks aswell. 
The test would probably move to Service test by using this because such tests usually test logic rather than the GUI.
 
We make a few test to check that the fron-end is "healthy". 
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

2) The GUI is what the stockholders/users see and they might be eager to change around a lot. 
It is also the easiest part to change but that might lead to the est needing to be rewritten so that that keeping the automated GUI tests creates a lot of maintenance.

_______________ 
#### 4) Demonstrate details in how to create a Selenium Test using the code for the exercise 

_______________ 
#### 5) Explain shortly about the DOM, and how you have read/manipulated DOM-elements in your test  

*The Document Object Model (DOM) is a programming interface for HTML and XML documents. 
It represents the page so that programs can change the document structure, style, and content. 
The DOM represents the document as nodes and objects. That way, programming languages can connect to the page.

A Web page is a document. This document can be either displayed in the browser window or as the HTML source. 
But it is the same document in both cases. The Document Object Model (DOM) represents that same document so it can be manipulated. 
The DOM is an object-oriented representation of the web page, which can be modified with a scripting language such as JavaScript.* [MDN Web Docs](https://developer.mozilla.org/en-US/docs/Web/API/Document_Object_Model/Introduction)

In the Selenium tests I use the Chrome Driver to read and manipulate the DOM.

_______________ 
#### 6) Explain how (and why it was necessary) you have solved "waiting" problems in your test 

*When we perform a set of action on a web page the response time of the application might not be the same every time. 
The DOM has not been rendered before we start performing actions on the page.

*So, we need to inculcate a mechanism in our automation script that deals with such type of delays, this is nothing but Synchronization between our tool and the application under test.

Synchronization/ Waits can be achieved in many different ways :

- Sleep method of Thread class
- Page Load timeout
- Script timeout
- Implicit Wait
- Explicit Wait
*
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
Selenium ([Homepage](http://www.seleniumhq.org/)) is a comprehensive testing framework for web applications which allows us to take screenshots of web pages programmatically amongst many other things.  It makes use of web browsers, such as FireFox ([Homepage](https://www.mozilla.org/en-US/firefox/)), to open the web page and then takes the screenshot.  This guarantees a genuine representation of the web page.

The following example illustrates how to do this.

```java
package com.javacreed.examples.misc;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example {

  public static final Logger LOGGER = LoggerFactory.getLogger(Example.class);

  public static void main(final String[] args) throws Exception {
    final String link = "http://www.javacreed.com/";
    final File screenShot = new File("screenshot.png").getAbsoluteFile();

    Example.LOGGER.debug("Creating Firefox Driver");
    final WebDriver driver = new FirefoxDriver();
    try {
      Example.LOGGER.debug("Opening page: {}", link);
      driver.get(link);

      Example.LOGGER.debug("Wait a bit for the page to render");
      TimeUnit.SECONDS.sleep(5);

      Example.LOGGER.debug("Taking Screenshot");
      final File outputFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(outputFile, screenShot);
      Example.LOGGER.debug("Screenshot saved: {}", screenShot);
    } finally {
      driver.close();
    }

    Example.LOGGER.debug("done");
  }
}
```

The delay of five seconds was only required as the page renders the latest posts using JavaScript after the page is loaded.  Thus without this delay we will not see the final result.

The code show above is available at: [https://github.com/javacreed/take-a-screenshot-of-webpage](https://github.com/javacreed/take-a-screenshot-of-webpage).  The readers can download or view all code from the above link.

The coder illustrated above makes use of the following Maven dependencies.

```xml
  <dependencies>
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-firefox-driver</artifactId>
      <version>2.45.0</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.5</version>
    </dependency>
  </dependencies>
```

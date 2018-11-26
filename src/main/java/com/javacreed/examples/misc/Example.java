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

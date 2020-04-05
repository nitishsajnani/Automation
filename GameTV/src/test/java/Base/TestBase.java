package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.fluent.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	/*
	 * WebDriver Properties GetOject methods
	 */
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUp() {
		loadConfigFile();
		loadObjectRepository();
		launchBrowser();
	}
	/*
	 * This is used for scrolling to the Games section
	 */

	public void scrollToApplication(String ele) {
		WebElement element = driver.findElement(By.xpath(OR.getProperty(ele)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * This function is used for identifying number of games in the application
	 */
	public int findCountOfGames() {
		List<WebElement> count = driver.findElements(By.xpath(OR.getProperty("gamesCount")));
		return count.size();
	}

	/*
	 * This function is used for fetching the below following details of games:
	 * Game Name, Game url, Game status code, Game number of tournaments
	 */

	public void getDetailsOfGameInTheApplication() {
		
		Reporter.log(String.format("|%-2s |%-70s  |%-150s |%-11s |%-15s|", "#","Game Name","Page URL","Page status","Tournament Count"),true);
		List<String> list = new ArrayList<String>();
		List<WebElement> count = driver.findElements(By.xpath(OR.getProperty("gamesCount")));
		for (int i = 0; i < count.size(); i++) {
			list.add(count.get(i).getAttribute("href"));
		}
		int S_No = 0;
		for (int j = 0; j < list.size(); j++) {
			S_No++;
			driver.get(list.get(j));
			String sn = String.format("|%-3d", S_No);
			String tournamentCount = String.format("|%-16s|", getTournamentCount());
			String gameName = String.format("|%-70s", getGameName());
			String statusCode = String.format("|%-11d", getResponseCode(getCurrentURL()));
			String gameURL = String.format("|%-150s", list.get(j));
			Reporter.log(sn + "" + gameName + " " + " " + gameURL + " " + statusCode + " " + tournamentCount, true);
		}
	}

	/*
	 * This function is used for fetching number of tournament of the game
	 */
	public String getTournamentCount() {
		return driver.findElement(By.cssSelector(OR.getProperty("counttournaments"))).getText();
	}

	/*
	 * This function is used for fetching name of the game
	 */
	public String getGameName() {
		{
			return driver.findElement(By.cssSelector(OR.getProperty("gameName"))).getText();
		}
	}

	/*
	 * This function is used for getting current url of Game
	 */
	public String getCurrentURL() {
		{
			return driver.getCurrentUrl();
		}
	}

	/*
	 * This function is used for getting response code of application
	 */
	public int getResponseCode(String url) {
		try {
			return Request.Get(url).execute().returnResponse().getStatusLine().getStatusCode();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * This function is used for loading the configuration file i.e
	 * Config.properties
	 */

	public void loadConfigFile() {
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			Reporter.log("Config file is loaded", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * This function is used for loading the object repository i.e
	 * Object.properties
	 */
	public void loadObjectRepository() {
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Object.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
			Reporter.log("Object file is loaded", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * This function is used for launch the browser and application
	 */
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		Reporter.log("Chrome browser has been launched");
		driver.navigate().to(Config.getProperty("testsiteurl"));
		Reporter.log("Navigated to : " + Config.getProperty("testsiteurl"), true);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
	}

	// @AfterSuite
	// public void tearDown(){

	// driver.quit();
	// log.debug("Test successfully executed !!!");

	// }

}

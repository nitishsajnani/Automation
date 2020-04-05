package TestCases;

import org.testng.Reporter;
import org.testng.annotations.Test;
import Base.TestBase;

public class GetDeatilsOFApps extends TestBase {

	@Test()
	public void TC001_ScrollApplicationToAvailableGames() throws InterruptedException {
		scrollToApplication("availableGames");
		Reporter.log("User scroll the application to the available games section", true);
	}

	@Test()
	public void TC002_CountGamesInTheApplication() {
		Reporter.log("Number of games in Game TV are: " + findCountOfGames(), true);
	}

	@Test
	public void TC003_LaunchGameAndPrintDetailsOfGame() {
		getDetailsOfGameInTheApplication();
	}
}

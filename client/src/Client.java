import java.io.File;
import java.io.IOException;

public class Client {
	public static void main(String[] args) {
		String homeDir = System.getProperty("user.home");
		if (homeDir == null) {
			System.err.println("Can't find home directory. Using current directory instead");
			File currentDir = new File("");
			try {
				homeDir = currentDir.getCanonicalPath();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!homeDir.endsWith(File.separator))
			homeDir = homeDir + File.separator;

		// get user login name
		// verify/create new user here and verify work is still valid

		File settingsFile = new File(homeDir + "Mandelbrot.settings");
		if (settingsFile.exists()) {
			// load file and settings
		}
		else {
			// create file and fill with default settings
		}

		do {
			// if no work, get work
			// start work in background

			// Thread worker = new Thread(new Worker());
			// worker.start();

			// prompt user for status/quit/view total work done/etc

			// when work ends, stop prompting and send work, loop
		}
		while (!quit);

		// save current status of work to disk

	}
}
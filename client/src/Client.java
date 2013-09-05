import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Client {
	private static Thread workerThread;
	private static Worker worker;

	private static boolean running;

	private class JSONArray {
		// this is only here until I set up the JSON package
	}

	public static void main(String[] args) {
		final String homeDir = getHomeDir();
		final String user = getUser();

		JSONArray work = loadWork(homeDir + "Mandelbrot.settings", user);
		if (work == null) {
			// get work
		}

		running = true;

		startWorker(work);

		Scanner sc = new Scanner(System.in);
		do {
			System.out.print("> ");
			String command = sc.nextLine().trim();

			if (command.equals("status")) {
				// display status
			}
			else if (command.equals("total")) {
				// display total work done
			}
			else if (command.equals("help")) {
				// display list of commands and descriptions
			}
			else if (command.equals("quit")) {
				running = false;
			}
			else {
				System.out.println("Command not recognized, use help to get a list of commands");
			}
		}
		while (running);
		sc.close();

		if (workerThread != null)
			workerThread.interrupt();
		try {
			workerThread.join(5000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getHomeDir() {
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

		return homeDir;
	}

	private static String getUser() {
		Scanner sc = new Scanner(System.in);

		String user = null;
		do {
			System.out.print("Username: ");
			user = sc.nextLine().trim();

			if (!Validation.isValidUser(user)) {
				char choice;
				do {
					System.out.print("Account does not exist, make it? (y/n): ");
					choice = sc.nextLine().toLowerCase().charAt(0);
				}
				while (choice != 'y' && choice != 'n');

				if (choice == 'y') {
					Console console = System.console();

					while (true) {
						char[] passwordArr = console.readPassword("Enter password");
						System.out.print("Email: ");
						String email = sc.nextLine().trim();

						if (Validation.createAccount(user, new String(passwordArr), email))
							break;
					}
				}
				else
					user = null;
			}
		}
		while (user == null);

		sc.close();
		return user;
	}

	private static JSONArray loadWork(String path, String user) {
		// currently not loading work

		return null;

		// File settingsFile = new File(path);
		// if (settingsFile.exists()) {
		// load file and settings
		// if settings file has work saved, check with server that it's still valid
		// }
		// else {
		// create file and fill with default settings
		// }
	}

	private static void startWorker(JSONArray work) {
		if (!running || work == null)
			return;

		worker = new Worker(work);
		workerThread = new Thread(worker);
		workerThread.start();
	}

	public static void onWorkFinished(Worker currentWorker, long[] iters) {
		// if (currentWorker == worker) -- this will be more useful if this ever has more than 1 worker running

		// if iters seems correct, upload

		// getWork();
		// startWorker(work);
	}
}
package utils;

import com.qa.vice.AppiumProcess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * An interface to Appium process management, starting and stopping
 */
public class Appium {

    /**
     * Creates a new appium server with the specified ports
     * @param port
     * @param cp
     * @param bp
     * @return the reference to the appium process created
     */
    public static Process startNewServer(int port, int cp, int bp) {
        String command = String.format("appium -p %s -cp %s -bp %s", port, cp, bp);

        try {
            System.out.println("Starting new Appium Server on Port " + port);
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (!input.readLine().contains("Appium REST http interface listener started on")) {
                continue;
            }
            System.out.println("New Appium Server started on Port " + port);
            return p;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error trying to start new Appium Server on Port " + port);
        }
        return null;
    }

    /**
     * Creates a new appium server without the specifying the ports
     * @return AppiumProcess, contains the appium process and the appium port
     */
    public static AppiumProcess startServer() {
        int appiumPort = Net.getNextFreePort();
        AppiumProcess p = new AppiumProcess();
        p.port = appiumPort;
        p.process = Appium.startNewServer(appiumPort, Net.getNextFreePort(), Net.getNextFreePort());
        return p;
    }

    public static void killServer(AppiumProcess process) {
        process.process.destroy();
    }

}

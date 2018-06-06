package utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    private static List<UDID> UDIDs;
    private static boolean isBusy = false;

    public static DesiredCapabilities getCapabilities(String udid) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.vice.viceforandroid");
        capabilities.setCapability("appActivity", "com.vice.sharedcode.UI.LaunchActivity");
        capabilities.setCapability("deviceName", "ONEPLUS A3000");
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("systemPort", Net.getNextFreePort());
        return capabilities;
    }

    /**
     * Gets the next available UDID and waits for the device to available
     * @return String
     * @throws InterruptedException
     */
    public static String getNextAvailableUDID() throws InterruptedException {
        if (UDIDs == null && !isBusy) {
            readUDIDs();
        }

        while (isBusy) {
            Thread.sleep((long) (Math.random() * 200));
        }

        String nextAvailableUUID = null;
        do {
            for (UDID udid : UDIDs) {
                if (!udid.lock) {
                    nextAvailableUUID = udid.UDID;
                    udid.lock = true;
                    break;
                }
            }
            Thread.sleep((long) ( (Math.random() +1)* 200));
        } while (nextAvailableUUID == null);

        return nextAvailableUUID;
    }

    public static void freeUDID(String udid) {
        for (UDID udidObject : UDIDs) {
            if (udidObject.UDID.equals(udid)) {
                udidObject.lock = false;
            }
        }
    }

    /**
     * Finds all the android devices and maps it to
     * UDIDs
     */
    private static void readUDIDs() {
        isBusy = true;
        UDIDs = new ArrayList<>();

        ADB.findDevices().forEach(adbDevice -> {
            UDID udid = new UDID();
            udid.lock = false;
            udid.UDID = adbDevice;
            UDIDs.add(udid);
        });
        isBusy = false;
    }
}

class UDID {
    public String UDID;
    public boolean lock;
}
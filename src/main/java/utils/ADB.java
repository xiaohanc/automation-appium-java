package utils;

import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;

import java.util.ArrayList;
import java.util.List;

/**
 * a Wrapper of JadbConnection class, to do a ADB connected devices lookup
 */
public class ADB {

    public static List<String> findDevices() {
        List<String> adbList = new ArrayList<>();

        try {
            JadbConnection jadb = new JadbConnection();
            List<JadbDevice> devices = jadb.getDevices();
            for (JadbDevice device : devices) {
                adbList.add(device.getSerial());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return adbList;
    }
}

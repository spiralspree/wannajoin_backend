package com.spiralspree.wannajoin;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class EnvUtility {

    private final static Logger LOGGER = Logger.getLogger(CustomLogger.class.getName());
    private static String hostName;

    public static String getHostName() {
        if (EnvUtility.hostName == null) {
            try
            {
                LOGGER.info("Trying to access host name...");
                EnvUtility.hostName = InetAddress.getLocalHost().getHostName();
            }
            catch (UnknownHostException ex)
            {
                LOGGER.severe("Could not access host name.");
                ex.printStackTrace();
                System.exit(1);
            }
        }
        return EnvUtility.hostName;
    }

    public static String getFormattedTimeDate() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
    }
}

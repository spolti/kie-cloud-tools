package org.kie.cekit.cacher.utils;

import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

public class UrlUtils {

    private static Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

    /**
     * test the given url
     * @param url
     * @return true if valid or false if not valid
     */
    public static boolean isUrlValid(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            log.info("ERROR - Failed to validate " + url + " message: " + e.getMessage());
        }
        return false;
    }

    /**
     * extract the file name for the given url
     * @param url
     * @return filename
     */
    public static String getFileName(String url) {
        try {
            String[] path = new URI(url).getPath().split("/");
            return path[path.length - 1];
        } catch (URISyntaxException e) {
            log.warning("Failed to retrieve the file name from url " + url);
            return e.getMessage();
        }
    }
}

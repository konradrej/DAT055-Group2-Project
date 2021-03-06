package app;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class for copying an IP-address from Config.txt.
 *
 * @author Jakob Ståhl
 * @version 2021-03-08
 */
public class ConfigReader {

    /**
     * returns a Map (String, String) with name as key
     * and the name´s address as value.
     * @return new Hashmap with text.
     */
    public static Map<String, String> readText() {

        Map<String, String> ipNum = new HashMap<>();

        Path path = Paths.get(System.getProperty("user.dir")).resolve("Config.txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path.toFile()));
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found. Using default config.");
            return new HashMap<>();
        }
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            ipNum.put(line.substring(0, line.indexOf("="))
                    , line.substring(line.indexOf("=") + 1));
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipNum;
    }
}

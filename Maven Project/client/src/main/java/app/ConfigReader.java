package app;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class for copying an IP-address from Config.txt.
 *
 * @author Jakob Ståhl, Konrad Rej
 * @version 2021-03-03
 */
public class ConfigReader {

    /**
     * returns a Map<String,String> with name as key
     * and the name´s address as value.
     */
    public static Map<String, String> readText() {
        int s;
        Map<String, String> ipNum = new HashMap<>();

        Path path = Paths.get(System.getProperty("user.dir")).resolve("Config.txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path.toFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

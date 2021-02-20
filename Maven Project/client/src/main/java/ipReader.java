import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ipReader {

    public static Map<String, String> readText(){

        Path path = Paths.get(System.getProperty("user.dir")).resolve("Config.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path.toFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }

        Map<String, String> ipNum = new HashMap<>();

        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (line != null) {
            ipNum.put(line.substring(0, line.indexOf("="))
                    ,line.substring(line.indexOf("=")+1));
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return ipNum;
    }

}

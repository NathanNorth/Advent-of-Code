import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    /**
     * Gets the input data as a List of String from the web, or from local if already got from web
     * @param day
     * @return
     */
    public static List<String> getInputLinesWeb(int day) {
        System.out.println("Attempting to get input lines...");

        //make local copies
        File exists = new File("./inputs/day" + day + ".txt");
        if (exists.exists()) {
            System.out.println("Using local copy!");
            return getInputLinesLocal(day);
        }

        System.out.println("Grabbing online copy!");

        try {
            HttpURLConnection con = (HttpURLConnection) new URL("https://adventofcode.com/2021/day/" + day + "/input").openConnection();
            con.setRequestMethod("GET");
            con.addRequestProperty("Cookie", "session=" + getKeys().get(0));
            Scanner s = new Scanner(con.getInputStream());
            String cache = "";
            while (s.hasNextLine()) {
                cache += s.nextLine() + "\n";
            }
            Files.writeString(Path.of("./inputs/day" + day + ".txt"), cache);
            return getInputLinesLocal(day);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> getInputLinesLocal(int day) {
        try {
            return Files.readAllLines(Path.of("./inputs/day" + day + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //keys.txt is stored in root dir and holds instance-specific data (eg. bot token)
    private static List<String> keys = null;

    public static List<String> getKeys() {
        if (keys == null) {
            try {
                keys = Files.readAllLines(Paths.get("./keys.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //filter out things we commented out in our keys
            for (int i = keys.size() - 1; i >= 0; i--) {
                if (keys.get(i).indexOf('#') == 0) keys.remove(i);
            }
        }
        return keys;
    }
}

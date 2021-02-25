import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DateComparator implements Comparator<String> {
    private static final List<String> months = new ArrayList<>(Arrays.asList(
            "january",
            "february",
            "march",
            "april",
            "may",
            "june",
            "july",
            "august",
            "september",
            "october",
            "november",
            "december"
    ));

    @Override
    public int compare(String item1, String item2) {
        String[] items1 = item1.split(" ");
        String[] items2 = item2.split(" ");

        if (!items1[1].equals(items2[1])) {
            return Integer.compare(
                    months.indexOf(items1[1].toLowerCase()),
                    months.indexOf(items2[1].toLowerCase())
            );
        } else {
            return Integer.compare(
                    Integer.parseInt(items1[0]),
                    Integer.parseInt(items2[0])
            );
        }
    }
}

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class ReadFileTests {

    @Test
    public void secondLineShouldHabAlloha() throws FileNotFoundException {
        File file = new File("src/test/resources/ReadFileTests.txt");
        Scanner scanner = new Scanner(file);
        List<String> strings = new ArrayList<>();

        while (scanner.hasNextLine()) {
            strings.add(scanner.nextLine());
        }

        assertTrue(strings.get(1).contains("аллоха"));
    }

}
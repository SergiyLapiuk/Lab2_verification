import org.example.Main;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;


public class Tests {

    private ArrayList<String> words;
    @BeforeSuite
    public static void setUpAll() {
        System.out.println("Running all tests...");
    }


    @Test(dataProvider = "provideWords", groups = {"cutting"})
    public void testSubStringsParametrized(String[] input, String[] expected) {
        String[] actual = Main.cutWord(input);
        assertEquals(actual, expected);
    }

    @DataProvider
    private static Object[][] provideWords() {
        return new Object[][]{
                {new String[]{"supercalifragilisticexpialidocious", "antidisestablishmentarianism"},
                        new String[]{"supercalifragilisticexpialidoc", "antidisestablishmentarianism"}}
        };
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testException() {
        Main.longestConsonant(null);
    }

    @BeforeMethod
    public void setUp() {
        words = new ArrayList<>();
        words.add("cat");
        words.add("dog");
        words.add("fish");
        words.add("elephant");
        words.add("lion");
        words.add("tiger");
    }

    @Test(groups = {"longestSubstring"})
    public void testGetWordWithLargestSubstring() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("fish");
        expected.add("elephant");
        assertEquals(expected, Main.getWordWithLargestSubstring(words));
    }

    @Test(groups = {"cutting"})
    public void testCutWord() {
        String[] words = new String[]{"apple", "banana", "kiwi", "pineapple"};
        String[] expected = new String[]{"apple", "banana", "kiwi", "pineapple"};
        Main.cutWord(words);
        String[] actual = new String[]{"apple", "banana", "kiwi", "pineapple"};
        assertArrayEquals(expected, actual);
    }

    @Test(groups = {"cutting"})
    public void UsingCutWord() {
        String[] words = new String[]{"dipalmitoylphosphatidylcholineaceton"};
        Main.cutWord(words);
        assertEquals(words[0].length(), 30);
    }

    @Test(groups = {"cutting"})
    public void testCutWordLongWordsArray() {
        String[] words = new String[]{"dipalmitoylphosphatidylcholineaceton",
                "Anotherdipalmitoylphosphatidylcholine"};
        String[] expected = new String[]{"dipalmitoylphosphatidylcholine", "Anotherdipalmitoylphosphatidyl"};
        Main.cutWord(words);
        assertArrayEquals(expected, words);
    }



    @Test(groups = {"longestSubstring"})
    public void testGetWordWithLargestSubstringAssertTrue() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("apple", "banana", "pear", "grapefruit"));
        ArrayList<String> result = Main.getWordWithLargestSubstring(words);
        Assert.assertTrue(result.contains("apple"));
    }
    @AfterMethod
    public void tearDown() {
        System.out.println("...");
        words = null;
    }

    @AfterClass
    public void tearDownClass() {
        System.out.println("...");
    }

    @AfterSuite
    public void tearDownAll() {
        System.out.println("All tests completed.");
    }
}
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EditorSkills
{
    private int testint;

    public EditorSkills(int testint) {
        this.testint = testint;
    }

    public static void main(String[] args) {

        if (weAreLucky()) {
            if (args.length > 0) {
                sayHello(args[0]);
            } else {
                sayHello();
            }
        } else {
            sayHello();
        }
    }

    private static void sayHello() {
        System.out.println("Hello world");
        System.out.println("How are you");
    }

    private static void sayHello(String arg) {
        System.out.println("Hello  " + arg);
    }

    private static boolean weAreLucky() {
        return ThreadLocalRandom.current().nextBoolean();
    }


    public static void main1(String[] args) {
        System.out.println("hi this is new class");
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
        }
        System.out.println("args = [" + Arrays.toString(args) + "]");
        List<String> names = Arrays.asList("First", "Second","Thrid");
        for (Iterator<String> it = names.iterator(); it.hasNext(); ) {
            String next = it.next();


        }

        try {
            System.out.println("Hello world");
            try {
                BufferedReader in = new BufferedReader(new FileReader("file"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        // introduce local variable using ctrl-alt-v
        boolean nextBoolean = ThreadLocalRandom.current().nextBoolean();

    }
}

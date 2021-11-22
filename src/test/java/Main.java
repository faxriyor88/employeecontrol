import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       File file=new File("informationaboutemployee");
        System.out.println(file.list().length);
    }
}

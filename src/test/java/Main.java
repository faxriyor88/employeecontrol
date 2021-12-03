import com.example.employeecontrol.model.enums.Permission;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Permission.VIEW.name();
        System.out.println(Permission.VIEW.name());
    }
}

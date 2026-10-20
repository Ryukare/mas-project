package oskar.rojek.s25723.masproject;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationLauncher {

    public static void main(String[] args) {
        Application.launch(ProjectApplication.class, args);
    }
}

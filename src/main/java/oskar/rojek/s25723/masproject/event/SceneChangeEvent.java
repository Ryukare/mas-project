package oskar.rojek.s25723.masproject.event;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class SceneChangeEvent extends ApplicationEvent{
    public String viewURL;

    public SceneChangeEvent(Stage stage, String viewURL) {
        super(stage);
        this.viewURL = viewURL;
    }
}
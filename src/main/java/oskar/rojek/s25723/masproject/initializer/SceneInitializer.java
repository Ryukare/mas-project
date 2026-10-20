package oskar.rojek.s25723.masproject.initializer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import oskar.rojek.s25723.masproject.event.SceneChangeEvent;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SceneInitializer implements ApplicationListener<SceneChangeEvent> {

    private final ApplicationContext springContext;

    @Override
    public void onApplicationEvent(SceneChangeEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(event.viewURL));
            loader.setControllerFactory(springContext::getBean);
            Parent parent = loader.load();
            StageInitializer.stage.setScene(new Scene(parent, 800, 600));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

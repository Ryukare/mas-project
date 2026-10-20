package oskar.rojek.s25723.masproject.initializer;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import oskar.rojek.s25723.masproject.event.StageReadyEvent;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    public static Stage stage;

    @Value("classpath:/fxml/ExpeditionView.fxml")
    private Resource expeditionFxml;

    private final ApplicationContext springContext;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(expeditionFxml.getURL());
            loader.setControllerFactory(springContext::getBean);
            Parent parent = loader.load();
            stage = event.getStage();
            stage.setTitle("Exploration System");
            stage.setScene(new Scene(parent, 800, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

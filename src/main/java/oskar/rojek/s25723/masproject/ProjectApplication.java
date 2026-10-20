package oskar.rojek.s25723.masproject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import oskar.rojek.s25723.masproject.ApplicationLauncher;
import oskar.rojek.s25723.masproject.event.StageReadyEvent;

/**
 * A main FX class, it's run from main class (JavafxSpringDemoApplication)
 */
public class ProjectApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        super.init();
        context = new SpringApplicationBuilder(ApplicationLauncher.class).run();
    }

    @Override
    public void start(Stage stage){
        context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.stop();
        Platform.exit();
    }
}

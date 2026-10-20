package oskar.rojek.s25723.masproject.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import oskar.rojek.s25723.masproject.initializer.StageInitializer;
import oskar.rojek.s25723.masproject.event.SceneChangeEvent;
import oskar.rojek.s25723.masproject.model.Ekspedycja;
import oskar.rojek.s25723.masproject.model.StatusEkspedycji;
import oskar.rojek.s25723.masproject.repository.EkspedycjaRepository;
import javafx.fxml.Initializable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
@Controller
public class ExpeditionController implements Initializable {

    private final ApplicationContext springContext;
    private final EkspedycjaRepository ekspedycjaRepository;

    public TableView<Ekspedycja> expeditionTableView;
    public TableColumn<Ekspedycja, String> nameColumn;
    public TableColumn<Ekspedycja, String> ruinColumn;
    public TableColumn<Ekspedycja, StatusEkspedycji> statusColumn;
    public TableColumn<Ekspedycja, LocalDate> startDateColumn;
    public TableColumn<Ekspedycja, LocalDate> endDateColumn;
    public Button planExpeditionButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Ekspedycja> ekspedycje = (List<Ekspedycja>) ekspedycjaRepository.findAll();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        ruinColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRuina().getNazwa()));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("dataRozpoczecia"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("dataZakonczenia"));

        for (Ekspedycja ekspedycja: ekspedycje) {
            expeditionTableView.getItems().add(0, ekspedycja);
        }
    }

    public void planExpedition(ActionEvent actionEvent) {
        springContext.publishEvent(new SceneChangeEvent(StageInitializer.stage, "/fxml/PlanExpeditionView.fxml"));
    }
}

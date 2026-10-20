package oskar.rojek.s25723.masproject.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import oskar.rojek.s25723.masproject.event.SceneChangeEvent;
import oskar.rojek.s25723.masproject.initializer.StageInitializer;
import oskar.rojek.s25723.masproject.model.*;
import oskar.rojek.s25723.masproject.repository.EkspedycjaRepository;
import oskar.rojek.s25723.masproject.repository.KrainaRepository;
import oskar.rojek.s25723.masproject.repository.OdkrywcaRepository;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
@Controller
public class PlanExpeditionController implements Initializable {

    private final ApplicationContext springContext;
    private final KrainaRepository krainaRepository;
    private final EkspedycjaRepository ekspedycjaRepository;
    private final OdkrywcaRepository odkrywcaRepository;

    public VBox mainContainer;

    public Label ruinChoiceLabel;
    public Label dataFillLabel;
    public Label teamChoiceLabel;
    public Label summaryLabel;

    public TableView<Kraina> realmTable;
    public TableColumn<Kraina, String> realmNameColumn;
    public TableColumn<Kraina, String> realmElementColumn;
    public VBox realmDetailsVBox;
    public Label realmName;
    public Label realmElement;
    public Label realmSize;
    public Label realmPopulation;
    public TableView<StarozytnaRuina>  ruinTable;
    public TableColumn<StarozytnaRuina, String> ruinNameColumn;
    public TableColumn<StarozytnaRuina, Integer> ruinDifficultyColumn;
    public TableColumn<StarozytnaRuina, Integer> ruinRequiredPowerColumn;

    public VBox formView;
    public TextField expeditionNameTextField;
    public Label nameError;
    public DatePicker expeditionStartDateDatePicker;
    public Label dateError;

    public VBox teamChoiceView;
    public TableView<Odkrywca> availableExplorerTable;
    public TableColumn<Odkrywca, String> explorerNameColumn;
    public TableColumn<Odkrywca, Integer> explorerPowerColumn;
    public TableView<Odkrywca> teamTable;
    public TableColumn<Odkrywca, String> explorerInTeamNameColumn;
    public TableColumn<Odkrywca, Integer> explorerInTeamPowerColumn;
    public Label powerLabel;

    public VBox summaryView;
    public Label expeditionNameSummaryLabel;
    public Label realmNameSummaryLabel;
    public Label ruinNameSummaryLabel;
    public Label startDateSummaryLabel;
    public Label powerSummaryLabel;
    public TableView<Odkrywca> teamTableSummary;
    public TableColumn<Odkrywca, String> explorerNameSummaryColumn;
    public TableColumn<Odkrywca, Integer> explorerPowerSummaryColumn;

    private Kraina chosenRealm;
    private StarozytnaRuina chosenRuin;
    private String expeditionName;
    private LocalDate expeditionStartDate;
    private Set<Odkrywca> team = new HashSet<>();
    private int teamPower;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        teamPower = 0;
        team = new HashSet<>();
        expeditionStartDate = null;
        chosenRuin = null;
        chosenRealm = null;
        expeditionName = null;

        List<Kraina> krainy = krainaRepository.findAllSorted();
        realmNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        realmElementColumn.setCellValueFactory(new PropertyValueFactory<>("dominujacyZywiol"));

        for (Kraina kraina : krainy) {
            realmTable.getItems().add(0, kraina);
        }

        ruinNameColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        ruinDifficultyColumn.setCellValueFactory(new PropertyValueFactory<>("poziomTrudnosci"));
        ruinRequiredPowerColumn.setCellValueFactory(new PropertyValueFactory<>("wymaganaMoc"));

        PropertyValueFactory<Odkrywca, String> nazwaProperty = new PropertyValueFactory<>("nazwa");
        explorerNameColumn.setCellValueFactory(nazwaProperty);
        explorerInTeamNameColumn.setCellValueFactory(nazwaProperty);
        explorerNameSummaryColumn.setCellValueFactory(nazwaProperty);

        explorerPowerColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getPoziomMocy()).asObject());
        explorerInTeamPowerColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getPoziomMocy()).asObject());
        explorerPowerSummaryColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getPoziomMocy()).asObject());

        showChosenRealmDetails();
        changeDateRange();
    }

    //Metoda, która zmienia widoczne szczegóły w zależności od wybranej krainy z tabeli
    private void showChosenRealmDetails() {
        realmTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Kraina>() {
            @Override
            public void changed(ObservableValue<? extends Kraina> observable, Kraina oldValue, Kraina newValue) {
                if (newValue != null) {
                    ruinTable.getItems().clear();
                    realmName.setText("Nazwa: " + newValue.getNazwa());
                    realmElement.setText("Dominujący żywioł: " + newValue.getDominujacyZywiol());
                    realmSize.setText("Wielkość: " + newValue.getWielkosc());
                    realmPopulation.setText("Populacja: " + newValue.getPopulacja());
                    for (StarozytnaRuina ruina : newValue.getRuiny()) {
                        ruinTable.getItems().add(ruina);
                    }
                    realmDetailsVBox.setVisible(true);
                } else {
                    realmDetailsVBox.setVisible(false);
                }
            }
        });
    }

    //Metoda, która sprawia, że tylko daty po obecnej są możliwe do wyboru
    private void changeDateRange(){
        expeditionStartDateDatePicker.getEditor().setDisable(true);
        expeditionStartDateDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setVisible(!item.isBefore(LocalDate.now()));
                    }
                };
            }
        });
    }


    //Metoda, która ustawia parametr wybranaRuina w zależności od wybranej ruiny w tabeli oraz przechodzi do następnego kroku
    public void continueToForm(ActionEvent actionEvent) {
        chosenRuin = ruinTable.getSelectionModel().getSelectedItem();
        chosenRealm = realmTable.getSelectionModel().getSelectedItem();
        if (chosenRuin != null) {
            formView.setVisible(true);
            mainContainer.getChildren().setAll(formView);

            ruinChoiceLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: gray;");
            dataFillLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: black;");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Brak ruiny");
            alert.setHeaderText(null);
            alert.setContentText("Nie wybrano żadnej ruiny.");
            alert.showAndWait();
        }
    }


    public void addAvailableExplorersToTable(){
        List<Odkrywca> odkrywcy = (List<Odkrywca>) odkrywcaRepository.findAll();

        for (Odkrywca odkrywca: odkrywcy) {
            boolean isAvailable = true;
            for (Ekspedycja ekspedycja : odkrywca.getEkspedycje()) {
                if (ekspedycja.getDataZakonczenia() == null) {
                    isAvailable = false;
                    break;
                }
            }
            if(isAvailable){
                availableExplorerTable.getItems().add(odkrywca);
            }
        }
    }

    public void continueToTeamChoice(ActionEvent actionEvent) {
        expeditionName = expeditionNameTextField.getText();
        expeditionStartDate = expeditionStartDateDatePicker.getValue();

        boolean errorOccured = false;

        if(expeditionName == null || expeditionName.length() < 2){
            nameError.setVisible(true);
            errorOccured = true;
        }
        else {
            nameError.setVisible(false);
        }

        if(expeditionStartDate == null){
            dateError.setVisible(true);
            errorOccured = true;
        }
        else {
            dateError.setVisible(false);
        }

        if(!errorOccured){
            teamChoiceView.setVisible(true);
            mainContainer.getChildren().setAll(teamChoiceView);
            dataFillLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: gray;");
            teamChoiceLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: black;");
            powerLabel.setText("Wymagana Moc: " + teamPower + "/" + chosenRuin.getWymaganaMoc());
            addAvailableExplorersToTable();
        }
    }

    public void addToTeam(ActionEvent actionEvent) {
        Odkrywca selectedOdkrywca = availableExplorerTable.getSelectionModel().getSelectedItem();
        if (selectedOdkrywca != null) {
            availableExplorerTable.getItems().remove(selectedOdkrywca);
            teamTable.getItems().add(selectedOdkrywca);
            team.add(selectedOdkrywca);
            teamPower += selectedOdkrywca.getPoziomMocy();
            powerLabel.setText("Wymagana Moc: " + teamPower + "/" + chosenRuin.getWymaganaMoc());
        }
    }
    public void showNextUseCasePopup(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dodanie nowego odkrywcy");
        alert.setHeaderText(null);
        alert.setContentText("Uruchomienie przypadku użycia: Wprowadzenie danych o nowym odkrywcy");
        alert.showAndWait();
    }

    public void removeFromTeam(ActionEvent actionEvent) {
        Odkrywca selectedOdkrywca = teamTable.getSelectionModel().getSelectedItem();
        if (selectedOdkrywca != null) {
            teamTable.getItems().remove(selectedOdkrywca);
            availableExplorerTable.getItems().add(selectedOdkrywca);
            team.remove(selectedOdkrywca);
            teamPower -= selectedOdkrywca.getPoziomMocy();
            powerLabel.setText("Wymagana Moc: " + teamPower + "/" + chosenRuin.getWymaganaMoc());
        }
    }

    public void continueToSummary(ActionEvent actionEvent) {
        if(teamPower >= chosenRuin.getWymaganaMoc()){
            summaryView.setVisible(true);
            mainContainer.getChildren().setAll(summaryView);
            teamChoiceLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: gray;");
            summaryLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;-fx-text-fill: black;");

            expeditionNameSummaryLabel.setText("Nazwa ekspedycji: " + expeditionName);
            realmNameSummaryLabel.setText("Kraina: " + chosenRealm.getNazwa());
            ruinNameSummaryLabel.setText("Ruina: " + chosenRuin.getNazwa());
            startDateSummaryLabel.setText("Data rozpoczęcia: " + expeditionStartDate);
            powerSummaryLabel.setText("Moc: " + teamPower + "/" + chosenRuin.getWymaganaMoc());
            teamTableSummary.getItems().addAll(team);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Zbyt mało mocy");
            alert.setHeaderText(null);
            alert.setContentText("Drużyna odkrywców ma zbyt mało mocy, aby wziąć udział w eksploracji.");
            alert.showAndWait();
        }
    }

    public void finishPlanning(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Utworzenie Ekspedycji");
        alert.setHeaderText(null);
        alert.setContentText("Ekspedycja została poprawnia zaplanowana i zapisana do systemu");
        alert.showAndWait();

        Ekspedycja ekspedycja = Ekspedycja.builder()
                .nazwa(expeditionName)
                .dataRozpoczecia(expeditionStartDate)
                .status(StatusEkspedycji.Zaplanowana)
                .ruina(chosenRuin)
                .druzyna(team)
                .build();

        chosenRuin.getEkspedycje().add(ekspedycja);

        ekspedycjaRepository.save(ekspedycja);

        springContext.publishEvent(new SceneChangeEvent(StageInitializer.stage, "/fxml/ExpeditionView.fxml"));
    }
}

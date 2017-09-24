package sample.firstLab;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class First {
    private void showResult(Button button){
        button.setVisible(true);
        button.setDisable(false);
    }
    private void hideResult (Button button){
        button.setVisible(false);
        button.setDisable(true);
    }
    public Parent entity(Stage primaryStage){

        Tab tabForFirstLab = new Tab();
        Tab tabForSecondLab = new Tab();
        GridPane rootForFirstLab = new GridPane();
        GridPane rootForSecondLab = new GridPane();
        AnchorPane anchorForTopPart = new AnchorPane();
        AnchorPane anchorForLeftPart = new AnchorPane();
        AnchorPane anchorForGridPartInTheCenter = new AnchorPane();
        Button showTriangle = new Button("Треугольник");
        Button showResults = new Button("Корни");
        Button showDeterminant = new Button("Определитель");
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        TabPane mainRoot = new TabPane();
        MenuItem fileOpen = new MenuItem("Open");
        MenuItem fileExit = new MenuItem("Exit");
        Label sizeLabel = new Label("Size: ");
        TextField sizeTextField = new TextField();
        VBox vBoxForLeftPart = new VBox();



        tabForFirstLab.setText("#1");
        tabForFirstLab.setContent(rootForFirstLab);
        tabForFirstLab.setClosable(false);
        mainRoot.getTabs().add(tabForFirstLab); //Закладка для 1 лабораторки

        mainRoot.getTabs().add(tabForSecondLab); //Закладка для 2 лабораторки
        tabForSecondLab.setText("#2");
        tabForSecondLab.setContent(rootForSecondLab);
        tabForSecondLab.setClosable(false);


        rootForFirstLab.setGridLinesVisible(true);
        rootForFirstLab.add(anchorForTopPart,0,0); //Верхняя часть для менюбара
        HBox hBox = new HBox();
        hBox.getChildren().addAll(showResults,showTriangle, showDeterminant);
        hideResult(showResults);
        hideResult(showDeterminant);
        hideResult(showTriangle);

        rootForFirstLab.add(hBox,1,0);

        RowConstraints rowConstraintsForTopPart = new RowConstraints();
        rowConstraintsForTopPart.setPrefHeight(35.0);
        rowConstraintsForTopPart.setMaxHeight(35.0);
        rowConstraintsForTopPart.setMinHeight(35.0);

        rootForFirstLab.add(anchorForLeftPart,0,1); //Левая часть для ввода размера
        rootForFirstLab.add(anchorForGridPartInTheCenter,1,1);
        RowConstraints rowConstraintsForGridPane = new RowConstraints();
        rowConstraintsForGridPane.setMinHeight(400);
        rowConstraintsForGridPane.setPrefHeight(700);

        ColumnConstraints columnConstraints0 = new ColumnConstraints();
        columnConstraints0.setPrefWidth(130);

        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        columnConstraints1.setPrefWidth(770);


        rootForFirstLab.getColumnConstraints().addAll(columnConstraints0,columnConstraints1);
        rootForFirstLab.getRowConstraints().addAll(rowConstraintsForTopPart,rowConstraintsForGridPane);

        anchorForTopPart.getChildren().add(menuBar);
        menuBar.getMenus().addAll(fileMenu);
        menuBar.setPrefHeight(35);
        menuBar.setMaxHeight(35);
        menuBar.setMinHeight(35);
        fileMenu.getItems().addAll(fileOpen, fileExit);
        menuBar.setLayoutX(20);

        HBox  hBox2 = new HBox();
        Button button = new Button("Посчитать");
        Button randomButtom = new Button("Рандом");

        sizeLabel.setPrefSize(80,-1);
        sizeTextField.setPrefSize(50,-1);
        hBox2.getChildren().addAll(sizeLabel,sizeTextField);
        vBoxForLeftPart.getChildren().addAll(hBox2, button, randomButtom);
        anchorForLeftPart.getChildren().addAll(vBoxForLeftPart);
        vBoxForLeftPart.setSpacing(5);

        Matrix globalMatrix = new Matrix();
        String numberMatcher = "^?\\d+$";
        sizeTextField.textProperty().addListener((observableValue, oldText, newText) -> {
            hideResult(showResults);
            hideResult(showTriangle);
            hideResult(showDeterminant);
            if (!newText.isEmpty()) {
                if (!newText.matches(numberMatcher)) {
                    sizeTextField.setText(oldText);
                } else {
                    try {
                        int value = Integer.parseInt(newText);
                        if (value<=20) {

                            sizeTextField.setText(String.valueOf(value));
                            globalMatrix.setSize(value);
                            globalMatrix.clear();
                            anchorForGridPartInTheCenter.getChildren().clear();
                            anchorForGridPartInTheCenter.getChildren().addAll(globalMatrix.getMatrix());
                        }
                        else{
                            sizeTextField.setText(oldText);
                        }
                    } catch (NumberFormatException e) {
                        sizeTextField.setText(oldText);
                    }
                }
            }else{
                globalMatrix.setSize(0);
                anchorForGridPartInTheCenter.getChildren().clear();
            }
        });
        button.setOnAction((event)->{
            globalMatrix.setList();
            globalMatrix.calculate();
            showResult(showTriangle);
            showResult(showResults);
            showResult(showDeterminant);
        });
        randomButtom.setOnAction((event -> {
            globalMatrix.setList(globalMatrix.generateSystem());
            globalMatrix.fillTextField();
            globalMatrix.calculate();
            showResult(showTriangle);
            showResult(showResults);
            showResult(showDeterminant);
        }));
        fileOpen.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(primaryStage);
            System.out.println(file.getAbsolutePath());
            globalMatrix.setList(file.getAbsolutePath());
            globalMatrix.calculate();
            showResult(showTriangle);
            showResult(showResults);
            showResult(showDeterminant);
        });
        showTriangle.setOnAction(event -> {
            anchorForGridPartInTheCenter.getChildren().clear();
            anchorForGridPartInTheCenter.getChildren().add(new TextArea(globalMatrix.printSystem()));
            ((TextArea)anchorForGridPartInTheCenter.getChildren().get(0)).setEditable(false);
        });
        showResults.setOnAction(event -> {
            anchorForGridPartInTheCenter.getChildren().clear();
            anchorForGridPartInTheCenter.getChildren().add(new TextArea(globalMatrix.printResult()));
            ((TextArea)anchorForGridPartInTheCenter.getChildren().get(0)).setEditable(false);
        });
        showDeterminant.setOnAction(event -> {
            anchorForGridPartInTheCenter.getChildren().clear();
            anchorForGridPartInTheCenter.getChildren().add(new TextArea(String.valueOf(globalMatrix.getDeterminant())+"\n"+globalMatrix.printResiduals()));
            ((TextArea)anchorForGridPartInTheCenter.getChildren().get(0)).setEditable(false);
        });

        return mainRoot;
    }
}

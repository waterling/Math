package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.firstLab.First;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        TabPane mainRoot = new TabPane();
//        Tab tabForFirstLab = new Tab();
//        Tab tabForSecondLab = new Tab();
//        GridPane rootForFirstLab = new GridPane();
//        GridPane rootForSecondLab = new GridPane();
//        GridPane matrix = new GridPane();
//        AnchorPane anchorForTopPart = new AnchorPane();
//        AnchorPane anchorForLeftPart = new AnchorPane();
//        AnchorPane anchorForGridPartInTheCenter = new AnchorPane();
//        MenuBar menuBar = new MenuBar();
//        Menu file = new Menu("File");
//        Menu method = new Menu("Method");
//        Menu help = new Menu("help");
//        MenuItem methodFirst = new MenuItem("First");
//        MenuItem methodSecond = new MenuItem("Second");
//        MenuItem methodThird = new MenuItem("Third");
//        MenuItem methodFourth = new MenuItem("Fourth");
//
//        MenuItem fileOpen = new MenuItem("Open");
//        MenuItem fileExit = new MenuItem("Exit");
//        Label labelColumn = new Label("Columns: ");
//        Label labelRow = new Label("Rows: ");
//        TextField textFieldColumn = new TextField();
//        TextField textFieldRow = new TextField();
//        VBox vBoxForLeftPart = new VBox();
//
//
//
//        tabForFirstLab.setText("#1");
//        tabForFirstLab.setContent(rootForFirstLab);
//        tabForFirstLab.setClosable(false);
//        mainRoot.getTabs().add(tabForFirstLab); //Закладка для 1 лабораторки
//
//        mainRoot.getTabs().add(tabForSecondLab); //Закладка для 2 лабораторки
//        tabForSecondLab.setText("#2");
//        tabForSecondLab.setContent(rootForSecondLab);
//        tabForSecondLab.setClosable(false);
//
//
//        rootForFirstLab.setGridLinesVisible(true);
//        rootForFirstLab.add(anchorForTopPart,0,0,2,1); //Верхняя часть для менюбара
//        anchorForTopPart.setStyle("-fx-background-color:#1d1d1d");
//        RowConstraints rowConstraintsForTopPart = new RowConstraints();
//        rowConstraintsForTopPart.setPrefHeight(35.0);
//        rowConstraintsForTopPart.setMaxHeight(35.0);
//        rowConstraintsForTopPart.setMinHeight(35.0);
//
//        rootForFirstLab.add(anchorForLeftPart,0,1); //Левая часть для ввода размера
//        rootForFirstLab.add(anchorForGridPartInTheCenter,1,1);
//        RowConstraints rowConstraintsForGridPane = new RowConstraints();
//        rowConstraintsForGridPane.setMinHeight(400);
//        rowConstraintsForGridPane.setPrefHeight(400);
//
//        ColumnConstraints columnConstraints0 = new ColumnConstraints();
//        columnConstraints0.setPrefWidth(130);
//
//        ColumnConstraints columnConstraints1 = new ColumnConstraints();
//        columnConstraints1.setPrefWidth(470);
//
//
//        rootForFirstLab.getColumnConstraints().addAll(columnConstraints0,columnConstraints1);
//        rootForFirstLab.getRowConstraints().addAll(rowConstraintsForTopPart,rowConstraintsForGridPane);
//
//        anchorForTopPart.getChildren().add(menuBar);
//        menuBar.getMenus().addAll(file,method,help);
//        menuBar.setPrefHeight(35);
//        menuBar.setMaxHeight(35);
//        menuBar.setMinHeight(35);
//        file.getItems().addAll(fileOpen, fileExit);
//        method.getItems().addAll(methodFirst,methodSecond,methodThird,methodFourth);
//        menuBar.setLayoutX(20);
//
//        HBox  hBox1 = new HBox();
//        HBox  hBox2 = new HBox();
//        hBox1.getChildren().addAll(labelColumn,textFieldColumn);
//        labelColumn.setPrefSize(80,-1);
//        textFieldColumn.setPrefSize(50, -1);
//        labelRow.setPrefSize(80,-1);
//        textFieldRow.setPrefSize(50,-1);
//        hBox2.getChildren().addAll(labelRow,textFieldRow);
//        vBoxForLeftPart.getChildren().addAll(hBox1,hBox2);
//        anchorForLeftPart.getChildren().addAll(vBoxForLeftPart);
//        vBoxForLeftPart.setSpacing(5);
//
//        anchorForGridPartInTheCenter.getChildren().addAll(matrix);

        Parent mainRoot = new First().entity(primaryStage);
        primaryStage.setTitle("Math");
        primaryStage.setScene(new Scene(mainRoot, 600, 485));
        primaryStage.show();
//        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

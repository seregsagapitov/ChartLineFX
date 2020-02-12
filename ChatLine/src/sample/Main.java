package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ChatLine");


        ObservableList<Integer> numbers = FXCollections.observableArrayList(Arrays.asList(94, 47, -43, -91, -35, 89, -20, 43, -87, 5, -22, 95, -70, -76, -64, -86, -48, 54, -50, 44, 80, 82, 62, -14, 82, -93, -9, 68, -3, 25, 55, 57, -16, -54, 53, -1, 58, -41, -59, -56, 22, -13, -68, 6, -97, 76, 32, -38, -48, 69, -95, 19, 41, 26, 86, -62, 82, 7, -60, -68, 33, -1, 1, -51, -57, -100, 27, -29, -76, 13, 43, -15, -30, 30, 65, 85, 65, 97, 53, 39, -35, 38, -39, 55, -92, 51, -74, -66, -76, -84, 76, 84, -1, -35, -90, 63, -85, 15, -28, 52));
        System.out.println(numbers.size());
        ObservableList<Integer> numbers1 = FXCollections.observableArrayList();
        for (int i = 0; i < numbers.size(); i++) {
            //numbers.add((-100) + (int) (Math.random() * 200));
            numbers1.add(i + 1);
        }
        System.out.println(numbers);
        System.out.println(numbers1);
        HBox hbox_lineChart;
        ComboBox<Integer> begin_int = null;
        ComboBox<Integer> end_int = null;
        Button start;
        Button escape;


        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Элемент");
        //xAxis.setAutoRanging(false);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Значение");
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setAnimated(true);
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Анализ");
        lineChart.setTitle("Анализ");
        for (int i = 10; i <= numbers.size(); i++) {
            dataSeries.getData().add(new XYChart.Data(i, numbers.get(i)));
        }
        lineChart.getData().add(dataSeries);

        begin_int = new ComboBox<>(numbers1);
        begin_int.setValue(numbers1.get(0));
        end_int = new ComboBox<>(numbers1);
        end_int.setValue(numbers1.size());

        start = new Button("Расчитать");
        escape = new Button("Сброс");
        hbox_lineChart = new HBox(begin_int, end_int, start, escape);
        VBox vbox_line = new VBox(lineChart, hbox_lineChart);


        ComboBox<Integer> finalBegin_int = begin_int;
        ComboBox<Integer> finalEnd_int = end_int;


        start.setOnAction(value -> {
            dataSeries.getData().removeAll(Collections.singleton(lineChart.getData().setAll()));

//                NumberAxis xAxis1 = new NumberAxis(finalBegin_int.getValue(), finalEnd_int.getValue(), 1);
//                xAxis1.setLabel("Элемент");
//                NumberAxis yAxis1 = new NumberAxis();
//                yAxis1.setLabel("Значение");
            //LineChart lineChart1 = new LineChart(xAxis1, yAxis1);
            xAxis.setAutoRanging(false);
            xAxis.setLowerBound(finalBegin_int.getValue()-1);
            xAxis.setUpperBound(finalEnd_int.getValue()+1);
            xAxis.setTickUnit(1);
            XYChart.Series dataSeries2 = new XYChart.Series();
            dataSeries2.setName("Анализ");
            //lineChart.setTitle("Анализ");
            for (int i = finalBegin_int.getValue(); i < finalEnd_int.getValue(); i++) {
                dataSeries2.getData().add(new XYChart.Data(i, numbers.get(i)));
            }
            lineChart.getData().add(dataSeries2);

        });

        escape.setOnAction(value -> {
            try {
                start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });


        primaryStage.setScene(new Scene(vbox_line, 500, 575));
        primaryStage.show();


    }




    public static void main(String[] args) {
        launch(args);
    }
}

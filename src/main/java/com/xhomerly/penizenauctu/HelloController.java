package com.xhomerly.penizenauctu;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloController {
    @FXML private TextField inputMoneyField, yearPercentField, numOfYearsField;
    @FXML private TextArea textArea;
    int year;
    double percent, money;

    public void calc() {
        year = Integer.parseInt(numOfYearsField.getText());
        percent = Double.parseDouble(yearPercentField.getText());
        percent /= 100;
        money = Double.parseDouble(inputMoneyField.getText());
    }

    public void createGraph() {
        calc();
        Stage stage = new Stage();
        VBox root = new VBox(10);

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i <= year; i++) {
            money += money*percent;
            series.getData().add(new XYChart.Data<>(i, money));
        }
        series.setName("Růst peněz");

        lineChart.getData().add(series);
        root.getChildren().add(lineChart);
        Scene scene = new Scene(root);
        stage.setTitle("Graf růstu peněz");
        stage.setScene(scene);
        stage.show();
    }

    public void createTable() {
        calc();
        StringBuilder resultText = new StringBuilder();
        resultText.append("Rok\tČástka\n");

        for (int i = 0; i <= year; i++) {
            resultText.append(i+"\t");
            money += money*percent;
            resultText.append(String.format("%.2f", money)+"\n");
        }

        textArea.setText(resultText.toString());
    }

    public void copy() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(textArea.getText());
        clipboard.setContent(content);
    }
}
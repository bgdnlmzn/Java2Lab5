package com.example.java2lab5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;

public class HelloApplication extends Application {

    final int MAX_COUNT_OF_ANSWERS = 10;
    int questionNumber = 1;
    int correctAnswers = 0;
    Task currentTask = TaskGenerator.generateRandomTask();
    @Override
    public void start(Stage stage) throws IOException {

        Label questionNumberLabel = new Label("Задание №1");

        Label inputLabel = new Label();

        inputLabel.setText(currentTask.toString());

        inputLabel.setPadding(new Insets(0, 20, 0, 0));

        TextField inputTextField = new TextField();
        inputTextField.setMaxWidth(300);

        HBox inputHBox = new HBox(inputLabel, inputTextField);
        inputHBox.setAlignment(Pos.CENTER);

        Canvas progressCanvas = new Canvas(300, 20);
        GraphicsContext gc = progressCanvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, 300, 20);

        Alert alert = new Alert(AlertType.NONE, "", ButtonType.CLOSE);

        Button actionButton = new Button("Далее");
        actionButton.setPadding(new Insets(10, 15,10,15));
        actionButton.setOnAction(e -> {
            String answer = inputTextField.getText();
            inputTextField.setText("");
            if(currentTask.isCorrectAnswer(Integer.parseInt(answer))) {
                correctAnswers++;
                gc.setFill(Color.LIME);
            }
            else gc.setFill(Color.RED);
            gc.fillRect((questionNumber - 1) * 30, 0, 30, 20);

            if(questionNumber == MAX_COUNT_OF_ANSWERS){ // Заканчиваем игру
                questionNumber = 0;
                actionButton.setText("Далее");
                alert.setContentText("Игра завершена. Количество правильных ответов: " + correctAnswers);
                alert.showAndWait();
                gc.setFill(Color.LIGHTGRAY);
                gc.fillRect(0, 0, 300, 20);
                return;
            }

            questionNumber++;

            currentTask = TaskGenerator.generateRandomTask();
            inputLabel.setText(currentTask.toString());
            questionNumberLabel.setText("Задание №" + questionNumber);

        });

        VBox rootBox = new VBox(questionNumberLabel, inputHBox, actionButton, progressCanvas);
        rootBox.setAlignment(Pos.CENTER);
        rootBox.setMinSize(450, 150);
        rootBox.setPadding(new Insets(20));
        rootBox.setSpacing(30);

        Scene scene = new Scene(rootBox);

        stage.setTitle("Тест на знание бинарных операций");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


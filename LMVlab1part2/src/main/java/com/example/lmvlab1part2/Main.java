package com.example.lmvlab1part2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class Main extends Application {
    Label resultLabel = new Label();


    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        HBox topBox = new HBox();

        // Створення меню
        ButtonBar buttonBar = new ButtonBar();
        Button modeButton = new Button("MODE");
        Button eraseButton = new Button("ERASE");
        Button copyButton = new Button("COPY");
        Button exitButton = new Button("EXIT");
        buttonBar.getButtons().addAll(modeButton, eraseButton, copyButton, exitButton);
        topBox.getChildren().add(buttonBar);
        root.setTop(topBox);
        // Додавання resultLabel на сцену внизу
        VBox bottomBox = new VBox(resultLabel);
        bottomBox.setPadding(new Insets(10));
        root.setBottom(bottomBox);

        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
        modeButton.setOnAction(event -> {
            // Код для виконання команди MODE
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "MODE");
                Process process = processBuilder.start();
                String result = new String(process.getInputStream().readAllBytes(), "cp866");
                resultLabel.setText(result);
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        // Код для виконання команди ERASE
        eraseButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Видалення файлу");
            dialog.setHeaderText("Введіть шлях до файлу, який потрібно видалити:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(filePath -> {
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "ERASE", filePath);
                    Process process = processBuilder.start();
                    String output = new String(process.getInputStream().readAllBytes(), "cp866");
                    resultLabel.setText("Файл видалено");
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        // Код для виконання команди COPY
        copyButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Копіювання");
            dialog.setHeaderText("Введіть шлях до файлу, який потрібно скопіювати:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(sourcePath -> {
                try {
                    Clipboard clipboard = Clipboard.getSystemClipboard();
                    ClipboardContent content = new ClipboardContent();
                    content.putFiles(Collections.singletonList(new File(sourcePath)));
                    clipboard.setContent(content);
                    resultLabel.setText("Файл скопійований у буфер обмінну!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        });


        exitButton.setOnAction(event -> {
            primaryStage.close();
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}

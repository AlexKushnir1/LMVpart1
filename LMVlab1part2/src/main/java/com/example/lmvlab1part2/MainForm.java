package com.example.lmvlab1part2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainForm extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Створюємо головне вікно програми
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Створюємо меню
        MenuBar menuBar = new MenuBar();

        // Створюємо пункт меню "Вихід"
        MenuItem exitMenuItem = new MenuItem("Вихід");
        exitMenuItem.setOnAction(event -> primaryStage.close());

        // Розміщуємо панель меню в головному вікні
        root.setTop(menuBar);

        // Встановлюємо сцену та відображаємо вікно
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
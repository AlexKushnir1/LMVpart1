module com.example.lmvlab1part2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.lmvlab1part2 to javafx.fxml;
    exports com.example.lmvlab1part2;
}
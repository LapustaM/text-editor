module com.example.texteditorproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.texteditorproject to javafx.fxml;
    exports com.example.texteditorproject.ui; // Експорт пакета для JavaFX
    exports com.example.texteditorproject;
    exports com.example.texteditorproject.model;
    opens com.example.texteditorproject.model to com.google.gson;
}
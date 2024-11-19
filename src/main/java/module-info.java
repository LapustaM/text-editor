module com.example.texteditorproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.texteditorproject to javafx.fxml;
    exports com.example.texteditorproject.ui; // Експортуємо пакет для JavaFX
    exports com.example.texteditorproject;
}
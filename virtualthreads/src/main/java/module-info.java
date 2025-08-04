module org.example.virtualthreads {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.virtualthreads to javafx.fxml;
    exports org.example.virtualthreads;
}
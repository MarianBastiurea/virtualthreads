module org.example.virtualthreads {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires java.sql;


    opens org.example.virtualthreads to javafx.fxml;
    exports org.example.virtualthreads;
}
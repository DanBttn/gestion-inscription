module sio.tp7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens sio.tp7.Model;

    opens sio.tp7 to javafx.fxml;
    exports sio.tp7;
}
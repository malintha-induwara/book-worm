module lk.ijse.bookworm {
    requires MaterialFX;
    requires javafx.controls;
    requires javafx.fxml;

    opens lk.ijse.bookworm to javafx.graphics, javafx.fxml;
    opens lk.ijse.bookworm.controller to javafx.fxml;
    opens lk.ijse.bookworm.tm to javafx.base;
    exports lk.ijse.bookworm.controller;
}
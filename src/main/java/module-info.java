module com.xhomerly.penizenauctu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.xhomerly.penizenauctu to javafx.fxml;
    exports com.xhomerly.penizenauctu;
}
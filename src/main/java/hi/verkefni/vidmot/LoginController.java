package hi.verkefni.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

public class LoginController extends DialogPane {

    @FXML
    private TextField fxUserId, fxPassword;

    public LoginController(){
        // Load interface of the dialog
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    /**
     * @return - Pair of username and password if Login button pressed,
     *           otherwise null
     */
    public Pair<String, String> getUser(){
        Dialog<Pair<String, String>> d = new Dialog<>();
        d.setDialogPane(this);
        d.setResultConverter(b -> {
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return new Pair<String, String>(fxUserId.getText(), fxPassword.getText());
            } else {
                return null;
            }
        });
        Optional<Pair<String, String>> utkoma = d.showAndWait();
        return utkoma.orElse(null);
    }
}
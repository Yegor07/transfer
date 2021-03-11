package ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.BigDecimalStringConverter;
import presentation.MainPresenterImplementation;
import ui.view_interface.MainViewController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainViewControllerImplementation implements MainViewController {

    private MainPresenterImplementation presenter = new MainPresenterImplementation(this);


    @FXML
    private TextField send_text, receive_text;
    @FXML
    private Label rate_lable;
    @FXML
    private ImageView send_image, receive_image;


    public void setRate(String rate) {
        rate_lable.setText(rate + " PLN");
    }

    public void alertGetRate() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You have problem with internet connection!");
        ButtonType buttonTypeOne = new ButtonType("Exit");
        alert.getButtonTypes().setAll(buttonTypeOne);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            System.exit(1);
        }
    }

    private void initEditTextFields(TextField send, TextField receive, Boolean inversely) {

        final Pattern pattern = Pattern.compile("(([0]|([1-9][0-9]*))(\\.[0-9]{0,2})?)?");
        send.setTextFormatter(new TextFormatter<>(new BigDecimalStringConverter(), BigDecimal.ZERO, change -> {
            final Matcher matcher = pattern.matcher(change.getControlNewText());
            return (matcher.matches() || matcher.hitEnd()) ? change : null;
        }));

        send.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() != 0) {
                if (send.focusedProperty().get()) {
                    presenter.transfer(receive::setText, newValue, inversely);
                }
            } else {
                receive.clear();
            }
        });
    }


    public void init() {

        try {
            Image sendImage = new Image(new FileInputStream("image/GPB.png"));
            Image receiveImage = new Image(new FileInputStream("image/PLN.png"));
            send_image.setImage(sendImage);
            receive_image.setImage(receiveImage);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        initEditTextFields(send_text, receive_text, true);
        initEditTextFields(receive_text, send_text, false);

        presenter.initRate();
    }
}

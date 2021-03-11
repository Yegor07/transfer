
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.MainViewControllerImplementation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main extends Application {
    private Stage stage;
    private Parent view;
    private MainViewControllerImplementation viewMainViewControllerImplementation;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        initMVC();
        initStage();
        viewMainViewControllerImplementation.init();
    }

    private void initMVC() throws IOException {
        this.view = initView("sample.fxml");
    }

    private Parent initView(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
        Parent view = loader.load();
        viewMainViewControllerImplementation = loader.getController();
        return view;
    }

    private void initStage() throws FileNotFoundException {
        stage.setTitle("Transfer");
        stage.getIcons().add(new Image(new FileInputStream("image/icon.png")));
        stage.setScene(new Scene(view, 330, 230));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

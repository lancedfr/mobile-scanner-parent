package za.ac.cput.mobile.scanner.admin.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenuController.class);

    public void startAddProduct() throws Exception {
        start(new Stage());
    }

    public void start(Stage stage) throws Exception {
        LOGGER.info("Starting add product");

        String fxmlFile = "/fxml/addproduct.fxml";
        LOGGER.debug("Loading FXML for add product from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));

        Scene scene = new Scene(rootNode, 400, 200);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }
}

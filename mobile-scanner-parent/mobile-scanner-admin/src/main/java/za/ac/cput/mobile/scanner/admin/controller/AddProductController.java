package za.ac.cput.mobile.scanner.admin.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import za.ac.cput.mobile.scanner.admin.config.SpringFacade;
import za.ac.cput.mobile.scanner.repository.model.Product;

public class AddProductController {

    @FXML
    private TextField productBarcodeField;
    @FXML
    private TextField productNameField;

    public void addProduct() {
        Product product = new Product();
        product.setBarcode(productBarcodeField.getText());
        product.setName(productNameField.getText());
        SpringFacade.getProductDao().addProduct(product);
    }

}

package za.ac.cput.mobile.scanner.admin.dao;

import org.junit.Before;
import org.junit.Test;
import za.ac.cput.mobile.scanner.repository.dao.ProductDao;
import za.ac.cput.mobile.scanner.repository.model.Product;

public class RestProductDaoIT {
    private ProductDao productDao;

    @Before
    public void setUp() throws Exception {
        productDao = new RestProductDao();
    }

    @Test
    public void testGetProducts() throws Exception {
        productDao.getProducts();
    }

    @Test
    public void testAddProducts() throws Exception {
        Product product = new Product();
        product.setBarcode("77777777777");
        productDao.addProduct(product);
    }

    @Test
    public void testGetProduct() throws Exception {
        Product product = productDao.getProduct(1);
        product.getBarcode();
    }

    @Test
    public void testGetProductByBarcode() throws Exception {
        Product product = productDao.getProductByBarcode("999999999");
        product.getBarcode();
    }

    @Test
    public void testDeleteProduct() throws Exception {
        productDao.deleteProduct(1);
    }
}

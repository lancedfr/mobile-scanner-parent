package za.ac.cput.mobile.scanner.admin.dao;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.ac.cput.mobile.scanner.admin.config.AdminConfig;
import za.ac.cput.mobile.scanner.admin.exception.RestException;
import za.ac.cput.mobile.scanner.repository.dao.ProductDao;
import za.ac.cput.mobile.scanner.repository.model.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RestProductDao implements ProductDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDao.class);

    private AdminConfig adminConfig;
    private HttpClient client;

    @Override
    public void addProduct(Product product) {
        LOGGER.debug("Adding product");
        ObjectMapper mapper = new ObjectMapper();
        HttpPost httpPost = new HttpPost(adminConfig.addProductUrl());
        httpPost.setHeader("Content-Type", "application/json");
        try {
            String productJson = mapper.writeValueAsString(product);
            httpPost.setEntity(new StringEntity(productJson));
            HttpResponse response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RestException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
            }
        } catch (IOException e) {
            throw new RestException(e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        addProduct(product);
    }

    @Override
    public Product getProduct(Integer id) {
        LOGGER.debug("Get product");
        HttpGet httpGet = new HttpGet(adminConfig.getProductUrl(id));
        try {
            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RestException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
            }
            InputStream content = response.getEntity().getContent();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, Product.class);
        } catch (IOException e) {
            throw new RestException(e);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        LOGGER.debug("Deleting product");
        HttpDelete httpPost = new HttpDelete(adminConfig.deleteProductUrl(id));
        httpPost.setHeader("Content-Type", "application/json");
        try {
            HttpResponse response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RestException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
            }
        } catch (IOException e) {
            throw new RestException(e);
        }
    }

    @Override
    public List<Product> getProducts() {
        LOGGER.debug("Get products");
        HttpGet httpGet = new HttpGet(adminConfig.getProductsUrl());
        try {
            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RestException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
            }
            InputStream content = response.getEntity().getContent();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, Product.class));
        } catch (IOException e) {
            throw new RestException(e);
        }
    }

    @Override
    public Product getProductByBarcode(String barcode) {
        LOGGER.debug("Get product by barcode");
        HttpGet httpGet = new HttpGet(adminConfig.getProductByBarcodeUrl(barcode));
        try {
            HttpResponse response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RestException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
            }
            InputStream content = response.getEntity().getContent();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content, Product.class);
        } catch (IOException e) {
            throw new RestException(e);
        }
    }

    public void setAdminConfig(AdminConfig adminConfig) {
        this.adminConfig = adminConfig;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

}

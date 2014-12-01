package za.ac.cput.mobile.scanner.admin.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:mobile-scanner-admin.properties", "file:src/test/resources/mobile-scanner-admin-test.properties"})
public interface AdminConfig extends Config {

    @Key("serverProtocol")
    @DefaultValue("http")
    String serverProtocol();

    @Key("serverAddress")
    @DefaultValue("41.185.27.216")
    String serverAddress();

    @Key("serverPort")
    @DefaultValue("80")
    String serverPort();

    @Key("serverContext")
    @DefaultValue("mobile-scanner-server")
    String serverContext();


    @Key("addProductUrl")
    @DefaultValue("${serverProtocol}://${serverAddress}:${serverPort}/${serverContext}/rest/product")
    String addProductUrl();

    @Key("getProductUrl")
    @DefaultValue("${serverProtocol}://${serverAddress}:${serverPort}/${serverContext}/rest/product/%d")
    String getProductUrl(Integer id);

    @Key("deleteProductUrl")
    @DefaultValue("${serverProtocol}://${serverAddress}:${serverPort}/${serverContext}/rest/product/%d")
    String deleteProductUrl(Integer id);

    @Key("getProductsUrl")
    @DefaultValue("${serverProtocol}://${serverAddress}:${serverPort}/${serverContext}/rest/products")
    String getProductsUrl();

    @Key("getProductByBarcodeUrl")
    @DefaultValue("${serverProtocol}://${serverAddress}:${serverPort}/${serverContext}/rest/product?barcode=%s")
    String getProductByBarcodeUrl(String barcode);

}

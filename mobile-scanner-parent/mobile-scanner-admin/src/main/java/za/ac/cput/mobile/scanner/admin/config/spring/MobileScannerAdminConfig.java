package za.ac.cput.mobile.scanner.admin.config.spring;

import org.aeonbits.owner.ConfigFactory;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.ac.cput.mobile.scanner.admin.config.AdminConfig;
import za.ac.cput.mobile.scanner.admin.dao.RestProductDao;
import za.ac.cput.mobile.scanner.repository.dao.ProductDao;

@Configuration
public class MobileScannerAdminConfig extends AnnotationConfigApplicationContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileScannerAdminConfig.class);

//    @Bean
//    public AdminConfig adminConfig() {
//        LOGGER.info("Creating AdminConfig bean");
//        return ConfigFactory.create(AdminConfig.class);
//    }

//    @Bean
//    public HttpClient client() {
//        LOGGER.info("Creating HttpClient bean");
//        return HttpClients.createDefault();
//    }

    @Bean
    public ProductDao productDao() {
        LOGGER.info("Creating ProductDao bean");
        RestProductDao restProductDao = new RestProductDao();
        restProductDao.setAdminConfig(ConfigFactory.create(AdminConfig.class));
        restProductDao.setClient(HttpClients.createDefault());
        return restProductDao;
    }
}


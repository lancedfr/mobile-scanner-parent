package za.ac.cput.mobile.scanner.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import za.ac.cput.mobile.scanner.admin.config.spring.MobileScannerAdminConfig;
import za.ac.cput.mobile.scanner.repository.dao.ProductDao;

public class SpringFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringFacade.class);

    private static ApplicationContext applicationContext;

    private static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            LOGGER.debug("Creating spring context");
            applicationContext = new AnnotationConfigApplicationContext(MobileScannerAdminConfig.class);
        }
        return applicationContext;
    }

    public static ProductDao getProductDao() {
        return getApplicationContext().getBean(ProductDao.class);
    }
}

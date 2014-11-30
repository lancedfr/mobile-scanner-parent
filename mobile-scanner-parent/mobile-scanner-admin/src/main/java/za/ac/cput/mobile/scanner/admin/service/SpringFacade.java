package za.ac.cput.mobile.scanner.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import za.ac.cput.mobile.scanner.service.product.ProductService;

public class SpringFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringFacade.class);
    private static AbstractXmlApplicationContext applicationContext;

    public static void startSpring() {
        LOGGER.debug("Starting spring services");
        applicationContext = new ClassPathXmlApplicationContext("root-context-test.xml");
    }

    public static AbstractXmlApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            LOGGER.error("applicationContext not configured, consider calling SpringFacade#startSpring() in application initiation");
        }
        return applicationContext;
    }

    public static ProductService getProductService() {
        return getApplicationContext().getBean(ProductService.class);
    }
}

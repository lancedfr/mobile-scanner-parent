package za.ac.cput.mobile.scanner.admin.config;

import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminConfigTest.class);

    @Test
    public void testAdminConfig() throws Exception {
        AdminConfig cfg = ConfigFactory.create(AdminConfig.class);
        String s = cfg.getProductUrl(1111);
        LOGGER.debug(cfg.toString());
        Assert.assertNotNull(s);
    }
}

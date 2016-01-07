package info.smartkit.verbose_dollop;

import info.smartkit.verbose_dollop.service.CustomerService;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = VerboseDollopApplication.class)
public class VerboseDollopApplicationTests {

    @Autowired
    private CustomerService customerService;

    @Test
    @Ignore
    public void contextLoads() {
    }

    @Test
    public void customerServicePopulateOK() throws IOException {
        Assert.assertNotNull(customerService.populate());
    }
}

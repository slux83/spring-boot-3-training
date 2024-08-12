package fr.slux.training.springboot.aop;

import fr.slux.training.springboot.aop.business.BusinessService1;
import fr.slux.training.springboot.aop.business.BusinessService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AspectOrientedProgrammingApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(AspectOrientedProgrammingApplication.class);
    private BusinessService1 businessService1;
    private BusinessService2 businessService2;

    public AspectOrientedProgrammingApplication(BusinessService1 businessService1, BusinessService2 businessService2) {
        this.businessService1 = businessService1;
        this.businessService2 = businessService2;
    }

    public static void main(String[] args) {
        SpringApplication.run(AspectOrientedProgrammingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int max = this.businessService1.calculateMax();
        LOG.info("The max is {}", max);

        int min = this.businessService2.calculateMin();
        LOG.info("The min is {}", min);
    }
}

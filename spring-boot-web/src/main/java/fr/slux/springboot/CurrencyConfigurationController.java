package fr.slux.springboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CurrencyConfigurationController {
    private CurrencyServiceConfiguration currencyServiceConfiguration;

    public CurrencyConfigurationController(CurrencyServiceConfiguration currencyServiceConfiguration) {
        this.currencyServiceConfiguration = currencyServiceConfiguration;
    }

    @RequestMapping("/currency-configuration")
    public CurrencyServiceConfiguration retrieveAllCourses() {
        return currencyServiceConfiguration;
    }

}

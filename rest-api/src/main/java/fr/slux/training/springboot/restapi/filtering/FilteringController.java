package fr.slux.training.springboot.restapi.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBeanStaticFiltering filteringStatic() {
        return new SomeBeanStaticFiltering("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBeanStaticFiltering> filteringStaticList() {
        return Arrays.asList(
                new SomeBeanStaticFiltering("value1", "value2", "value3"),
                new SomeBeanStaticFiltering("value4", "value5", "value6"));
    }

    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue filteringDynamic() {
        SomeBeanDynamicFiltering bean = new SomeBeanDynamicFiltering("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
        // Only field1 will be filtered out
        PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanDynamicFilteringName", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

        @GetMapping("/filtering-list-dynamic")
    public MappingJacksonValue filteringDynamicList() {
        List<SomeBeanDynamicFiltering> list = Arrays.asList(
                new SomeBeanDynamicFiltering("value1", "value2", "value3"),
                new SomeBeanDynamicFiltering("value4", "value5", "value6"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanDynamicFilteringName", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}

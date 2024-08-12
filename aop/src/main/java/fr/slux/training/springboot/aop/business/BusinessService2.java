package fr.slux.training.springboot.aop.business;

import fr.slux.training.springboot.aop.annotations.TrackTime;
import fr.slux.training.springboot.aop.data.DataService1;
import fr.slux.training.springboot.aop.data.DataService2;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BusinessService2 {

    private DataService2 dataService2;

    public BusinessService2(DataService2 dataService2) {

        this.dataService2 = dataService2;
    }

    @TrackTime
    public int calculateMin() {
        int[] data = this.dataService2.retrieveData(333);
        //throw new RuntimeException("Something went wrong");
        return Arrays.stream(data).min().orElse(0);
    }

}

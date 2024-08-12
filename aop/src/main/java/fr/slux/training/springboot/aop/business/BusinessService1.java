package fr.slux.training.springboot.aop.business;

import fr.slux.training.springboot.aop.annotations.TrackTime;
import fr.slux.training.springboot.aop.data.DataService1;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BusinessService1 {

    private DataService1 dataService1;

    public BusinessService1(DataService1 dataService1) {
        this.dataService1 = dataService1;
    }

    @TrackTime
    public int calculateMax() {
        int[] data = this.dataService1.retrieveData(666);
        //throw new RuntimeException("Something went wrong");
        return Arrays.stream(data).max().orElse(0);
    }

}

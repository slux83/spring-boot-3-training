package fr.slux.training.springboot.aop.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataService1 {

    public int[] retrieveData(int someArgument) {
        return new int[]{
                42, 33, 98, 6, 9, 0, 6, 5
        };
    }
}

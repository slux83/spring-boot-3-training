package fr.slux.training.springboot.aop.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataService2 {

    public int[] retrieveData(int someArgument) {
        return new int[]{
                99, 88, 77, 66, 55, 44, 33, 22, 11
        };
    }
}

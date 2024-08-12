package fr.slux.training.udemy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class ClassA {

}

@Component
@Lazy // not recommended and not usually used!!! Config errors not discovered at startup
class ClassB {
    private ClassA classA;

    public ClassB(ClassA classA) {
        this.classA = classA;
        System.out.println("Some initialization logic");
    }

    public void doSomething() {
        System.out.println("Do something");
    }

}

@Configuration
@ComponentScan
public class LazyInitializationApp {

	public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(
                LazyInitializationApp.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println("Initialization completed");
            context.getBean(ClassB.class).doSomething();
        }
	}

}

package fr.slux.training.udemy;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

// Default scope SINGLETON
@Component
class SingletonClass {
    @PreDestroy
    void preDestroy() {
        System.out.println("pre-destroy");
    }

    @PostConstruct
    void post() {
        System.out.println("post-construct");
    }
}

// Scope prototype means every time you request a bean, a new instance is created.
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class PrototypeClass {
    @PreDestroy
    void preDestroy() {
        System.out.println("pre-destroy");
        // WILL NOT BE CALLED because it's a scope prototype
    }

    @PostConstruct
    void post() {
        System.out.println("post-construct");
    }
}

@Configuration
@ComponentScan
public class BeanScopeApp {

	public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(
                LazyInitializationApp.class)) {
            //Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            //System.out.println("Initialization completed");

            System.out.println(context.getBean(SingletonClass.class));
            System.out.println(context.getBean(SingletonClass.class));

            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
        }
	}

}

package fr.slux.training.springboot.restapi.helloworld;


import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // better than @RequestMapping (to have always GET method)
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(
            @PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-i18n")
    public String helloWorldI18n() {
        // will get the locale of the request or the default from the OS
        Locale locale = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage("good.morning.message", null, "default message", locale);
    }

    @GetMapping(path = "/hello-world/path-variable-i18n/{name}")
    public HelloWorldBean helloWorldBeanPathVariableI18n(
            @PathVariable String name) {
        Locale locale = LocaleContextHolder.getLocale();
        return new HelloWorldBean(this.messageSource.getMessage("hello.message", new Object[] {name}, locale));
    }

}

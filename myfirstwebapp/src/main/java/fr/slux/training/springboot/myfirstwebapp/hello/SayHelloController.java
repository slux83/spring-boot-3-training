package fr.slux.training.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody   // without this you will have an issue because there's no view associated to this URL
    public String sayHello() {
        return "Hello from spring boot!";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody   // without this you will have an issue because there's no view associated to this URL
    public String sayHelloHtml() {
        return """
                <html>
                    <head>
                        <title>Hello page</title>
                    </head>
                    <body>
                        <h1>Hello from spring boot!</h1>
                     </body>
                </html>""";
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHelloView"; // the name of the JSP file to view data
    }
}

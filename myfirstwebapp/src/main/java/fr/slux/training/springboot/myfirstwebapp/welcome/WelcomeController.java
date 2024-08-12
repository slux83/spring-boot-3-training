package fr.slux.training.springboot.myfirstwebapp.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
    private static final Logger LOG = LoggerFactory.getLogger(WelcomeController.class);

    // Not used, just a test
    @RequestMapping("login-with-param")
    public String loginTestWithParam(@RequestParam String name,
                                     ModelMap model) {
        // ModelMap is needed to pass attributes to the JSP
        model.put("name", name);
        LOG.debug("parameter name = {}", name);
        return "loginWithParamView";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        model.put("name", getLoggerUsername());
        return "welcomeView";
    }

    private String getLoggerUsername() {
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        return user.getName();
    }

}

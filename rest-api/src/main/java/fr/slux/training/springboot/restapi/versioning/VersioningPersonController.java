package fr.slux.training.springboot.restapi.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * - URI based versioning [Twitter]
 * - Request Parameter based versioning [Amazon]
 * - Custom HTTP header versioning [Microsoft]
 * - Media type versioning (content negotiation or accept header) [GitHub]
 *
 * URI pollution with the first two.
 * Headers are not supposed to be used for these things
 * URI based and parameters is easy to use from a browser without tools to adjust headers
 *
 */
@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Elon Musk");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Elon", "Musk"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonReqParameter() {
        return new PersonV1("Elon Musk");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonReqParam() {
        return new PersonV2(new Name("Elon", "Musk"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonReqHeader() {
        return new PersonV1("Elon Musk");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonReqHeader() {
        return new PersonV2(new Name("Elon", "Musk"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader() {
        return new PersonV1("Elon Musk");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Elon", "Musk"));
    }
}

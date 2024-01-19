package project.rabbitmq.producer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.rabbitmq.producer.services.StringService;

@RestController
@RequestMapping("/produces")
public class StringController {

    @Autowired
    private StringService stringService;

    @GetMapping
    public ResponseEntity<String> producesMessage(@RequestParam("message") String message){
        stringService.produce(message);

        return ResponseEntity.ok().body("Sending message");
    }
}

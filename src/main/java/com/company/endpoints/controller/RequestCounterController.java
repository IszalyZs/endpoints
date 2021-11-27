package com.company.endpoints.controller;

import com.company.endpoints.model.Statistics;
import com.company.endpoints.service.RequestCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestCounterController {
    private RequestCounterService requestCounterService;

    @Autowired
    public RequestCounterController(RequestCounterService requestCounterService) {
        this.requestCounterService = requestCounterService;
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy");
    }

    @GetMapping("/requests")
    public ResponseEntity<String> getRequests() {
        System.out.println("Get requests");
        requestCounterService.increase(RequestCounterService.GET);
        return ResponseEntity.ok().body("It was success");
    }

    @GetMapping("/stats")
    public ResponseEntity<List<Statistics>> getStats() {
        System.out.println("Get stats");
        return ResponseEntity.ok(requestCounterService.getStat());
    }

    @DeleteMapping("/requests")
    public ResponseEntity<Void> delete() {
        System.out.println("delete");
        requestCounterService.increase(RequestCounterService.DELETE);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/requests")
    public ResponseEntity<Void> patch() {
        System.out.println("patch");
        requestCounterService.increase(RequestCounterService.PATCH);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/requests")
    public ResponseEntity<Void> put() {
        System.out.println("put");
        requestCounterService.increase(RequestCounterService.PUT);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/requests")
    public ResponseEntity<Void> post() {
        System.out.println("post");
        requestCounterService.increase(RequestCounterService.POST);
        return ResponseEntity.ok().build();
    }


}

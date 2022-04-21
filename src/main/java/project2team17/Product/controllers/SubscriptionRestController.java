package project2team17.Product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project2team17.Product.model.Subscription;
import project2team17.Product.repositories.SubscriptionRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubscriptionRestController {
    SubscriptionRepository repository;

    @Autowired
    public void setProductRepository(SubscriptionRepository repository){
        this.repository = repository;

        Subscription sub1 = new Subscription();
        sub1.setName("Premium");
        sub1.setAdvantages("The premium advantages");
        sub1.setPrice(60.0);

        Subscription sub2 = new Subscription();
        sub2.setName("Normal");
        sub2.setAdvantages("The normal advantages");
        sub2.setPrice(60.0);
        this.repository.save(sub1);
        this.repository.save(sub2);
    }

    @GetMapping("/subscriptions")
    public List<Subscription> getAllSubscription(){
        return repository.findAll();
    }

    @PostMapping("/subscriptions")
    public Subscription createSubscription(@RequestBody Subscription subscription){
        return repository.save(subscription);
    }

    @GetMapping("/subscriptions/{id}")
    public Subscription getSubscription(@PathVariable("id") Long id){
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/subscriptions/{id}")
    public void deleteTutorial(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}

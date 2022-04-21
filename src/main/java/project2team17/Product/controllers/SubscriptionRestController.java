package project2team17.Product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project2team17.Product.model.BankAccount;
import project2team17.Product.model.Subscription;
import project2team17.Product.repositories.BankAccountsRepository;
import project2team17.Product.repositories.SubscriptionRepository;

@RestController
@RequestMapping("/api/subscriptions")
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

    @GetMapping("/overview")
    public Iterable<Subscription> getAll(){
        return repository.findAll();
    }
}

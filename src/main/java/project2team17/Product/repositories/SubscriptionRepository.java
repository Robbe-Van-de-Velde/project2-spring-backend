package project2team17.Product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project2team17.Product.model.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}

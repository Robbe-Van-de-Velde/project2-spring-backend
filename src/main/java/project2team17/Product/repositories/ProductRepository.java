package project2team17.Product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project2team17.Product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

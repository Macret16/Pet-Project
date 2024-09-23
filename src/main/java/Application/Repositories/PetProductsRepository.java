package Application.Repositories;

import Application.Models.PetProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetProductsRepository extends JpaRepository<PetProducts, Long> {
    // You can add custom query methods if needed
}
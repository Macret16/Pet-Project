package Application.Repositories;

import java.util.List;

import Application.Models.Adoptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionsRepository extends JpaRepository<Adoptions, Long> {
	 List<Adoptions> findByStatus(Boolean status);
    // You can add custom query methods if needed
}

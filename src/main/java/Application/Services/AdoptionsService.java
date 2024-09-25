package Application.Services;

import Application.Models.Adoptions;
import Application.Models.PetCategory;
import Application.Models.PetProducts;
import Application.Repositories.AdoptionsRepository;
import Application.Repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdoptionsService {

    @Autowired private AdoptionsRepository adoptionsRepository;
    @Autowired private PetRepository petRepository;

    public List<Adoptions> getAvailableAdoptions() {
        return adoptionsRepository.findByStatus(true); // true indicates available
    }
}

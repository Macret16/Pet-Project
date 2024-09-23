package Application.Services;

import Application.Models.PetProducts;
import Application.Repositories.PetProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PetProductsService {

    @Autowired
    private PetProductsRepository petProductsRepository;

    public PetProducts saveProduct(PetProducts product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setPhoto(imageFile.getBytes());
        }
        return petProductsRepository.save(product);
    }

    public List<PetProducts> findAll() {
        return petProductsRepository.findAll();
    }

    public PetProducts findById(Long id) {
        return petProductsRepository.findById(id).orElse(null);
    }
}

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

    public void saveProduct(PetProducts product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setPhoto(imageFile.getBytes());
        }
        petProductsRepository.save(product);
    }

    public PetProducts findById(Long id) {
        return petProductsRepository.findById(id).orElse(null);
    }

    public List<PetProducts> getAllProducts() {
        return petProductsRepository.findAll();
    }

    public void addProduct(PetProducts productDto) {
        PetProducts product = new PetProducts();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAvailableStock(productDto.getAvailableStock());
        product.setCategory(productDto.getCategory());
        product.setVendorName(productDto.getVendorName());
        product.setVendorPhone(productDto.getVendorPhone());
        // Handle saving the image (not shown for brevity)
        petProductsRepository.save(product);
    }

    public void deleteProduct(Long id) {
        petProductsRepository.deleteById(id);
    }
}

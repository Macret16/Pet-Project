package Application.Controllers;

import Application.Models.PetProducts;
import Application.Services.PetProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class PetProductsController {

    @Autowired
    private PetProductsService petProductsService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", petProductsService.getAllProducts());
        return "shopping";
    }

    @GetMapping("/all")
    public ResponseEntity<List<PetProducts>> getAllProducts() {
        return ResponseEntity.ok(petProductsService.getAllProducts());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        petProductsService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new PetProducts());
        return "admin-panel";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute PetProducts product,
                             @RequestParam("image") MultipartFile imageFile) throws IOException {
        petProductsService.saveProduct(product, imageFile);
        return "redirect:/admin";
    }

    @GetMapping("/images/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        PetProducts product = petProductsService.findById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust as needed
                .body(product.getPhoto());
    }
}
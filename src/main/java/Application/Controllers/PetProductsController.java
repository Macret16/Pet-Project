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

@Controller
@RequestMapping("/products")
public class PetProductsController {

    @Autowired
    private PetProductsService petProductsService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", petProductsService.findAll());
        return "shopping";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new PetProducts());
        return "addProducts";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute PetProducts product, @RequestParam("image") MultipartFile imageFile) {
        try {
            petProductsService.saveProduct(product, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error (e.g., redirect to an error page)
        }
        return "redirect:/shopping";
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
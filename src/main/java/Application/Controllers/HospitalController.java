package Application.Controllers;

import Application.Models.Hospital;
import Application.Models.PetProducts;
import Application.Services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    // Add a new hospital with image upload
    @PostMapping("/add")
    public String addHospital(@ModelAttribute Hospital hospital,
                              @RequestParam("hospital_image") MultipartFile imageFile) throws IOException {
        hospitalService.addHospital(hospital, imageFile);
        return "redirect:/admin";
    }

    // Update an existing hospital with an image upload
    @PostMapping("/update")
    public String updateHospital(@ModelAttribute Hospital hospital,
                                 @RequestParam("hospital_image") MultipartFile imageFile) throws IOException {
        hospitalService.updateHospital(hospital, imageFile);
        return "redirect:/admin";
    }

    // Delete a hospital
    @PostMapping("/delete/{id}")
    public String deleteHospital(@PathVariable Long id) {
        hospitalService.deleteHospital(id);
        return "redirect:/admin";
    }

    @GetMapping("/images/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getHospitalImage(@PathVariable Long id) {
        Hospital product = hospitalService.findById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust as needed
                .body(product.getImage());
    }
}

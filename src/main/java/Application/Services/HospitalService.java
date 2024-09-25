package Application.Services;

import Application.Models.Hospital;
import Application.Models.PetProducts;
import Application.Repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class HospitalService{

    @Autowired
    private HospitalRepository hospitalRepository;

    // Retrieve all hospitals
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // Add a new hospital
    public void addHospital(Hospital hospital, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            hospital.setImage(imageFile.getBytes());
        }
        hospitalRepository.save(hospital);
    }

    // Update an existing hospital
    public void updateHospital(Hospital hospital, MultipartFile imageFile) throws IOException {
        if (hospitalRepository.existsById(hospital.getId())) {
            if (imageFile != null && !imageFile.isEmpty()) {
                hospital.setImage(imageFile.getBytes());
            }
            hospitalRepository.save(hospital);
        }
    }

    // Delete a hospital
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

    // Get a hospital by ID
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id).orElse(null);
    }

    public Hospital findById(Long id) {
        return hospitalRepository.findById(id).orElse(null);
    }
}

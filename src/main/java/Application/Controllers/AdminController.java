package Application.Controllers;

import Application.Models.Hospital;
import Application.Models.PetCategory;
import Application.Models.Role;
import Application.Models.User;
import Application.Repositories.HospitalRepository;
import Application.Repositories.PetRepository;
import Application.Repositories.RoleRepository;
import Application.Repositories.UserRepository;
import Application.Services.AdoptionsService;
import Application.Services.HospitalService;
import Application.Services.RoleService;
import Application.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private AdoptionsService adoptionsService;

    @Autowired private PetRepository petRepository;

    @Autowired private HospitalService hospitalService;

    // Default admin panel view
    @GetMapping
    public String admin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("categories", petRepository.findAll()); // Add categories
        System.out.println("Categories: " + petRepository.findAll()); // Debugging output
        List<Hospital> hospitals = hospitalService.getAllHospitals();
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("hospital", new Hospital()); // Form for adding/updating
        return "admin-panel"; // Ensure this view is correctly set up in your templates
    }

    // Add a new role
    @PostMapping("/roles")
    public String addRole(@RequestParam String roleName) {
        Role role = new Role();
        role.setName(roleName.toUpperCase());
        roleRepository.save(role);
        return "redirect:/admin"; // Redirect to the admin panel
    }

    // Assign roles to a user
    @PostMapping("/users/{userId}/roles")
    public String assignRole(@PathVariable Long userId, @RequestParam Set<String> roleName) {
        // Fetch the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + userId));

        // Clear existing roles if you want to replace them or comment this line to keep existing roles
        // user.getRoles().clear();

        // Iterate through the role names to find their IDs and assign roles
        for (String roles : roleName) {
            Long roleId = roleService.findIdByName(roles); // Get the role ID by name
            if (roleId != null) { // Check if the role ID is valid
                Role role = roleService.getRoleById(roleId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid role Id: " + roleId));
                user.getRoles().add(role); // Add the role to the user
            } else {
                throw new IllegalArgumentException("Role name does not exist: " + roles);
            }
        }
        userService.updateUserRoles(userId, roleName);
        userRepository.save(user); // Save the user with the updated roles
        return "redirect:/admin"; // Redirect to the admin panel
    }

    // Remove a role from a user
    @PostMapping("/users/{userId}/roles/remove")
    public String removeRole(@PathVariable Long userId, @RequestParam Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + userId));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id: " + roleId));

        user.getRoles().remove(role); // Remove the specified role from the user
        userRepository.save(user); // Save user with the updated roles
        return "redirect:/admin"; // Redirect to the admin panel
    }

    // Remove a role by ID
    @PostMapping("/roles/delete")
    public String deleteRole(@RequestParam Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id: " + roleId));

        // Remove the role from all users who have this role
        userRepository.findAll().forEach(user -> user.getRoles().remove(role));

        roleRepository.delete(role); // Delete the role
        return "redirect:/admin"; // Redirect to the admin panel
    }

    // Add a new pet category
    @PostMapping("/categories/add")
    public String addPetCategory(@RequestParam String categoryName, PetCategory petCategory) {
        try {
            petCategory.setName(categoryName);
            petRepository.save(petCategory);
            return "redirect:/admin"; // Redirect to the admin panel
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "redirect:/admin";
        }
    }

    // Delete a pet category
    @PostMapping("/categories/delete")
    public String deletePetCategory(@RequestParam Long categoryId) {
        PetCategory category = petRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id: " + categoryId));

        // Optionally, remove category from pets or other related entities before deletion
        petRepository.delete(category); // Delete the category
        return "redirect:/admin"; // Redirect to the admin panel
    }
}
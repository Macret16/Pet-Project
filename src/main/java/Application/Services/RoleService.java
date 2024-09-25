package Application.Services;

import Application.Models.Role;
import Application.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Method to get a role by ID
    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    // Method to get a role by name
    public Long findIdByName(String roleName) {
        Role role = roleRepository.findRoleByName(roleName);
        return role != null ? role.getId() : null; // Return ID if found, else null
    }

    // Method to create a new role
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // Method to get all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Method to delete a role by ID
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    // Other methods for managing roles can be added here as needed
}
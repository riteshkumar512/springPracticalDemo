package in.kumar.springPracticalDemo.service;

import in.kumar.springPracticalDemo.entity.Role;
import in.kumar.springPracticalDemo.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role){
        roleRepository.save(role);
    }
}

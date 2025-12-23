package org.example.demo.run_method;


import lombok.RequiredArgsConstructor;
import org.example.demo.entity.Role;
import org.example.demo.entity.enums.RoleName;
import org.example.demo.repo.RoleRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RunnableConfig implements Runnable{


  private final RoleRepository  roleRepository;

  @Override
  public void run() {
    roleRepository.deleteAll();
    roleRepository.save(Role.builder()
        .name(RoleName.USER)
        .build());
    roleRepository.save(Role.builder()
        .name(RoleName.SUPER_ADMIN)
        .build());
    roleRepository.save(Role.builder()
        .name(RoleName.ADMIN)
        .build());
    roleRepository.save(Role.builder()
        .name(RoleName.DELIVER)
        .build());



  }
}

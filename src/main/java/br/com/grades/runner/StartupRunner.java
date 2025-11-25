package br.com.grades.runner;

import br.com.grades.entity.CursoEntity;
import br.com.grades.entity.RoleEntity;
import br.com.grades.entity.UserEntity;
import br.com.grades.repository.CursoRepository;
import br.com.grades.repository.RoleRepository;
import br.com.grades.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StartupRunner implements CommandLineRunner {

    private final CursoRepository cursoRepository;

    public StartupRunner(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public void run(String... args) {
        if (cursoRepository.count() == 0) {

            CursoEntity c1 = new CursoEntity();
            c1.setNome("Java Web");
            c1.setDescricao("Curso completo de Spring Boot");
            c1.setDuracao("40h");
            c1.setProfessor("João Silva");
            c1.setCategoria("Programação");
            c1.setPreco(399.90);

            CursoEntity c2 = new CursoEntity();
            c2.setNome("Design UX");
            c2.setDescricao("Curso de UI/UX moderno");
            c2.setDuracao("30h");
            c2.setProfessor("Ana Souza");
            c2.setCategoria("Design");
            c2.setPreco(299.90);

            cursoRepository.save(c1);
            cursoRepository.save(c2);

            System.out.println("Cursos iniciais cadastrados!");
        }
    }

    @Bean
    public CommandLineRunner init(RoleRepository roleRepo,
                                  UserRepository userRepo,
                                  PasswordEncoder encoder) {
        return args -> {

            // Criar roles se não existirem
            if (roleRepo.findByName("ROLE_ADMIN").isEmpty()) {
                roleRepo.save(new RoleEntity(null, "ROLE_ADMIN"));
            }
            if (roleRepo.findByName("ROLE_USER").isEmpty()) {
                roleRepo.save(new RoleEntity(null, "ROLE_USER"));
            }

            // Admin
            if (userRepo.findByUsername("admin").isEmpty()) {
                RoleEntity adminRole = roleRepo.findByName("ROLE_ADMIN").orElseThrow();
                RoleEntity userRole = roleRepo.findByName("ROLE_USER").orElseThrow();

                UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin"));
                admin.setRoles(Set.of(adminRole, userRole));

                userRepo.save(admin);
            }

            // Usuário comum
            if (userRepo.findByUsername("user").isEmpty()) {
                RoleEntity userRole = roleRepo.findByName("ROLE_USER").orElseThrow();

                UserEntity user = new UserEntity();
                user.setUsername("user");
                user.setPassword(encoder.encode("user"));
                user.setRoles(Set.of(userRole));

                userRepo.save(user);
            }
        };
    }
}
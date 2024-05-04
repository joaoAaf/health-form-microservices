package estudo.serviceusers.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import estudo.serviceusers.dto.UserSave;
import estudo.serviceusers.repository.UserRepository;
import estudo.serviceusers.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class Instantiation implements CommandLineRunner {

    private final UserRepository userRepo;
    private final UserService userServ;
    
    @Override
    public void run(String... args) throws Exception {
        if(userRepo.count() == 0) {
			var user1 = new UserSave("Fulano de Tal", "fulano@gmail.com","123456");
			userServ.saveUser(user1);
            var user2 = new UserSave("Sicrano da Silva", "sicrano@gmail.com","123456");
			userServ.saveUser(user2);
			var user3 = new UserSave("Beltrano Costa", "beltrano@gmail.com","123456");
			userServ.saveUser(user3);
		}
    }

}

package estudo.serviceusers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import estudo.serviceusers.dto.UserMod;
import estudo.serviceusers.dto.UserSave;
import estudo.serviceusers.dto.UserView;
import estudo.serviceusers.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService extends BaseService {

    private final UserRepository userRepo;

    public List<UserView> findUsers() {
        var users = userRepo.findAll();
        return users.stream().map(this::toDto).toList();
    }

    public Optional<Object> findUserId(String id) {
        var user = userRepo.findById(id);
        if (user.isPresent()) {
            return Optional.of(toDto(user.get()));
        }
        return Optional.empty();
    }

    public Optional<Object> findUserEmail(String email) {
        var user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            return Optional.of(toDto(user.get()));
        }
        return Optional.empty();
    }

    public UserView saveUser(UserSave userSave) {
        var user = fromDto(userSave);
        user.setPass(passEnc().encode(user.getPass()));
        return toDto(userRepo.save(user));
    }

    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }

    public UserView update(UserMod newUser, String userId) {
		var user = userRepo.findById(userId).get();
		updateData(user, newUser);
		return toDto(userRepo.save(user));
	}

}

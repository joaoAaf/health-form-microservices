package estudo.serviceusers.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import estudo.serviceusers.dto.UserAuth;
import estudo.serviceusers.dto.UserMod;
import estudo.serviceusers.dto.UserSave;
import estudo.serviceusers.dto.UserView;
import estudo.serviceusers.model.User;

public class BaseService {

  protected BCryptPasswordEncoder passEnc() {
    return new BCryptPasswordEncoder();
  }

  protected User fromDto(UserSave userDto) {
    return User.builder()
        .name(userDto.name())
        .email(userDto.email())
        .pass(userDto.pass())
        .build();
  }

  protected User fromDto(UserMod userDto) {
    return User.builder()
        .name(userDto.name())
        .email(userDto.email())
        .pass(userDto.pass())
        .build();
  }

  protected UserView toDto(User user) {
    return UserView.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }

  protected UserAuth toDtoAuth(User user) {
    return new UserAuth(user.getId(), user.getName(), user.getEmail(), user.getPass());
  }

  protected void updateData(User oldUser, UserMod newUser) {
    if (validateUpdate(oldUser.getName(), newUser.name())) {
      oldUser.setName(newUser.name());
    }
    if (validateUpdate(oldUser.getEmail(), newUser.name())) {
      oldUser.setEmail(newUser.email());
    }
    if (validateUpdate(oldUser.getPass(), newUser.pass())) {
      oldUser.setPass(passEnc().encode(newUser.pass()));
    }
  }

  protected boolean validateUpdate(String attOldUser, String attNewUser) {
    return !attNewUser.equals(attOldUser) && !attNewUser.equals(null)
        && !attNewUser.isEmpty() && !attNewUser.isBlank();
  }

}

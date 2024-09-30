package dev.jlkeesh;


public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String go() {
        return "go from service " + userRepository.getUser();
    }

}

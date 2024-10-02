package dev.jlkeesh;


public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        System.out.println("user service constructor "+System.identityHashCode(userRepository));
        this.userRepository = userRepository;
    }

    public String go() {
        return "go from service " + userRepository.getUser();
    }

}

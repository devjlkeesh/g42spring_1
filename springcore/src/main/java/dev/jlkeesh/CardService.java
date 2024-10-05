package dev.jlkeesh;


public class CardService {
    private final UserRepository userRepository;

    public CardService(UserRepository userRepository) {
        System.out.println("card service constructor " + System.identityHashCode(userRepository));
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}

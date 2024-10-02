package dev.jlkeesh;

public class ProductService {
    private final UserRepository userRepository;

    public ProductService(UserRepository userRepository) {
        System.out.println("product service constructor "+System.identityHashCode(userRepository));
        this.userRepository = userRepository;
    }
}

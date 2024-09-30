package dev.jlkeesh;

public class UserRepository {

    public UserRepository(){
        System.out.println("UserRepository created");
    }

    public static UserRepository getInstance(){
        System.out.println("UserRepository getInstance created");
        return new UserRepository();
    }

    public void init() {
        System.out.println("userRepository init");
    }

    public void destroy() {
        System.out.println("userRepository destroy");
    }

    public Object getUser() {
        return "DangDung User";
    }

}

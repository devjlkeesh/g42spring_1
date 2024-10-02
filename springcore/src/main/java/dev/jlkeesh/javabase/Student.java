package dev.jlkeesh.javabase;

public class Student {
    @Override
    public String toString() {
        return "student bean " + System.identityHashCode(this);
    }
}

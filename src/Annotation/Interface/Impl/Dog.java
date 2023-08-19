package Annotation.Interface.Impl;

import Annotation.Interface.Animal;

public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog eat meat......");
    }

    @Override
    public int think() {
        return 1;
    }
}

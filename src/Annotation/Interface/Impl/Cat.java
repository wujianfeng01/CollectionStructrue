package Annotation.Interface.Impl;

import Annotation.Interface.Animal;

public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("cat eat fish.....");
    }

    @Override
    public int think() {
        return 0;
    }
}

package ru.spbu.mas;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello");
        MainController mc = new MainController();
        mc.initAgents();
    }
}
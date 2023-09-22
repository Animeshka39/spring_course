package org.chdtu;

public class Model {
    private Sprinter sprinter;

    public Model() {}

    public Model(Sprinter sprinter) {
        this.sprinter = sprinter;
    }

    public void setSprinter(Sprinter sprinter) {
        this.sprinter = sprinter;
    }

    public void showModelInfo() {
        System.out.println("Model");
        sprinter.showSprinterInfo();
    }
}
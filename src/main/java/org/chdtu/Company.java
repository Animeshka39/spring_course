package org.chdtu;

public class Company {
    private Model model;

    public Company() {}

    public Company(Model model) {
        this.model = model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void showCompanyInfo() {
        System.out.println("Toyota");
        model.showModelInfo();
    }
}

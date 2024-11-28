package com.schoolmanagerserver.pojos;

public class Menu {
    private String menuName;
    private String menuDescription;
    private Long id;

    public Menu(String menuName, String menuDescription, Long id) {
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.id = id;
    }
    public Menu(String menuName, Long id ) {}
}

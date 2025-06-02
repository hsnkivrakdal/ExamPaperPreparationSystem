package com.example.eppms.menucomposite;

import java.util.ArrayList;
import java.util.List;

public class MenuItem implements MenuComponent {
    private final String name;
    private final String url;
    private final String iconClass;

    public MenuItem(String name, String url, String iconClass) {
        this.name = name;
        this.url = url;
        this.iconClass = iconClass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getIconClass() {
        return iconClass;
    }
}


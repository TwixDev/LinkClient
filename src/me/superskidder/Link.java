package me.superskidder;


import me.superskidder.manager.module.ModuleManager;

public class Link {
    public static Link Instance = new Link();
    public final String CLIENT_NAME= "Link";
    public final String CLIENT_VERSION= "1.0";
    public ModuleManager moduleManager;

    public Link(){
        moduleManager = new ModuleManager();
    }

    public void onInitialize(){


    }

    public void onExit(){


    }

}

package com.dynecon.dynecon;

import org.bukkit.plugin.java.JavaPlugin;

public final class DynEcon extends JavaPlugin {

    private static DynEcon instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getLogger().info("==========================================");
        getLogger().info("DynEcon успешно запущен!");
        getLogger().info("==========================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("DynEcon выключен.");
    }

    public static DynEcon getInstance() {
        return instance;
    }
}
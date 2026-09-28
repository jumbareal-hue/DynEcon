package com.dynecon.dynecon;

import com.dynecon.dynecon.commands.ShopCommand;
import com.dynecon.dynecon.economy.MarketManager;
import com.dynecon.dynecon.economy.MarketManager.MarketItem;
import org.bukkit.plugin.java.JavaPlugin;

public final class DynEcon extends JavaPlugin {

    private static DynEcon instance;
    private MarketManager marketManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        marketManager = new MarketManager();
        marketManager.registerItem(new MarketItem("DIAMOND", 100.0, 10.0, 500.0, 100.0));

        if (getCommand("shop") != null) {
            getCommand("shop").setExecutor(new ShopCommand());
        }

        getLogger().info("DynEcon запущен!");
    }

    @Override
    public void onDisable() {
        getLogger().info("DynEcon выключен.");
    }

    public static DynEcon getInstance() {
        return instance;
    }

    public MarketManager getMarketManager() {
        return marketManager;
    }
}
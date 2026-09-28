package com.dynecon.dynecon.commands;

import com.dynecon.dynecon.DynEcon;
import com.dynecon.dynecon.economy.MarketManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Команда только для игроков!");
            return true;
        }

        MarketManager market = DynEcon.getInstance().getMarketManager();

        if (args.length == 0) {
            player.sendMessage("§e=== Рынок DynEcon ===");
            player.sendMessage("§fЦена DIAMOND: §a" + String.format("%.2f", market.getPrice("DIAMOND")) + "$");
            player.sendMessage("§7Купить: §f/shop buy DIAMOND <кол-во>");
            player.sendMessage("§7Продать: §f/shop sell DIAMOND <кол-во>");
            return true;
        }

        if (args.length >= 3) {
            String action = args[0];
            String item = args[1].toUpperCase();
            int amount;

            try {
                amount = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                player.sendMessage("§cУкажите количество числом!");
                return true;
            }

            if (action.equalsIgnoreCase("buy")) {
                double cost = market.buyItem(item, amount);
                if (cost <= 0) {
                    player.sendMessage("§cТовар не найден!");
                    return true;
                }
                player.sendMessage("§aКуплено " + amount + "x " + item + " за §e" + String.format("%.2f", cost) + "$");
                player.sendMessage("§7Новая цена: §a" + String.format("%.2f", market.getPrice(item)) + "$");
                return true;
            }

            if (action.equalsIgnoreCase("sell")) {
                double earnings = market.sellItem(item, amount);
                if (earnings <= 0) {
                    player.sendMessage("§cТовар не найден!");
                    return true;
                }
                player.sendMessage("§aПродано " + amount + "x " + item + " за §e" + String.format("%.2f", earnings) + "$");
                player.sendMessage("§7Новая цена: §a" + String.format("%.2f", market.getPrice(item)) + "$");
                return true;
            }
        }

        return true;
    }
}
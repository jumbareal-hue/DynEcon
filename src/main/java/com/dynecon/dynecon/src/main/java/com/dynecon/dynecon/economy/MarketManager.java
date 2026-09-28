package com.dynecon.dynecon.economy;

import java.util.HashMap;
import java.util.Map;

public class MarketManager {

    private final Map<String, Double> currentPrices = new HashMap<>();
    private final Map<String, MarketItem> marketItems = new HashMap<>();

    public static class MarketItem {
        public String material;
        public double basePrice;
        public double minPrice;
        public double maxPrice;
        public double elasticity;

        public MarketItem(String material, double basePrice, double minPrice, double maxPrice, double elasticity) {
            this.material = material;
            this.basePrice = basePrice;
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
            this.elasticity = elasticity;
        }
    }

    public void registerItem(MarketItem item) {
        marketItems.put(item.material, item);
        currentPrices.put(item.material, item.basePrice);
    }

    public double getPrice(String material) {
        return currentPrices.getOrDefault(material, 0.0);
    }

    public double buyItem(String material, int amount) {
        MarketItem item = marketItems.get(material);
        if (item == null) return 0.0;

        double currentPrice = getPrice(material);
        double totalCost = 0.0;

        for (int i = 0; i < amount; i++) {
            totalCost += currentPrice;
            currentPrice += (item.basePrice / item.elasticity);
            if (currentPrice > item.maxPrice) {
                currentPrice = item.maxPrice;
            }
        }

        currentPrices.put(material, currentPrice);
        return totalCost;
    }

    public double sellItem(String material, int amount) {
        MarketItem item = marketItems.get(material);
        if (item == null) return 0.0;

        double currentPrice = getPrice(material);
        double totalEarnings = 0.0;

        for (int i = 0; i < amount; i++) {
            totalEarnings += currentPrice;
            currentPrice -= (item.basePrice / item.elasticity);
            if (currentPrice < item.minPrice) {
                currentPrice = item.minPrice;
            }
        }

        currentPrices.put(material, currentPrice);
        return totalEarnings;
    }
}
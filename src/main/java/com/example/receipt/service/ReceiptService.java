package com.example.receipt.service;

import com.example.receipt.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptService {
    private final Map<String, Integer> receiptStore = new ConcurrentHashMap<>();

    public String processReceipt(Receipt receipt) {
        int points = calculatePoints(receipt);
        String id = UUID.randomUUID().toString();
        receiptStore.put(id, points);
        return id;
    }

    public Integer getPoints(String id) {
        return receiptStore.get(id);
    }

    private int calculatePoints(Receipt r) {
        int points = 0;
        points += (int) r.getRetailer().chars().filter(Character::isLetterOrDigit).count();
        double total = Double.parseDouble(r.getTotal());
        if (total == Math.floor(total)) points += 50;
        if (total % 0.25 == 0) points += 25;

        List<Item> items = r.getItems();
        points += (items.size() / 2) * 5;

        for (Item item : items) {
            String desc = item.getShortDescription().trim();
            if (desc.length() % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());
                points += (int) Math.ceil(price * 0.2);
            }
        }

        //if (total > 10.00) points += 5;

        LocalDate date = LocalDate.parse(r.getPurchaseDate());
        if (date.getDayOfMonth() % 2 == 1) points += 6;

        LocalTime time = LocalTime.parse(r.getPurchaseTime());
        if (time.getHour() >= 14 && time.getHour() < 16) points += 10;

        return points;
    }
}

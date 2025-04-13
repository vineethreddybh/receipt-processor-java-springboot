package com.example.receipt.controller;

import com.example.receipt.model.Receipt;
import com.example.receipt.service.ReceiptService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService service;

    public ReceiptController(ReceiptService service) {
        this.service = service;
    }

    @PostMapping("/process")
    public Map<String, String> processReceipt(@RequestBody Receipt receipt) {
        String id = service.processReceipt(receipt);
        return Map.of("id", id);
    }

    @GetMapping("/{id}/points")
    public Map<String, Integer> getPoints(@PathVariable String id) {
        Integer points = service.getPoints(id);
        return Map.of("points", points != null ? points : 0);
    }
}

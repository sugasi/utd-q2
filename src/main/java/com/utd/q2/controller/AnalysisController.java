package com.utd.q2.controller;


import com.utd.q2.data.entity.Product;
import com.utd.q2.service.reload.ReloadService;
import com.utd.q2.service.revenue.RevenueService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Setter
@AllArgsConstructor
@Slf4j
public class AnalysisController {

    private final ReloadService reloadService;

    private final RevenueService revenueService;

    @GetMapping("/analysis/reload")
    public ResponseEntity<String> reload() {
        if (reloadService.reload()) {
            return ResponseEntity.ok("reload successful");
        }
        return ResponseEntity.ok("reload failed");
    }

    @GetMapping("/analysis/totalRevenue")
    public ResponseEntity<String> totalRevenue(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        double total = revenueService.totalRevenue(from, to);
        if (total > 0 ){
            return ResponseEntity.ok("total revenue: " + total);
        } else {
            return ResponseEntity.ok("There is no recorded activity in the provided region");
        }
    }

    @GetMapping("/analysis/totalRevenueByProduct/{productId}")
    public ResponseEntity<String> totalRevenueByProduct(@RequestParam LocalDate from, @RequestParam LocalDate to, @PathVariable String productId) {
        double total = revenueService.totalRevenueByProduct(from, to, productId);
        if (total > 0 ){
            return ResponseEntity.ok("total revenue: " + total);
        } else {
            return ResponseEntity.ok("There is no recorded activity in the provided region for the requested product");
        }
    }

    @GetMapping("/analysis/topProducts/{n}")
    public ResponseEntity<List<Product>> topProducts(@RequestParam LocalDate from, @RequestParam LocalDate to, @PathVariable int n) {
        List<Product> products =
    }

}

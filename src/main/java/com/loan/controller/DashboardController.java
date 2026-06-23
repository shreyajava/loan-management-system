package com.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loan.dto.DashboardResponse;
import com.loan.service.DashboardService;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {

        return service.getDashboardData();
    }
}
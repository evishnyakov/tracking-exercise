package com.test.driver.web;

import com.test.driver.Driver;
import com.test.driver.DriverRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/drivers")
public class DriversController {

    private final @NonNull DriverRepository drivers;

    @GetMapping
    public Driver getDriver() {
        return drivers.findAll().get(0);
    }

}

package com.test.myapp.controllers;

import com.test.myapp.security.PersonDetails;
import com.test.myapp.services.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    private final AdminService adminService;

    public IndexController(AdminService adminService) {
        this.adminService=adminService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        log.info("User defails fetched " + personDetails.getPerson().getUsername());
        return "hello";
    }

    @GetMapping("/admin")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminPage() {
        adminService.doAdminStuff();
        return "admin";
    }

}

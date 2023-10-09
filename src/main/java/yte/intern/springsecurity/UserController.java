package yte.intern.springsecurity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String user(){
        return "ben userim";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin")
    public String admin(){
        return "admin";
    }
}

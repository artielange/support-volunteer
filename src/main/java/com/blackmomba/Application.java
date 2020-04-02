package com.blackmomba;

import com.blackmomba.supportvolunteer.gui.InsertClientForm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final InsertClientForm insertClientForm;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

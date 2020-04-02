package com.blackmomba;

import com.blackmomba.supportvolunteer.gui.StartupForm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final StartupForm startupForm;

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false);
        builder.run(args);
    }

}

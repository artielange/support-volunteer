package com.blackmomba;

import com.blackmomba.supportvolunteer.gui.InsertClientForm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {


    public static void main(String[] args) {
        InsertClientForm insertClientForm = new InsertClientForm();
    }

}

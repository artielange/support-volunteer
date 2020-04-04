package com.blackmomba.supportvolunteer.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ComboItem {

    private Object key;
    private String value;

    @Override
    public String toString() {
        return value;
    }

}

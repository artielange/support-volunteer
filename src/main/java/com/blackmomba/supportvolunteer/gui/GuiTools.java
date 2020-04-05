package com.blackmomba.supportvolunteer.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GuiTools {

    static JPanel getForm(String[] labels, String[] fieldNames) {
        JPanel jPanel = new JPanel(new SpringLayout());
        for (int x = 0; x < labels.length; x++) {
            JLabel jLabel = new JLabel(labels[x], JLabel.TRAILING);
            jPanel.add(jLabel);
            JTextField textField = new JTextField(10);
            textField.setName(fieldNames[x]);
            jLabel.setLabelFor(textField);
            jPanel.add(textField);
        }
        SpringUtilities.makeCompactGrid(jPanel, labels.length, 2, 6, 6, 6, 6);
        return jPanel;
    }

    static JPanel getExistingRecordsJPanel(String title, String content) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        JLabel existingSupportEventsLabel = new JLabel(title, JLabel.LEFT);
        JTextArea jTextArea = new JTextArea(8, 150);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        jTextArea.setText(content);
        jTextArea.setEditable(false);
        p.add(existingSupportEventsLabel);
        existingSupportEventsLabel.setLabelFor(jScrollPane);
        p.add(jScrollPane);
        return p;
    }

    static JButton getButton(String caption, String actionCommand, ActionListener actionListener) {
        JButton jButton = new JButton(caption);
        jButton.setActionCommand(actionCommand);
        jButton.addActionListener(actionListener);
        return jButton;
    }

    static String getTextFieldValueByName(HashMap<String, Component> componentHashMap, String name) {
        JTextField textField = (JTextField) componentHashMap.getOrDefault(name, null);
        return textField != null ? textField.getText().trim() : "";
    }

}

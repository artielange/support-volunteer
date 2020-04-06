package com.blackmomba.supportvolunteer.gui;

import com.blackmomba.supportvolunteer.data.FeedbackRepository;
import com.blackmomba.supportvolunteer.domain.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.stream.Collectors;

import static com.blackmomba.supportvolunteer.gui.GuiTools.getButton;
import static com.blackmomba.supportvolunteer.gui.GuiTools.getTextFieldValueByName;

@Service
public class AddFeedbackForm extends JFrame implements ActionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private FeedbackRepository feedbackRepository;

    private HashMap<String, Component> componentHashMap;

    public AddFeedbackForm(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
        this.componentHashMap = new HashMap<>();
        this.setTitle("Ajouter un commentaire");
        this.setLayout(new SpringLayout());
        String[] labels = {"Date et Heure: ", "Type de Commentaire: ", "NAS Client: "};
        String[] fieldNames = {"dateTime", "feedbackType", "clientNas"};
        // Make the form
        JPanel form = GuiTools.getForm(labels, fieldNames);
        for (Component component : form.getComponents()) {
            componentHashMap.put(component.getName(), component);
        }
        JButton addButton = getButton("Ajouter", "add", this);
        JButton cancelButton = getButton("Annuler", "cancel", this);
        form.add(cancelButton);
        form.add(addButton);
        int numPairs = labels.length + 1;
        SpringUtilities.makeCompactGrid(form, numPairs, 2, 6, 6, 6, 6);
        this.setContentPane(form);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addFeedback();
                this.setVisible(false);
                break;
            case "cancel":
                this.setVisible(false);
                break;
        }
    }

    private void addFeedback() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm");
            feedbackRepository.save(new Feedback(
                    0L,
                    formatter.parse(getTextFieldValueByName(componentHashMap, "dateTime")),
                    getTextFieldValueByName(componentHashMap, "feedbackType"),
                    getTextFieldValueByName(componentHashMap, "clientNas")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage(), e);
        }
    }

    public JPanel getExistingRecordsJPanel() {
        String title = "Commentaires existants";
        String content = String.format("%-6s %-20s %-20s %-12s\n",
                "ID", "Date et Heure", "Type de commentaire", "NAS Client");
        content += feedbackRepository.findAll().stream().map(Feedback::toString).collect(Collectors.joining("\n"));
        return GuiTools.getExistingRecordsJPanel(title, content);
    }

}

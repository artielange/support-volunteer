package com.blackmomba.supportvolunteer.gui;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Service
public class StartupForm extends JFrame implements ActionListener {

    private AddClientForm addClientForm;
    private AddFeedbackForm addFeedbackForm;
    private AddSupportEventForm addSupportEventForm;
    private AddSupportRequestForm addSupportRequestForm;
    private AddTeamForm addTeamForm;
    private AddVehicleForm addVehicleForm;
    private AddVolunteerForm addVolunteerForm;

    public StartupForm(
            AddClientForm addClientForm,
            AddFeedbackForm addFeedbackForm,
            AddSupportEventForm addSupportEventForm,
            AddSupportRequestForm addSupportRequestForm,
            AddTeamForm addTeamForm,
            AddVehicleForm addVehicleForm,
            AddVolunteerForm addVolunteerForm) {
        this.addClientForm = addClientForm;
        this.addFeedbackForm = addFeedbackForm;
        this.addSupportEventForm = addSupportEventForm;
        this.addSupportRequestForm = addSupportRequestForm;
        this.addTeamForm = addTeamForm;
        this.addVehicleForm = addVehicleForm;
        this.addVolunteerForm = addVolunteerForm;
        // Create the frame
        this.setTitle("Gestion des benevoles pour l'ille de Montreal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new SpringLayout());

        // Create the buttons
        JButton addClientButton = getButton("Ajouter un client","addCustomer");
        JButton addFeedbackButton = getButton("Ajouter un commentaire", "addFeedback");
        JButton addSupportEventButton = getButton("Ajouter un accompagnement", "addSupportEvent");
        JButton addSupportRequestButton = getButton("Ajouter une demande", "addSupportRequest");
        JButton addTeamButton = getButton("Creer un equipe", "addTeam");
        JButton addVehicleButton = getButton("Ajouter un vehicule", "addVehicle");
        JButton addVolunteerButton = getButton("Ajouter un benevole", "addVolunteer");
        // Create the existing records panels
        JPanel existingClientsJPanel = addClientForm.getExistingRecordsJPanel();
        JPanel existingFeedbackJPanel = addFeedbackForm.getExistingRecordsJPanel();
        JPanel existingSupportEventsJPanel = addSupportEventForm.getExistingRecordsJPanel();
        JPanel existingSupportRequestJPanel = addSupportRequestForm.getExistingRecordsJPanel();
        JPanel existingTeamsJPanel = addTeamForm.getExistingRecordsJPanel();
        JPanel existingVehiclesJPanel = addVehicleForm.getExistingRecordsJPanel();
        JPanel existingVolunteersJPanel = addVolunteerForm.getExistingRecordsJPanel();
        // Put it all together
        this.getContentPane().add(addClientButton);
        this.getContentPane().add(existingClientsJPanel);
        this.getContentPane().add(addFeedbackButton);
        this.getContentPane().add(existingFeedbackJPanel);
        this.getContentPane().add(addSupportEventButton);
        this.getContentPane().add(existingSupportEventsJPanel);
        this.getContentPane().add(addSupportRequestButton);
        this.getContentPane().add(existingSupportRequestJPanel);
        this.getContentPane().add(addTeamButton);
        this.getContentPane().add(existingTeamsJPanel);
        this.getContentPane().add(addVehicleButton);
        this.getContentPane().add(existingVehiclesJPanel);
        this.getContentPane().add(addVolunteerButton);
        this.getContentPane().add(existingVolunteersJPanel);
        SpringUtilities.makeCompactGrid(this.getContentPane(), 7, 2, 6, 6, 6, 6);
        this.pack();
        this.setVisible(true);
    }

    private JButton getButton(String caption, String actionCommand) {
        JButton jButton = new JButton(caption);
        jButton.setFont(new Font("Serif", Font.PLAIN, 14));
        jButton.setActionCommand(actionCommand);
        jButton.addActionListener(this);
        jButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        return jButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "addCustomer":
                addClientForm.setVisible(true);
                break;
            case "addFeedback":
                addFeedbackForm.setVisible(true);
                break;
            case "addSupportEvent":
                addSupportEventForm.setVisible(true);
                break;
            case "addSupportRequest":
                addSupportRequestForm.setVisible(true);
                break;
            case "addTeam":
                addTeamForm.setVisible(true);
                break;
            case "addVehicle":
                addVehicleForm.setVisible(true);
                break;
            case "addVolunteer":
                addVolunteerForm.setVisible(true);
                break;
        }
    }

}

package com.blackmomba.supportvolunteer.gui;

import com.blackmomba.supportvolunteer.data.SectorRepository;
import com.blackmomba.supportvolunteer.data.TeamRepository;
import com.blackmomba.supportvolunteer.data.VolunteerRepository;
import com.blackmomba.supportvolunteer.domain.Sector;
import com.blackmomba.supportvolunteer.domain.Team;
import com.blackmomba.supportvolunteer.domain.Volunteer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.stream.Collectors;

import static com.blackmomba.supportvolunteer.gui.GuiTools.getButton;

@Service
public class AddTeamForm extends JFrame implements ActionListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private TeamRepository teamRepository;

    private HashMap<String, Component> componentHashMap;

    public AddTeamForm(
            TeamRepository teamRepository,
            VolunteerRepository volunteerRepository,
            SectorRepository sectorRepository) {
        this.teamRepository = teamRepository;
        this.componentHashMap = new HashMap<>();
        this.setTitle("Creer un Equipe");
        this.setLayout(new SpringLayout());
        JPanel p = new JPanel(new SpringLayout());
        JComboBox<ComboItem> volunteer1JComboBox = new JComboBox<>();
        JComboBox<ComboItem> volunteer2JComboBox = new JComboBox<>();
        for (Volunteer volunteer : volunteerRepository.findAll()) {
            volunteer1JComboBox.addItem(new ComboItem(volunteer.getSin(), volunteer.getFirstName() + ' ' + volunteer.getLastName()));
            volunteer2JComboBox.addItem(new ComboItem(volunteer.getSin(), volunteer.getFirstName() + ' ' + volunteer.getLastName()));
        }
        JLabel volunteer1Label = new JLabel("NAS Benevole 1: ", JLabel.TRAILING);
        p.add(volunteer1Label);
        volunteer1Label.setLabelFor(volunteer1JComboBox);
        p.add(volunteer1JComboBox);
        JLabel volunteer2Label = new JLabel("NAS Benevole 2: ", JLabel.TRAILING);
        p.add(volunteer2Label);
        volunteer2Label.setLabelFor(volunteer2JComboBox);
        p.add(volunteer2JComboBox);
        JComboBox<ComboItem> sectorJComboBox = new JComboBox<>();
        for (Sector sector : sectorRepository.findAll()) {
            sectorJComboBox.addItem(new ComboItem(sector.getId(), sector.getName()));
        }
        JLabel sectorLabel = new JLabel("Secteur: ", JLabel.TRAILING);
        p.add(sectorLabel);
        sectorLabel.setLabelFor(sectorJComboBox);
        p.add(sectorJComboBox);
        componentHashMap.put("sector", sectorJComboBox);
        componentHashMap.put("volunteerSin1", volunteer1JComboBox);
        componentHashMap.put("volunteerSin2", volunteer2JComboBox);
        JButton addButton = getButton("Ajouter", "add", this);
        JButton cancelButton = getButton("Annuler", "cancel", this);
        p.add(cancelButton);
        p.add(addButton);
        SpringUtilities.makeCompactGrid(p, 4, 2, 6, 6, 6, 6);
        p.setOpaque(true);
        this.setContentPane(p);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add":
                addTeam();
                this.setVisible(false);
                break;
            case "cancel":
                this.setVisible(false);
                break;
        }
    }

    private void addTeam() {
        Component sectorComponent = componentHashMap.getOrDefault("sector", null);
        Component volunteerSin1Component = componentHashMap.getOrDefault("volunteerSin1", null);
        Component volunteerSin2Component = componentHashMap.getOrDefault("volunteerSin2", null);
        if (sectorComponent == null || volunteerSin1Component == null || volunteerSin2Component == null) { return; }
        ComboItem sectorComboItem = (ComboItem) ((JComboBox<?>) sectorComponent).getSelectedItem();
        ComboItem volunteerSin1ComboItem = (ComboItem) ((JComboBox<?>) volunteerSin1Component).getSelectedItem();
        ComboItem volunteerSin2ComboItem = (ComboItem) ((JComboBox<?>) volunteerSin2Component).getSelectedItem();
        if (sectorComboItem == null || volunteerSin1ComboItem == null || volunteerSin2ComboItem == null) { return; }
        try {
            teamRepository.save(new Team(
                    0L,
                    true,
                    volunteerSin1ComboItem.getKey().toString(),
                    volunteerSin2ComboItem.getKey().toString(),
                    (Long) sectorComboItem.getKey()));
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
        String title = "Equipes existants";
        String content = String.format("%-6s %-15s %-15s %-15s %-1s\n",
                "ID", "Disponible", "NAS Benevole 1", "NAS Benevole 2", "Secteur");
        content += teamRepository.findAll().stream().map(Team::toString).collect(Collectors.joining("\n"));
        return GuiTools.getExistingRecordsJPanel(title, content);
    }

}

package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import Model.Patient;
import controller.MedicalController;

/**
 * Main GUI window (View layer) for the medical system.
 * Displays a simple menu and loads patient data into a table.
 */
public class MainMenu implements ActionListener {

    private JFrame window;                 // Main application window
    private JPanel panelNorth, panelSouth; // Panels for layout
    private JTable patientTable;           // Table to display patients
    private DefaultTableModel tableModel;  // Table model for patient data
    private JButton btnLoadPatients;       // Button to load patients

    private MedicalController controller;  // MVC: View → Controller

    /**
     * Constructor: builds the GUI.
     */
    public MainMenu() {

        controller = new MedicalController(); // Create controller instance

        // Create main window
        window = new JFrame("Medical System");
        window.setBounds(100, 100, 800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // NORTH PANEL (buttons)
        panelNorth = new JPanel();
        btnLoadPatients = new JButton("Load Patients");
        btnLoadPatients.addActionListener(this); // Listen for clicks
        panelNorth.add(btnLoadPatients);

        // TABLE SETUP
        String[] columns = {"NHS Number", "First Name", "Last Name"};
        tableModel = new DefaultTableModel(columns, 0);
        patientTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(patientTable);

        // SOUTH PANEL (exit button)
        panelSouth = new JPanel();
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> System.exit(0));
        panelSouth.add(btnExit);

        // Add components to window
        window.add(panelNorth, BorderLayout.NORTH);
        window.add(scrollPane, BorderLayout.CENTER);
        window.add(panelSouth, BorderLayout.SOUTH);

        window.setVisible(true); // Show the window
    }

    /**
     * Handles button clicks.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLoadPatients) {
            loadPatientsIntoTable();
        }
    }

    /**
     * Loads patients from the controller and displays them in the table.
     */
    private void loadPatientsIntoTable() {

        // Clear existing rows
        tableModel.setRowCount(0);

        // Get patients from controller
        List<Patient> patients = controller.getAllPatients();

        // Add each patient to the table
        for (Patient p : patients) {
            tableModel.addRow(new Object[]{
                    p.getNhsNumber(),
                    p.getFirstName(),
                    p.getlastName()
            });
        }
    }

    /**
     * Main method to launch the GUI.
     */
    public static void main(String[] args) {
        new MainMenu(); // Start the GUI
    }
}


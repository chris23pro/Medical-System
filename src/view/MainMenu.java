package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import Model.Appointment;
import Model.ClinicalDocument;
import Model.Clinician;
import Model.Facility;
import Model.Patient;
import Model.Prescription;
import Model.Referral;
import Model.Staff;
import controller.MedicalController;

/**
 * Main GUI window (View layer) for the medical system.
 * Displays a simple menu and loads patient data into a table.
 */
public class MainMenu implements ActionListener {

    private JFrame window;                 // Main application window
    private JPanel panelNorth, panelSouth; // Panels for layout
    private JTable patientTable;
    private JTable clinicianTable;
    private JTable appointmentTable;
    private JTable prescriptionTable;
    private JTable referralTable;
    private JTable facilityTable;
    private JTable staffTable;
    private DefaultTableModel patientTableModel;
    private DefaultTableModel clinicianTableModel;
    private DefaultTableModel appointmentTableModel;
    private DefaultTableModel prescriptionTableModel;
    private DefaultTableModel referralTableModel;
    private DefaultTableModel facilityTableModel;
    private DefaultTableModel staffTableModel;
    private JButton btnLoadData;

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
        btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(this); // Listen for clicks
        panelNorth.add(btnLoadData);

        // TABLE SETUP
        patientTableModel = new DefaultTableModel(new String[]{"Patient ID", "NHS Number", "First Name", "Last Name"}, 0);
        clinicianTableModel = new DefaultTableModel(new String[]{"Clinician ID", "First Name", "Last Name", "License"}, 0);
        appointmentTableModel = new DefaultTableModel(new String[]{"Appointment ID", "Patient ID", "Clinician ID", "Date/Time"}, 0);
        prescriptionTableModel = new DefaultTableModel(new String[]{"Prescription ID", "Drug", "Dose", "Date"}, 0);
        referralTableModel = new DefaultTableModel(new String[]{"Referral ID", "Reason", "Target", "Date"}, 0);
        facilityTableModel = new DefaultTableModel(new String[]{"Facility ID", "Name", "Type", "Postcode"}, 0);
        staffTableModel = new DefaultTableModel(new String[]{"Staff ID", "Name", "Role", "Facility"}, 0);

        patientTable = new JTable(patientTableModel);
        clinicianTable = new JTable(clinicianTableModel);
        appointmentTable = new JTable(appointmentTableModel);
        prescriptionTable = new JTable(prescriptionTableModel);
        referralTable = new JTable(referralTableModel);
        facilityTable = new JTable(facilityTableModel);
        staffTable = new JTable(staffTableModel);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Patients", new JScrollPane(patientTable));
        tabs.addTab("Clinicians", new JScrollPane(clinicianTable));
        tabs.addTab("Appointments", new JScrollPane(appointmentTable));
        tabs.addTab("Prescriptions", new JScrollPane(prescriptionTable));
        tabs.addTab("Referrals", new JScrollPane(referralTable));
        tabs.addTab("Facilities", new JScrollPane(facilityTable));
        tabs.addTab("Staff", new JScrollPane(staffTable));

        // SOUTH PANEL (exit button)
        panelSouth = new JPanel();
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> System.exit(0));
        panelSouth.add(btnExit);

        // Add components to window
        window.add(panelNorth, BorderLayout.NORTH);
        window.add(tabs, BorderLayout.CENTER);
        window.add(panelSouth, BorderLayout.SOUTH);

        window.setVisible(true); // Show the window
    }

    /**
     * Handles button clicks.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLoadData) {
            loadData();
        }
    }

    private void loadData() {
        controller.loadPatientsFromCsv("patients.csv");
        controller.loadCliniciansFromCsv("clinicians.csv");
        controller.loadAppointmentsFromCsv("appointments.csv");
        controller.loadPrescriptionsFromCsv("prescriptions.csv");
        controller.loadReferralsFromCsv("referrals.csv");
        controller.loadFacilitiesFromCsv("facilities.csv");
        controller.loadStaffFromCsv("staff.csv");

        loadPatientsIntoTable();
        loadCliniciansIntoTable();
        loadAppointmentsIntoTable();
        loadDocumentsIntoTable();
        loadFacilitiesIntoTable();
        loadStaffIntoTable();
    }

    private void loadPatientsIntoTable() {
        patientTableModel.setRowCount(0);
        List<Patient> patients = controller.getAllPatients();
        for (Patient p : patients) {
            patientTableModel.addRow(new Object[]{
                    p.getPatientId(),
                    p.getNhsNumber(),
                    p.getFirstName(),
                    p.getLastName()
            });
        }
    }

    private void loadCliniciansIntoTable() {
        clinicianTableModel.setRowCount(0);
        List<Clinician> clinicians = controller.getAllClinicians();
        for (Clinician c : clinicians) {
            clinicianTableModel.addRow(new Object[]{
                    c.getEmployeeID(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getLicenseID()
            });
        }
    }

    private void loadAppointmentsIntoTable() {
        appointmentTableModel.setRowCount(0);
        List<Appointment> appointments = controller.getAllAppointments();
        for (Appointment a : appointments) {
            appointmentTableModel.addRow(new Object[]{
                    a.getAppId(),
                    a.getPatientId(),
                    a.getClinicianId(),
                    a.getDateTime()
            });
        }
    }

    private void loadDocumentsIntoTable() {
        prescriptionTableModel.setRowCount(0);
        referralTableModel.setRowCount(0);
        List<ClinicalDocument> documents = controller.getAllDocuments();
        for (ClinicalDocument doc : documents) {
            if (doc instanceof Prescription) {
                Prescription p = (Prescription) doc;
                prescriptionTableModel.addRow(new Object[]{
                        p.getDocumentID(),
                        p.getDrugName(),
                        p.getDosage(),
                        p.getDateCreated()
                });
            } else if (doc instanceof Referral) {
                Referral r = (Referral) doc;
                referralTableModel.addRow(new Object[]{
                        r.getDocumentID(),
                        r.getReferralReason(),
                        r.getTargetSpecialist(),
                        r.getDateCreated()
                });
            }
        }
    }

    private void loadFacilitiesIntoTable() {
        facilityTableModel.setRowCount(0);
        List<Facility> facilities = controller.getAllFacilities();
        for (Facility f : facilities) {
            facilityTableModel.addRow(new Object[]{
                    f.getFacilityId(),
                    f.getName(),
                    f.getType(),
                    f.getPostcode()
            });
        }
    }

    private void loadStaffIntoTable() {
        staffTableModel.setRowCount(0);
        List<Staff> staff = controller.getAllStaff();
        for (Staff s : staff) {
            staffTableModel.addRow(new Object[]{
                    s.getStaffId(),
                    s.getFirstName() + " " + s.getLastName(),
                    s.getRole(),
                    s.getFacilityId()
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


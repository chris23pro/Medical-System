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
        tabs.addTab("Patients", buildTabPanel(patientTable, this::addPatient, this::editPatient, this::deletePatient));
        tabs.addTab("Clinicians", buildTabPanel(clinicianTable, this::addClinician, this::editClinician, this::deleteClinician));
        tabs.addTab("Appointments", buildTabPanel(appointmentTable, this::addAppointment, this::editAppointment, this::deleteAppointment));
        tabs.addTab("Prescriptions", buildTabPanel(prescriptionTable, this::addPrescription, this::editPrescription, this::deletePrescription));
        tabs.addTab("Referrals", buildTabPanel(referralTable, this::addReferral, this::editReferral, this::deleteReferral));
        tabs.addTab("Facilities", buildTabPanel(facilityTable, this::addFacility, this::editFacility, this::deleteFacility));
        tabs.addTab("Staff", buildTabPanel(staffTable, this::addStaff, this::editStaff, this::deleteStaff));

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

    private JPanel buildTabPanel(JTable table, Runnable onAdd, Runnable onEdit, Runnable onDelete) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actions = new JPanel();
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        addButton.addActionListener(e -> onAdd.run());
        editButton.addActionListener(e -> onEdit.run());
        deleteButton.addActionListener(e -> onDelete.run());
        actions.add(addButton);
        actions.add(editButton);
        actions.add(deleteButton);
        panel.add(actions, BorderLayout.SOUTH);
        return panel;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(window, message);
    }

    private boolean ensureRowSelected(JTable table) {
        if (table.getSelectedRow() < 0) {
            showMessage("Please select a row first.");
            return false;
        }
        return true;
    }

    private String[] promptForFields(String title, String[] labels, String[] defaults) {
        JPanel panel = new JPanel(new GridLayout(labels.length, 2, 8, 6));
        JTextField[] fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i]));
            JTextField field = new JTextField();
            if (defaults != null && defaults.length > i && defaults[i] != null) {
                field.setText(defaults[i]);
            }
            fields[i] = field;
            panel.add(field);
        }

        int result = JOptionPane.showConfirmDialog(window, panel, title, JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return null;
        }

        String[] values = new String[labels.length];
        for (int i = 0; i < labels.length; i++) {
            values[i] = fields[i].getText().trim();
        }
        return values;
    }

    private void addPatient() {
        String[] fields = promptForFields("Add Patient",
                new String[]{"Patient ID", "First Name", "Last Name", "Date of Birth", "Phone", "Email",
                        "NHS Number", "Address", "Gender", "Postcode", "Emergency Contact Name",
                        "Emergency Contact Phone", "Registration Date", "GP Surgery ID"},
                null);
        if (fields == null) {
            return;
        }

        Patient patient = new Patient(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
                fields[6], fields[7], fields[8], fields[9], fields[10], fields[11], fields[12], fields[13]);
        showMessage(controller.registerPatient(patient));
        loadPatientsIntoTable();
    }

    private void editPatient() {
        if (!ensureRowSelected(patientTable)) {
            return;
        }
        int row = patientTable.getSelectedRow();
        String existingNhs = patientTableModel.getValueAt(row, 1).toString();
        String[] defaults = new String[]{
                patientTableModel.getValueAt(row, 0).toString(),
                patientTableModel.getValueAt(row, 2).toString(),
                patientTableModel.getValueAt(row, 3).toString(),
                "", "", "", existingNhs, "", "", "", "", "", "", ""
        };
        String[] fields = promptForFields("Edit Patient",
                new String[]{"Patient ID", "First Name", "Last Name", "Date of Birth", "Phone", "Email",
                        "NHS Number", "Address", "Gender", "Postcode", "Emergency Contact Name",
                        "Emergency Contact Phone", "Registration Date", "GP Surgery ID"},
                defaults);
        if (fields == null) {
            return;
        }

        Patient patient = new Patient(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
                fields[6], fields[7], fields[8], fields[9], fields[10], fields[11], fields[12], fields[13]);
        showMessage(controller.updatePatient(existingNhs, patient));
        loadPatientsIntoTable();
    }

    private void deletePatient() {
        if (!ensureRowSelected(patientTable)) {
            return;
        }
        int row = patientTable.getSelectedRow();
        String nhsNumber = patientTableModel.getValueAt(row, 1).toString();
        showMessage(controller.deletePatient(nhsNumber));
        loadPatientsIntoTable();
    }

    private void addClinician() {
        String[] fields = promptForFields("Add Clinician",
                new String[]{"Clinician ID", "First Name", "Last Name", "Date of Birth", "Phone", "Email",
                        "Employee ID", "License ID"},
                null);
        if (fields == null) {
            return;
        }
        Clinician clinician = new Clinician(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
                fields[6], fields[7]);
        showMessage(controller.registerClinician(clinician));
        loadCliniciansIntoTable();
    }

    private void editClinician() {
        if (!ensureRowSelected(clinicianTable)) {
            return;
        }
        int row = clinicianTable.getSelectedRow();
        String existingEmployeeId = clinicianTableModel.getValueAt(row, 0).toString();
        String[] defaults = new String[]{
                clinicianTableModel.getValueAt(row, 0).toString(),
                clinicianTableModel.getValueAt(row, 1).toString(),
                clinicianTableModel.getValueAt(row, 2).toString(),
                "", "", "", existingEmployeeId, clinicianTableModel.getValueAt(row, 3).toString()
        };
        String[] fields = promptForFields("Edit Clinician",
                new String[]{"Clinician ID", "First Name", "Last Name", "Date of Birth", "Phone", "Email",
                        "Employee ID", "License ID"},
                defaults);
        if (fields == null) {
            return;
        }
        Clinician clinician = new Clinician(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5],
                fields[6], fields[7]);
        showMessage(controller.updateClinician(existingEmployeeId, clinician));
        loadCliniciansIntoTable();
    }

    private void deleteClinician() {
        if (!ensureRowSelected(clinicianTable)) {
            return;
        }
        int row = clinicianTable.getSelectedRow();
        String employeeId = clinicianTableModel.getValueAt(row, 0).toString();
        showMessage(controller.deleteClinician(employeeId));
        loadCliniciansIntoTable();
    }

    private void addAppointment() {
        String[] fields = promptForFields("Add Appointment",
                new String[]{"Appointment ID", "Patient ID", "Clinician ID", "Facility ID",
                        "Date", "Time", "Appointment Type", "Status", "Reason for Visit"},
                null);
        if (fields == null) {
            return;
        }
        Appointment appointment = new Appointment(fields[0], fields[1], fields[2], fields[3], fields[4],
                fields[5], fields[6], fields[7], fields[8]);
        showMessage(controller.bookAppointment(fields[1], fields[2], appointment));
        loadAppointmentsIntoTable();
    }

    private void editAppointment() {
        if (!ensureRowSelected(appointmentTable)) {
            return;
        }
        int row = appointmentTable.getSelectedRow();
        String existingId = appointmentTableModel.getValueAt(row, 0).toString();
        String dateTime = appointmentTableModel.getValueAt(row, 3).toString();
        String[] dateTimeParts = dateTime.split(" ", 2);
        String date = dateTimeParts.length > 0 ? dateTimeParts[0] : "";
        String time = dateTimeParts.length > 1 ? dateTimeParts[1] : "";
        String[] defaults = new String[]{
                existingId,
                appointmentTableModel.getValueAt(row, 1).toString(),
                appointmentTableModel.getValueAt(row, 2).toString(),
                "",
                date,
                time,
                "",
                "",
                ""
        };
        String[] fields = promptForFields("Edit Appointment",
                new String[]{"Appointment ID", "Patient ID", "Clinician ID", "Facility ID",
                        "Date", "Time", "Appointment Type", "Status", "Reason for Visit"},
                defaults);
        if (fields == null) {
            return;
        }
        Appointment appointment = new Appointment(fields[0], fields[1], fields[2], fields[3], fields[4],
                fields[5], fields[6], fields[7], fields[8]);
        showMessage(controller.updateAppointment(existingId, appointment));
        loadAppointmentsIntoTable();
    }

    private void deleteAppointment() {
        if (!ensureRowSelected(appointmentTable)) {
            return;
        }
        int row = appointmentTable.getSelectedRow();
        String appointmentId = appointmentTableModel.getValueAt(row, 0).toString();
        showMessage(controller.cancelAppointment(appointmentId));
        loadAppointmentsIntoTable();
    }

    private void addPrescription() {
        String[] fields = promptForFields("Add Prescription",
                new String[]{"Prescription ID", "Title", "Content", "Date", "Drug Name", "Dosage"},
                null);
        if (fields == null) {
            return;
        }
        controller.createPrescription(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
        showMessage("Prescription added successfully.");
        loadDocumentsIntoTable();
    }

    private void editPrescription() {
        if (!ensureRowSelected(prescriptionTable)) {
            return;
        }
        int row = prescriptionTable.getSelectedRow();
        String existingId = prescriptionTableModel.getValueAt(row, 0).toString();
        String[] defaults = new String[]{
                existingId,
                "",
                "",
                prescriptionTableModel.getValueAt(row, 3).toString(),
                prescriptionTableModel.getValueAt(row, 1).toString(),
                prescriptionTableModel.getValueAt(row, 2).toString()
        };
        String[] fields = promptForFields("Edit Prescription",
                new String[]{"Prescription ID", "Title", "Content", "Date", "Drug Name", "Dosage"},
                defaults);
        if (fields == null) {
            return;
        }
        showMessage(controller.updatePrescription(existingId, fields[1], fields[2], fields[3], fields[4], fields[5]));
        loadDocumentsIntoTable();
    }

    private void deletePrescription() {
        if (!ensureRowSelected(prescriptionTable)) {
            return;
        }
        int row = prescriptionTable.getSelectedRow();
        String documentId = prescriptionTableModel.getValueAt(row, 0).toString();
        showMessage(controller.deleteDocument(documentId));
        loadDocumentsIntoTable();
    }

    private void addReferral() {
        String[] fields = promptForFields("Add Referral",
                new String[]{"Referral ID", "Title", "Content", "Date", "Reason", "Target Specialist", "Specialist Doctor"},
                null);
        if (fields == null) {
            return;
        }
        controller.createReferral(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]);
        showMessage("Referral added successfully.");
        loadDocumentsIntoTable();
    }

    private void editReferral() {
        if (!ensureRowSelected(referralTable)) {
            return;
        }
        int row = referralTable.getSelectedRow();
        String existingId = referralTableModel.getValueAt(row, 0).toString();
        String[] defaults = new String[]{
                existingId,
                "",
                "",
                referralTableModel.getValueAt(row, 3).toString(),
                referralTableModel.getValueAt(row, 1).toString(),
                referralTableModel.getValueAt(row, 2).toString(),
                ""
        };
        String[] fields = promptForFields("Edit Referral",
                new String[]{"Referral ID", "Title", "Content", "Date", "Reason", "Target Specialist", "Specialist Doctor"},
                defaults);
        if (fields == null) {
            return;
        }
        showMessage(controller.updateReferral(existingId, fields[1], fields[2], fields[3], fields[4], fields[5], fields[6]));
        loadDocumentsIntoTable();
    }

    private void deleteReferral() {
        if (!ensureRowSelected(referralTable)) {
            return;
        }
        int row = referralTable.getSelectedRow();
        String documentId = referralTableModel.getValueAt(row, 0).toString();
        showMessage(controller.deleteDocument(documentId));
        loadDocumentsIntoTable();
    }

    private void addFacility() {
        String[] fields = promptForFields("Add Facility",
                new String[]{"Facility ID", "Name", "Type", "Address", "Postcode", "Phone", "Email",
                        "Opening Hours", "Manager Name", "Capacity", "Specialities Offered"},
                null);
        if (fields == null) {
            return;
        }
        Facility facility = new Facility(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6],
                fields[7], fields[8], fields[9], fields[10]);
        showMessage(controller.addFacility(facility));
        loadFacilitiesIntoTable();
    }

    private void editFacility() {
        if (!ensureRowSelected(facilityTable)) {
            return;
        }
        int row = facilityTable.getSelectedRow();
        String existingId = facilityTableModel.getValueAt(row, 0).toString();
        String[] defaults = new String[]{
                existingId,
                facilityTableModel.getValueAt(row, 1).toString(),
                facilityTableModel.getValueAt(row, 2).toString(),
                "",
                facilityTableModel.getValueAt(row, 3).toString(),
                "",
                "",
                "",
                "",
                "",
                ""
        };
        String[] fields = promptForFields("Edit Facility",
                new String[]{"Facility ID", "Name", "Type", "Address", "Postcode", "Phone", "Email",
                        "Opening Hours", "Manager Name", "Capacity", "Specialities Offered"},
                defaults);
        if (fields == null) {
            return;
        }
        Facility facility = new Facility(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6],
                fields[7], fields[8], fields[9], fields[10]);
        showMessage(controller.updateFacility(existingId, facility));
        loadFacilitiesIntoTable();
    }

    private void deleteFacility() {
        if (!ensureRowSelected(facilityTable)) {
            return;
        }
        int row = facilityTable.getSelectedRow();
        String facilityId = facilityTableModel.getValueAt(row, 0).toString();
        showMessage(controller.deleteFacility(facilityId));
        loadFacilitiesIntoTable();
    }

    private void addStaff() {
        String[] fields = promptForFields("Add Staff",
                new String[]{"Staff ID", "First Name", "Last Name", "Role", "Department", "Facility ID",
                        "Phone", "Email", "Employment Status", "Start Date", "Line Manager", "Access Level"},
                null);
        if (fields == null) {
            return;
        }
        Staff staff = new Staff(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6],
                fields[7], fields[8], fields[9], fields[10], fields[11]);
        showMessage(controller.addStaff(staff));
        loadStaffIntoTable();
    }

    private void editStaff() {
        if (!ensureRowSelected(staffTable)) {
            return;
        }
        int row = staffTable.getSelectedRow();
        String existingId = staffTableModel.getValueAt(row, 0).toString();
        String[] defaults = new String[]{
                existingId,
                staffTableModel.getValueAt(row, 1).toString().split(" ")[0],
                staffTableModel.getValueAt(row, 1).toString().contains(" ")
                        ? staffTableModel.getValueAt(row, 1).toString().split(" ", 2)[1]
                        : "",
                staffTableModel.getValueAt(row, 2).toString(),
                "",
                staffTableModel.getValueAt(row, 3).toString(),
                "",
                "",
                "",
                "",
                "",
                ""
        };
        String[] fields = promptForFields("Edit Staff",
                new String[]{"Staff ID", "First Name", "Last Name", "Role", "Department", "Facility ID",
                        "Phone", "Email", "Employment Status", "Start Date", "Line Manager", "Access Level"},
                defaults);
        if (fields == null) {
            return;
        }
        Staff staff = new Staff(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6],
                fields[7], fields[8], fields[9], fields[10], fields[11]);
        showMessage(controller.updateStaff(existingId, staff));
        loadStaffIntoTable();
    }

    private void deleteStaff() {
        if (!ensureRowSelected(staffTable)) {
            return;
        }
        int row = staffTable.getSelectedRow();
        String staffId = staffTableModel.getValueAt(row, 0).toString();
        showMessage(controller.deleteStaff(staffId));
        loadStaffIntoTable();
    }

    /**
     * Main method to launch the GUI.
     */
    public static void main(String[] args) {
        new MainMenu(); // Start the GUI
    }
}


package medicationtracking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * MedicationTracking is the central management class for the pharmacy system.
 * It stores all the patients, doctors, medications, and the prescriptions and provides
 * the methods for every required system operation.
 */
public class MedicationTracking {

    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    private int nextPatientId = 1;
    private int nextDoctorId = 1;
    private int nextMedicationId = 1;
    private int nextPrescriptionId = 1;

    //  Constructor – seeds the system with sample data


    public MedicationTracking() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        medications = new ArrayList<>();
        prescriptions = new ArrayList<>();
        seedData();
    }

    /**
     * Seeds the system with a realistic sample data so that the application
     * starts with something useful rather than a empty list.
     */
    private void seedData() {
        // Medications (mix of valid and expired dates) 
        Medication m1 = new Medication(nextMedicationId++, "Amoxicillin", "500mg", 120, LocalDate.of(2026, 8, 15));
        Medication m2 = new Medication(nextMedicationId++, "Lisinopril",  "10mg",  80,  LocalDate.of(2024, 3, 1));   // expired
        Medication m3 = new Medication(nextMedicationId++, "Metformin",   "850mg", 200, LocalDate.of(2027, 1, 20));
        Medication m4 = new Medication(nextMedicationId++, "Atorvastatin","20mg",  60,  LocalDate.of(2023, 11, 5));  // expired
        Medication m5 = new Medication(nextMedicationId++, "Ibuprofen",   "400mg", 300, LocalDate.of(2026, 6, 30));
        medications.add(m1); medications.add(m2); medications.add(m3);
        medications.add(m4); medications.add(m5);

        // Doctors 
        Doctor d1 = new Doctor(nextDoctorId++, "Dr. Sarah Collins", 45, "709-555-0101", "Cardiology");
        Doctor d2 = new Doctor(nextDoctorId++, "Dr. James Patel",   52, "709-555-0202", "General Practice");
        doctors.add(d1); doctors.add(d2);

        // Patients 
        Patient p1 = new Patient(nextPatientId++, "Alice Martin",  34, "709-555-1001");
        Patient p2 = new Patient(nextPatientId++, "Bob Nguyen",    58, "709-555-1002");
        Patient p3 = new Patient(nextPatientId++, "Carol White",   67, "709-555-1003");
        patients.add(p1); patients.add(p2); patients.add(p3);

        // Assigns the patients to doctors
        d1.addPatient(p1); d1.addPatient(p2);
        d2.addPatient(p3);

        // Prescriptions
        addPrescription(d1, p1, m1, LocalDate.of(2025, 2, 10));  // valid
        addPrescription(d1, p2, m3, LocalDate.of(2024, 1, 5));   // expired (>1 year ago)
        addPrescription(d2, p3, m5, LocalDate.of(2025, 11, 20)); // valid
    }

    //  Internal helper – builds a Prescription and links everything together

    private Prescription addPrescription(Doctor doctor, Patient patient,
                                         Medication medication, LocalDate issueDate) {
        Prescription rx = new Prescription(nextPrescriptionId++, doctor, patient, medication, issueDate);
        prescriptions.add(rx);
        patient.addPrescription(rx);
        patient.addMedication(medication);
        return rx;
    }

    //  1. ADD

    /** Adds a new patient to the system and returns it. */
    public Patient addPatient(String name, int age, String phone) {
        Patient p = new Patient(nextPatientId++, name, age, phone);
        patients.add(p);
        return p;
    }

    /** Adds a new doctor to the system and returns it. */
    public Doctor addDoctor(String name, int age, String phone, String specialization) {
        Doctor d = new Doctor(nextDoctorId++, name, age, phone, specialization);
        doctors.add(d);
        return d;
    }

    /** Adds a new medication to the system and returns it. */
    public Medication addMedication(String name, String dose, int qty, LocalDate expiry) {
        Medication m = new Medication(nextMedicationId++, name, dose, qty, expiry);
        medications.add(m);
        return m;
    }

    //  2. DELETE

    public boolean deletePatient(int id) {
        return patients.removeIf(p -> p.getId() == id);
    }

    public boolean deleteDoctor(int id) {
        return doctors.removeIf(d -> d.getId() == id);
    }

    public boolean deleteMedication(int id) {
        return medications.removeIf(m -> m.getId() == id);
    }

    //  3. EDIT

    /**
     * Edits an existing patient's details.
     */
    public boolean editPatient(int id, String newName, int newAge, String newPhone) {
        Patient p = findPatientById(id);
        if (p == null) return false;
        if (newName != null && !newName.isBlank()) p.setName(newName);
        if (newAge > 0) p.setAge(newAge);
        if (newPhone != null && !newPhone.isBlank()) p.setPhoneNumber(newPhone);
        return true;
    }

    public boolean editDoctor(int id, String newName, int newAge, String newPhone, String newSpec) {
        Doctor d = findDoctorById(id);
        if (d == null) return false;
        if (newName != null && !newName.isBlank()) d.setName(newName);
        if (newAge > 0) d.setAge(newAge);
        if (newPhone != null && !newPhone.isBlank()) d.setPhoneNumber(newPhone);
        if (newSpec != null && !newSpec.isBlank()) d.setSpecialization(newSpec);
        return true;
    }

    public boolean editMedication(int id, String newName, String newDose,
                                   int newQty, LocalDate newExpiry) {
        Medication m = findMedicationById(id);
        if (m == null) return false;
        if (newName != null && !newName.isBlank()) m.setName(newName);
        if (newDose != null && !newDose.isBlank()) m.setDose(newDose);
        if (newQty >= 0) m.setQuantityInStock(newQty);
        if (newExpiry != null) m.setExpiryDate(newExpiry);
        return true;
    }

    //  4. SEARCH

    public List<Patient> searchPatientsByName(String name) {
        List<Patient> results = new ArrayList<>();
        for (Patient p : patients) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) results.add(p);
        }
        return results;
    }

    public List<Doctor> searchDoctorsByName(String name) {
        List<Doctor> results = new ArrayList<>();
        for (Doctor d : doctors) {
            if (d.getName().toLowerCase().contains(name.toLowerCase())) results.add(d);
        }
        return results;
    }

    public List<Medication> searchMedicationsByName(String name) {
        List<Medication> results = new ArrayList<>();
        for (Medication m : medications) {
            if (m.getName().toLowerCase().contains(name.toLowerCase())) results.add(m);
        }
        return results;
    }

    //  5. FIND BY ID

    public Patient findPatientById(int id) {
        for (Patient p : patients) { if (p.getId() == id) return p; }
        return null;
    }

    public Doctor findDoctorById(int id) {
        for (Doctor d : doctors) { if (d.getId() == id) return d; }
        return null;
    }

    public Medication findMedicationById(int id) {
        for (Medication m : medications) { if (m.getId() == id) return m; }
        return null;
    }

    //  6. ASSIGN A PATIENT TO A DOCTOR

    public boolean addPatientToDoctor(int patientId, int doctorId) {
        Patient p = findPatientById(patientId);
        Doctor d = findDoctorById(doctorId);
        if (p == null || d == null) return false;
        d.addPatient(p);
        return true;
    }

    //  7. PROCESS PRESCRIPTION

    public Prescription processPrescription(int doctorId, int patientId,
                                             int medicationId, LocalDate issueDate) {
        Doctor d = findDoctorById(doctorId);
        Patient p = findPatientById(patientId);
        Medication m = findMedicationById(medicationId);
        if (d == null || p == null || m == null) return null;
        return addPrescription(d, p, m, issueDate);
    }

    //  8. REPORTS

    /** Prints a full system report: all the patients, doctors, medications, prescriptions. */
    public void printSystemReport() {
        System.out.println("\n========================================");
        System.out.println("         PHARMACY SYSTEM REPORT         ");
        System.out.println("========================================");

        System.out.println("\n--- PATIENTS (" + patients.size() + ") ---");
        if (patients.isEmpty()) System.out.println("  No patients.");
        for (Patient p : patients) System.out.println("  " + p);

        System.out.println("\n--- DOCTORS (" + doctors.size() + ") ---");
        if (doctors.isEmpty()) System.out.println("  No doctors.");
        for (Doctor d : doctors) System.out.println("  " + d);

        System.out.println("\n--- MEDICATIONS (" + medications.size() + ") ---");
        if (medications.isEmpty()) System.out.println("  No medications.");
        for (Medication m : medications) System.out.println("  " + m);

        System.out.println("\n--- PRESCRIPTIONS (" + prescriptions.size() + ") ---");
        if (prescriptions.isEmpty()) System.out.println("  No prescriptions.");
        for (Prescription rx : prescriptions) System.out.println("  " + rx);

        System.out.println("\n========================================\n");
    }

    /** Checks for expired medications and prints details for each one found. */
    public void checkExpiredMedications() {
        System.out.println("\n--- EXPIRED MEDICATION CHECK ---");
        boolean found = false;
        for (Medication m : medications) {
            if (m.isExpired()) {
                System.out.println("  [EXPIRED] " + m);
                found = true;
            }
        }
        if (!found) System.out.println("  No expired medications are found.");
        System.out.println();
    }

    /** Prints all prescriptions issued by the specified doctor. */
    public void printPrescriptionsForDoctor(int doctorId) {
        Doctor d = findDoctorById(doctorId);
        if (d == null) { System.out.println("Doctor is not found."); return; }

        System.out.println("\n--- PRESCRIPTIONS FOR: " + d.getName() + " ---");
        boolean found = false;
        for (Prescription rx : prescriptions) {
            if (rx.getDoctor().getId() == doctorId) {
                System.out.println("  " + rx);
                found = true;
            }
        }
        if (!found) System.out.println("  No prescriptions found for this doctor.");
        System.out.println();
    }

    /**
     * Prints all prescriptions for a patient (searched by name) issued in the past year,
     * then summarises to just the drug names.
     */
    public void printPrescriptionsForPatientByName(String name) {
        List<Patient> matches = searchPatientsByName(name);
        if (matches.isEmpty()) { System.out.println("No patient has been found with the name: " + name); return; }

        Patient p = matches.get(0);
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        System.out.println("\n--- PAST-YEAR PRESCRIPTIONS FOR: " + p.getName() + " ---");
        List<String> drugNames = new ArrayList<>();
        for (Prescription rx : p.getPrescriptions()) {
            if (!rx.getIssueDate().isBefore(oneYearAgo)) {
                System.out.println("  " + rx);
                drugNames.add(rx.getMedication().getName());
            }
        }
        if (drugNames.isEmpty()) {
            System.out.println("  No prescriptions in the past year.");
        } else {
            System.out.println("\n  Summary (drug names only): " + drugNames);
        }
        System.out.println();
    }

    //  9. RESTOCK

    /**
     * Restocks a medication by the given amount (or a random amount if amount is <= 0).
     */
    public void restockMedication(int medicationId, int amount) {
        Medication m = findMedicationById(medicationId);
        if (m == null) { System.out.println("Medication not found."); return; }
        int add = (amount > 0) ? amount : (int)(Math.random() * 100) + 10;
        m.restock(add);
        System.out.println("Restocked " + m.getName() + " by " + add
                + " units. New stock: " + m.getQuantityInStock());
    }

    //  10. LIST ACCESSORS

    public List<Patient> getPatients() { return patients; }
    public List<Doctor> getDoctors() { return doctors; }
    public List<Medication> getMedications() { return medications; }
    public List<Prescription> getPrescriptions() { return prescriptions; }
}

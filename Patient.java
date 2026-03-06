package medicationtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Patient extends Person and represents a patient in the pharmacy system.
 * Each patient holds a list of current medications and active prescriptions.
 */
public class Patient extends Person {

    private List<Medication> medications;
    private List<Prescription> prescriptions;

    /**
     * Creates a Patient with the given personal details.
     *
     * @param id          unique identifier
     * @param name        full name
     * @param age         age in years
     * @param phoneNumber contact phone number
     */
    public Patient(int id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    // Medications

    public List<Medication> getMedications() { return medications; }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public void removeMedication(Medication medication) {
        medications.remove(medication);
    }

    // Prescriptions

    public List<Prescription> getPrescriptions() { return prescriptions; }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    @Override
    public String toString() {
        return "PATIENT  | " + super.toString()
                + " | Medications: " + medications.size()
                + " | Prescriptions: " + prescriptions.size();
    }
}

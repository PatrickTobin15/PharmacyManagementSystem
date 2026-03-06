package medicationtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Doctor extends Person and represents a physician in the pharmacy system.
 * Each doctor has a medical specialization and manages a list of patients.
 */
public class Doctor extends Person {

    private String specialization;
    private List<Patient> patients;

    /**
     * Creates a Doctor with the given details.
     *
     * @param id             unique identifier
     * @param name           full name
     * @param age            age in years
     * @param phoneNumber    contact phone number
     * @param specialization medical specialization (e.g. "Cardiology")
     */
    public Doctor(int id, String name, int age, String phoneNumber, String specialization) {
        super(id, name, age, phoneNumber);
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

    // Getters / Setters

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public List<Patient> getPatients() { return patients; }

    public void addPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    @Override
    public String toString() {
        return "DOCTOR   | " + super.toString()
                + " | Specialization: " + specialization
                + " | Patients: " + patients.size();
    }
}

package medicationtracking;

import java.time.LocalDate;

/**
 * Prescription represents a medication order issued by a doctor for a patient.
 * The prescription expiry defaults to one year from the issue date.
 */
public class Prescription {

    private int id;
    private Doctor doctor;
    private Patient patient;
    private Medication medication;
    private LocalDate issueDate;
    private LocalDate prescriptionExpiry;

    /**
     * Creates a Prescription.  The expiry date is automatically set to
     * one year after the issue date.
     *
     * @param id         unique identifier
     * @param doctor     the prescribing doctor
     * @param patient    the patient receiving the prescription
     * @param medication the prescribed medication
     * @param issueDate  the date the prescription was issued
     */
    public Prescription(int id, Doctor doctor, Patient patient,
                        Medication medication, LocalDate issueDate) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.issueDate = issueDate;
        this.prescriptionExpiry = issueDate.plusYears(1);
    }

    // Getters

    public int getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public Medication getMedication() { return medication; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getPrescriptionExpiry() { return prescriptionExpiry; }

    /**
     * Returns true when today is after the prescription's expiry date.
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(prescriptionExpiry);
    }

    @Override
    public String toString() {
        return "PRESCRIPTION | ID: " + id
                + " | Doctor: " + doctor.getName()
                + " | Patient: " + patient.getName()
                + " | Medication: " + medication.getName() + " (" + medication.getDose() + ")"
                + " | Issued: " + issueDate
                + " | Expires: " + prescriptionExpiry
                + (isExpired() ? "  [EXPIRED]" : "");
    }
}

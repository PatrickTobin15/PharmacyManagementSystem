package medicationtracking;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Main menu for the Pharmacy Management System.
 */
public class EmptyMenu {

    public static void main(String[] args) {
        MedicationTracking system = new MedicationTracking();
        boolean exit = false;

        while (!exit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nWelcome To The Pharmacy Med Tracking System");
            System.out.println("What would you like to do? ");
            System.out.println("1:  Add A New Patient");
            System.out.println("2:  Add A New Doctor");
            System.out.println("3:  Add A New Medication To The Pharmacy");
            System.out.println("4:  Print System Report");
            System.out.println("5:  Check If Meds Are Expired");
            System.out.println("6:  Process A New Prescription");
            System.out.println("7:  Print All Scripts For Specific Doctor");
            System.out.println("8:  Restock The Drugs In The Pharmacy");
            System.out.println("9:  Print All Scripts For Specific Patient");
            System.out.println("10: Edit A Patient, Doctor, or Medication");
            System.out.println("11: Delete A Patient, Doctor, or Medication");
            System.out.println("12: Search By Name");
            System.out.println("13: Add Patient To Doctor");
            System.out.println("14: Exit");

            System.out.print("\nEnter option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    addANewPatient(scanner, system);
                    break;
                case 2:
                    addANewDoctor(scanner, system);
                    break;
                case 3:
                    addNewMedicationToPharmacy(scanner, system);
                    break;
                case 4:
                    printPharmacyReport(system);
                    break;
                case 5:
                    checkExpiredMeds(system);
                    break;
                case 6:
                    processANewScript(scanner, system);
                    break;
                case 7:
                    printScriptsForSpecificDoctor(scanner, system);
                    break;
                case 8:
                    restockPharmacyDrugs(scanner, system);
                    break;
                case 9:
                    printAllScriptsForPatientByName(scanner, system);
                    break;
                case 10:
                    editRecord(scanner, system);
                    break;
                case 11:
                    deleteRecord(scanner, system);
                    break;
                case 12:
                    searchByName(scanner, system);
                    break;
                case 13:
                    addPatientToDoctor(scanner, system);
                    break;
                case 14:
                    exit = true;
                    System.out.println("Exiting The System, Good Bye!");
                    break;
                default:
                    System.out.println("Invalid option, Please try again.");
            }
        }
    }

    //  Case 1 – Add a new patient

    private static void addANewPatient(Scanner scanner, MedicationTracking system) {
        System.out.print("Enter the patients name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter the patients age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the patients phone number: ");
        String phone = scanner.nextLine();

        Patient p = system.addPatient(name, age, phone);
        System.out.println("The patient has been added: " + p);
    }

    //  Case 2 – Add a new doctor

    private static void addANewDoctor(Scanner scanner, MedicationTracking system) {
        System.out.print("Enter doctor name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter specialization: ");
        String spec = scanner.nextLine();

        Doctor d = system.addDoctor(name, age, phone, spec);
        System.out.println("Doctor added: " + d);
    }

    //  Case 3 – Add a new medication

    private static void addNewMedicationToPharmacy(Scanner scanner, MedicationTracking system) {
        System.out.print("Enter the medication name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter the dosage (example. 500mg): ");
        String dose = scanner.nextLine();
        System.out.print("Enter the quantity that is in stock: ");
        int qty = scanner.nextInt();
        System.out.print("Enter the year of expiry: ");
        int year = scanner.nextInt();
        System.out.print("Enter the month of expiry (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter the day of expiry: ");
        int day = scanner.nextInt();

        LocalDate expiry = LocalDate.of(year, month, day);
        Medication m = system.addMedication(name, dose, qty, expiry);
        System.out.println("Medication added: " + m);
    }

    //  Case 4 – Print full system report

    private static void printPharmacyReport(MedicationTracking system) {
        system.printSystemReport();
    }

    //  Case 5 – Check for expired medications

    private static void checkExpiredMeds(MedicationTracking system) {
        system.checkExpiredMedications();
    }

    //  Case 6 – Process a new prescription

    private static void processANewScript(Scanner scanner, MedicationTracking system) {
        System.out.println("\n-- All Available Doctors --");
        for (Doctor d : system.getDoctors()) System.out.println("  " + d);

        System.out.print("Enter the Doctors ID: ");
        int doctorId = scanner.nextInt();

        System.out.println("\n-- All of the Available Patients --");
        for (Patient p : system.getPatients()) System.out.println("  " + p);

        System.out.print("Enter the Patients ID: ");
        int patientId = scanner.nextInt();

        System.out.println("\n-- All of the Available Medications --");
        for (Medication m : system.getMedications()) System.out.println("  " + m);

        System.out.print("Enter the Medication ID: ");
        int medicationId = scanner.nextInt();

        Prescription rx = system.processPrescription(doctorId, patientId, medicationId, LocalDate.now());
        if (rx == null) {
            System.out.println("Failed: invalid doctor, patient, or medication ID.");
        } else {
            System.out.println("Prescription has been created: " + rx);
        }
    }

    //  Case 7 – Print prescriptions for a specific doctor

    private static void printScriptsForSpecificDoctor(Scanner scanner, MedicationTracking system) {
        System.out.println("\n-- Doctors --");
        for (Doctor d : system.getDoctors()) System.out.println("  " + d);
        System.out.print("Enter the Doctors ID: ");
        int id = scanner.nextInt();
        system.printPrescriptionsForDoctor(id);
    }

    //  Case 8 – Restock drugs

    private static void restockPharmacyDrugs(Scanner scanner, MedicationTracking system) {
        System.out.println("\n-- Medications --");
        for (Medication m : system.getMedications()) System.out.println("  " + m);
        System.out.print("Enter the Medications ID to restock: ");
        int id = scanner.nextInt();
        System.out.print("Enter the amount to add (0 = random): ");
        int amount = scanner.nextInt();
        system.restockMedication(id, amount);
    }

    //  Case 9 – Print all past-year prescriptions for a patient


    private static void printAllScriptsForPatientByName(Scanner scanner, MedicationTracking system) {
        System.out.print("Enter the patients name to search: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        system.printPrescriptionsForPatientByName(name);
    }

    // ============================================================
    //  Case 10 – Edit a record
    // ============================================================

    private static void editRecord(Scanner scanner, MedicationTracking system) {
        System.out.println("Edit: (1) Patient  (2) Doctor  (3) Medication");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\n-- Patients --");
            for (Patient p : system.getPatients()) System.out.println("  " + p);
            System.out.print("Enter the Patients ID to edit: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("New name (press Enter to skip): ");
            String name = scanner.nextLine();
            System.out.print("New age (0 to skip): ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("New phone (press Enter to skip): ");
            String phone = scanner.nextLine();
            boolean ok = system.editPatient(id, name.isBlank() ? null : name,
                                             age, phone.isBlank() ? null : phone);
            System.out.println(ok ? "Patient is updated." : "Patient is not found.");

        } else if (choice == 2) {
            System.out.println("\n-- Doctors --");
            for (Doctor d : system.getDoctors()) System.out.println("  " + d);
            System.out.print("Enter the Doctors ID to edit: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("New name (press Enter to skip): ");
            String name = scanner.nextLine();
            System.out.print("New age (0 to skip): ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("New phone (press Enter to skip): ");
            String phone = scanner.nextLine();
            System.out.print("New specialization (press Enter to skip): ");
            String spec = scanner.nextLine();
            boolean ok = system.editDoctor(id, name.isBlank() ? null : name, age,
                                            phone.isBlank() ? null : phone,
                                            spec.isBlank() ? null : spec);
            System.out.println(ok ? "The Doctor has been updated." : "The Doctor is not found.");

        } else if (choice == 3) {
            System.out.println("\n-- Medications --");
            for (Medication m : system.getMedications()) System.out.println("  " + m);
            System.out.print("Enter Medication ID to edit: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("New name (press Enter to skip): ");
            String name = scanner.nextLine();
            System.out.print("New dose (press Enter to skip): ");
            String dose = scanner.nextLine();
            System.out.print("New quantity (-1 to skip): ");
            int qty = scanner.nextInt();
            System.out.println("New expiry date? (1=Yes, 0=No)");
            int changeExpiry = scanner.nextInt();
            LocalDate expiry = null;
            if (changeExpiry == 1) {
                System.out.print("Year: "); int yr = scanner.nextInt();
                System.out.print("Month: "); int mo = scanner.nextInt();
                System.out.print("Day: "); int dy = scanner.nextInt();
                expiry = LocalDate.of(yr, mo, dy);
            }
            boolean ok = system.editMedication(id, name.isBlank() ? null : name,
                                                dose.isBlank() ? null : dose, qty, expiry);
            System.out.println(ok ? "Medication updated." : "The Medication is not able to be found.");

        } else {
            System.out.println("Invalid choice.");
        }
    }

    //  Case 11 – Delete a record

    private static void deleteRecord(Scanner scanner, MedicationTracking system) {
        System.out.println("Delete: (1) Patient  (2) Doctor  (3) Medication");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("\n-- Patients --");
            for (Patient p : system.getPatients()) System.out.println("  " + p);
            System.out.print("Enter the Patients ID to delete: ");
            int id = scanner.nextInt();
            System.out.println(system.deletePatient(id) ? "The Patient has been deleted." : "This Patient is not able to be found.");

        } else if (choice == 2) {
            System.out.println("\n-- Doctors --");
            for (Doctor d : system.getDoctors()) System.out.println("  " + d);
            System.out.print("Enter the Doctors ID to delete: ");
            int id = scanner.nextInt();
            System.out.println(system.deleteDoctor(id) ? "The Doctor has been deleted." : "This Doctor is not able to be found.");

        } else if (choice == 3) {
            System.out.println("\n-- Medications --");
            for (Medication m : system.getMedications()) System.out.println("  " + m);
            System.out.print("Enter Medication ID to delete: ");
            int id = scanner.nextInt();
            System.out.println(system.deleteMedication(id) ? "The Medication has been deleted." : "The Medication not able to be found.");

        } else {
            System.out.println("Invalid choice.");
        }
    }

    //  Case 12 – Search by name

    private static void searchByName(Scanner scanner, MedicationTracking system) {
        System.out.println("Search: (1) Patient  (2) Doctor  (3) Medication");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        if (choice == 1) {
            List<Patient> results = system.searchPatientsByName(name);
            if (results.isEmpty()) { System.out.println("No patients found."); }
            else results.forEach(p -> System.out.println("  " + p));

        } else if (choice == 2) {
            List<Doctor> results = system.searchDoctorsByName(name);
            if (results.isEmpty()) { System.out.println("No doctors found."); }
            else results.forEach(d -> System.out.println("  " + d));

        } else if (choice == 3) {
            List<Medication> results = system.searchMedicationsByName(name);
            if (results.isEmpty()) { System.out.println("No medications found."); }
            else results.forEach(m -> System.out.println("  " + m));

        } else {
            System.out.println("Invalid choice.");
        }
    }

    //  Case 13 – Assign a patient to a doctor

    private static void addPatientToDoctor(Scanner scanner, MedicationTracking system) {
        System.out.println("\n-- Patients --");
        for (Patient p : system.getPatients()) System.out.println("  " + p);
        System.out.print("Enter the Patients ID: ");
        int patientId = scanner.nextInt();

        System.out.println("\n-- Doctors --");
        for (Doctor d : system.getDoctors()) System.out.println("  " + d);
        System.out.print("Enter the Doctors ID: ");
        int doctorId = scanner.nextInt();

        boolean ok = system.addPatientToDoctor(patientId, doctorId);
        System.out.println(ok ? "Patient assigned to doctor." : "Invalid patient or doctor ID.");
    }
}

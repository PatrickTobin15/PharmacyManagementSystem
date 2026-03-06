package medicationtracking;

import java.time.LocalDate;

/**
 * Medication represents a drug stocked in the pharmacy.
 * It holds identity, dosage, stock quantity, and expiry information.
 */
public class Medication {

    private int id;
    private String name;
    private String dose;
    private int quantityInStock;
    private LocalDate expiryDate;

    /**
     * Creates a Medication with all details.
     *
     * @param id              unique identifier
     * @param name            medication name
     * @param dose            dosage description (example "500mg")
     * @param quantityInStock number of units in stock
     * @param expiryDate      expiry date of the medication
     */
    public Medication(int id, String name, String dose, int quantityInStock, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
    }

    // Getters 

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDose() { return dose; }
    public int getQuantityInStock() { return quantityInStock; }
    public LocalDate getExpiryDate() { return expiryDate; }

    // Setters 

    public void setName(String name) { this.name = name; }
    public void setDose(String dose) { this.dose = dose; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    /**
     * Returns true if the medication has passed its expiry date.
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    /**
     * Adds the units to the current stock.
     *
     * @param amount number of units to add (must be positive)
     */
    public void restock(int amount) {
        if (amount > 0) {
            quantityInStock += amount;
        }
    }

    @Override
    public String toString() {
        return "MEDICATION | ID: " + id
                + " | Name: " + name
                + " | Dose: " + dose
                + " | Stock: " + quantityInStock
                + " | Expires: " + expiryDate
                + (isExpired() ? "  [EXPIRED]" : "");
    }
}

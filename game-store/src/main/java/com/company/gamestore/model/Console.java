package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console {
    @Id
    @Column(name = "console_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "You must supply a name for model field")
    private String model;
    @NotEmpty(message = "You must supply a name for manufacturer field")
    private String manufacturer;
    private String memoryAmount;
    private String processor;
    @Min(value=0, message = "Price should be greater than or equal to zero")
    private BigDecimal price;
    @Min(value=0, message = "Quantity should be greater than or equal to zero")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return getId() == console.getId() && getQuantity() == console.getQuantity() && Objects.equals(getModel(), console.getModel()) && Objects.equals(getManufacturer(), console.getManufacturer()) && Objects.equals(getMemoryAmount(), console.getMemoryAmount()) && Objects.equals(getProcessor(), console.getProcessor()) && Objects.equals(getPrice(), console.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getModel(), getManufacturer(), getMemoryAmount(), getProcessor(), getPrice(), getQuantity());
    }

    @Override
    public String toString() {
        return "Console{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memoryAmount='" + memoryAmount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

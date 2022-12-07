package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t_shirt")
public class Tshirt implements Serializable {
    @Id
    @Column(name = "t_shirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "You must input a size.")
    private String size;
    @NotEmpty(message = "You must input a color.")
    private String color;
    @NotEmpty(message = "You must input a description.")
    private String description;
    @NotNull(message = "You must input price.")
    private BigDecimal price;
    @NotNull(message = "You must input quantity.")
    @Range( min = 1, max = 1000)
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Tshirt tshirt = (Tshirt) o;
        return getId() == tshirt.getId() && getQuantity() == tshirt.getQuantity() && Objects.equals(getSize(), tshirt.getSize()) && Objects.equals(getColor(), tshirt.getColor()) && Objects.equals(getDescription(), tshirt.getDescription()) && Objects.equals(getPrice(), tshirt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }

    @Override
    public String toString() {
        return "Tshirt{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

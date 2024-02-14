package application.entities;

import application.exceptions.ConstraintException;
import application.exceptions.DuplicateEntityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static application.utilities.Util.productService;

@Getter
@Setter

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_product")
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double value;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orders = new HashSet<>();

    public Product(Long id,
                   String name,
                   String description,
                   Double value) {
        this.name = name;
        this.description = description;
        this.value = value;

    }

    public void addOrder(Order order) {
        orders.add(new OrderItem(order, this));
    }

    public void addOrder(Order order, Integer quantity) {
        orders.add(new OrderItem(order, this, quantity));
    }

    public void removeOrder(Long order) {
        orders.removeIf(itemInOrders -> Objects.equals(itemInOrders.getOrder().getId(), order));
    }


    @Override
    public void validate() {
        if (name == null || description == null) {
            throw new ConstraintException("Name and description cannot be null");
        }
        if (value < 0) {
            throw new ConstraintException("The value of the product must be positive");
        }
        if (description.length() < 10) {
            throw new ConstraintException("The product description must be a minimum of 10 characters long");
        }

        List<Product> allProducts = productService.findAll();
        if (allProducts.contains(this)){
            throw new DuplicateEntityException("There is already a product with this name");
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Product(id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", name=");
        stringBuilder.append(this.name);
        stringBuilder.append(", description=");
        stringBuilder.append(this.description);
        stringBuilder.append(", value=");
        stringBuilder.append(this.value);

        stringBuilder.append(", orders=[");

        if (!orders.isEmpty()) {
            for (OrderItem order : orders) {
                stringBuilder.append(order.getOrder().getId().toString());
                i++;
                if (i < orders.size()) {
                    stringBuilder.append(", ");
                }
            }

        }
        stringBuilder.append("])");

        return stringBuilder.toString();
    }


}

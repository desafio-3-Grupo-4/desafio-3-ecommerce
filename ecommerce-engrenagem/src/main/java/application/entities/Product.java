package application.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double value;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orders = new HashSet<>();

    public Product( Integer id,
                    String name,
                    String description,
                    Double value){
        this.name = name;
        this.description = description;
        this.value = value;

    }

    public void addOrder(Order order){
        orders.add(new OrderItem(order, this));
    }

    public void addOrder(Order order, Integer quantity){
        orders.add(new OrderItem(order, this, quantity));
    }


    @Override
    public void validate() {
        if(name == null || description == null){
            throw new IllegalArgumentException("Name and description cannot be null");
        }
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

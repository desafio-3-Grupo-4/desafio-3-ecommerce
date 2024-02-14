package application.entities;

import application.enums.OrderStatus;
import application.exceptions.ConstraintException;
import application.exceptions.ObjectNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_order")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Date date;

    //private Client client;

    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> products = new HashSet<>();

    public void addProduct(Product product) {
        if (product == null) throw new ObjectNullException("Product cannot be null");

        products.add(new OrderItem(this, product));
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null) throw new ObjectNullException("Product cannot be null");
        if (quantity <= 0) throw new ConstraintException("Quantity cannot be less than or equal to 0");

        products.add(new OrderItem(this, product, quantity));
    }

    public void removeProduct(OrderItem product) {
        if (product == null) throw new ObjectNullException("Product cannot be null");

        products.remove(product);
    }

    @Override
    public void validate() {
        if (orderStatus == null) {
            throw new ObjectNullException("OrderStatus cannot be null");
        }
    }

    public Double total() {
        return products.stream().mapToDouble(n -> n.subTotal()).sum();
    }

    @Override
    public String toString() {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order(id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", orderStatus=");
        stringBuilder.append(this.orderStatus);
        stringBuilder.append(", products=[");

        if (!products.isEmpty()) {
            for (OrderItem product : products) {
                stringBuilder.append(product.getProduct().getId().toString());
                i++;
                if (i < products.size()) {
                    stringBuilder.append(", ");
                }
            }
        }
        stringBuilder.append("], ");
        stringBuilder.append(", total: $").append(total()).append(")");

        return stringBuilder.toString();
    }


}

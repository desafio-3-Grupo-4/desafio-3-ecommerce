package application.entities;

import application.enums.OrderStatus;
import application.exceptions.ConstraintException;
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
@Table(name="tb_order")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //private Date date;

    //private Client client;

    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> products = new HashSet<>();

    public void addProduct(Product product){
        products.add(new OrderItem(this, product));
    }

    public void addProduct(Product product, Integer quantity){
        products.add(new OrderItem(this, product, quantity));
    }

    @Override
    public void validate() {
        if (orderStatus == null){
            throw new ConstraintException("OrderStatus cannot be null");
        }
    }

    @Override
    public String toString(){
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order(id=");
        stringBuilder.append(this.id);
        stringBuilder.append(", orderStatus=");
        stringBuilder.append(this.orderStatus);
        stringBuilder.append(", products=[");

        if(!products.isEmpty()) {
            for (OrderItem product : products) {
                stringBuilder.append(product.getProduct().getId().toString());
                i++;
                if (i < products.size()) {
                    stringBuilder.append(", ");
                }
            }
        }

        stringBuilder.append("])");

        return stringBuilder.toString();
    }


}

package application.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public OrderItem(Order order, Product product, Integer quantity){
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderItem(Order order, Product product){
        this.order = order;
        this.product = product;
        this.quantity = 1;

    }

    public Double subTotal(){
        return this.quantity * product.getValue();
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(product.getName());

        return stringBuilder.toString();
    }


}

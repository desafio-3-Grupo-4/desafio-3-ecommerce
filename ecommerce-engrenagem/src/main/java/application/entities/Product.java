package application.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
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

    @Override
    public void validate() {
        if(name == null || description == null){
            throw new IllegalArgumentException("Name and description cannot be null");
        }
    }


}

package tutorial.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime orderTime;

    private Integer tableNumber;

    @OneToMany
    @JoinTable
    private Collection<Product> orderedProducts = new ArrayList<>();

    private String status;

    private OrderStatus status2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Collection<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Collection<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderStatus getStatus2() {
        return status2;
    }

    public void setStatus2(OrderStatus status2) {
        this.status2 = status2;
    }
}

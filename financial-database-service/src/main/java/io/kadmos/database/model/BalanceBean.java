package io.kadmos.database.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "balances")
public class BalanceBean {
    @Id
    @Column(name = "id", nullable = false)

    private String id;

    private float amount;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(final float amount) {
        this.amount = amount;
    }
}

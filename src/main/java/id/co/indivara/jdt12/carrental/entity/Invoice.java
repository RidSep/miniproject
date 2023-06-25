package id.co.indivara.jdt12.carrental.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Table(name = "trx_cust_invoice")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Invoice extends BaseEntity{
    @Id
    @Column(name = "inv_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String invId;
    @JoinColumn(name = "rent_id",insertable = false,updatable = false)
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Rental rental;

    @Column(name = "rent_id")
    private String rentId;
    @Column(name = "return_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant returnDate;
    @Column(name = "initial_cost")
    private BigDecimal initialCost;
    @Column(name = "driver_cost")
    private BigDecimal driverCost;
    @Column(name = "total_cost")
    private BigDecimal totalCost;
}

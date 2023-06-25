package id.co.indivara.jdt12.carrental.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mst_drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver extends BaseEntity{
    @Id
    @Column(name = "driver_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String driverId;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "address")
    private String address;

    @Column(name = "cost_perhour")
    private BigDecimal costPerhour;

    @Column(name = "contact")
    private String contact;
}

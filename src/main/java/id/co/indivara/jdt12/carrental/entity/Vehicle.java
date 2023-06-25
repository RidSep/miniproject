package id.co.indivara.jdt12.carrental.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mst_vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle extends BaseEntity{
    @Id
    @Column(name = "vehicle_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String vehicleId;

    @Column(name = "vehicle_series")
    private String vehicleSeries;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "vehicle_brand")
    private String vehicleBrand;

    @Column(name = "vehicle_no")
    private String vehicleNo;

    @Column(name = "cost_perhour")
    private BigDecimal costPerhour;
}

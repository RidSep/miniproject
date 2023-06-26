package id.co.indivara.jdt12.carrental.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "trx_cust_rent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental extends BaseEntity{
    @Id
    @Column(name = "rent_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String rentId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "driver_id")
    private String driverId;

    @Column(name = "check_in")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant checkIn;

    @Column(name = "check_out")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant checkOut;

    @Column(name = "rent_status")
    private TranscationStatus rentStatus;

    @ManyToOne//Untuk data besar dan harus di panggil menggunakan get
    @JoinColumn(name = "customer_id",insertable = false,updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "driver_id",insertable = false,updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id",insertable = false,updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vehicle vehicle;

    public enum TranscationStatus{
        BOOKED,
        ON_GOING,
        FINISH
    }
}

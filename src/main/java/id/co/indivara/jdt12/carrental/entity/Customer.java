package id.co.indivara.jdt12.carrental.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity{
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String customerId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "ktp_number")
    private String ktpNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;
}

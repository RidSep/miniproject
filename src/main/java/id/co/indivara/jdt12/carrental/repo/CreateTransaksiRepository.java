package id.co.indivara.jdt12.carrental.repo;

import id.co.indivara.jdt12.carrental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreateTransaksiRepository extends JpaRepository<Rental,String> {
    @Query(value = "SELECT \n" +
            "(CASE WHEN r.rent_status <> 2 THEN 'no' ELSE 'yes' END) AS result\n" +
            "FROM trx_cust_rent r\n" +
            "WHERE\n" +
            "r.vehicle_id = :vehicleId \n" +
            "ORDER BY \n" +
            "r.LAST_UPDATE_DATE DESC \n" +
            "LIMIT 1",nativeQuery = true)
    String findVehicleAvailbility(@Param("vehicleId") String vehicleId);
    @Query(value = "SELECT \n" +
            "(CASE WHEN d.rent_status <> 2 THEN 'no' ELSE 'yes' END) AS result\n" +
            "FROM trx_cust_rent d\n" +
            "WHERE\n" +
            "d.driver_id = :driverId \n" +
            "ORDER BY \n" +
            "d.LAST_UPDATE_DATE DESC \n" +
            "LIMIT 1",nativeQuery = true)
    String findDriverAvailbility(@Param("driverId") String driverId);
    @Query(value = "SELECT * FROM trx_cust_rent t WHERE t.rent_status = :rentStatus",nativeQuery = true)
    List<Rental> findByRentStatus(@Param("rentStatus") String rentStatus);
}

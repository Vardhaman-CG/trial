import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(name = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Integer trip_id;
    
    @Column(name = "boarding_address_id", nullable = false)
    private Integer boarding_address_id;
    
    
    @Column(name = "dropping_address_id",nullable=false)
    private Integer dropping_address_id;
 
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departure_time;
 
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrival_time;

    
    @Column(name = "available_seats", nullable = false)
    private Integer available_seats;
    
    
    
    @Column(name = "fare", nullable = false, precision = 10, scale = 2)
    private BigDecimal fare;
    
    
    
    @JsonIgnore
    @Column(name = "trip_date", nullable = false)
    private LocalDateTime trip_date;
    
    
    @ManyToOne
    
    @JsonIgnore
    @JoinColumn(name = "route_id")
    private Routes route_id;
 
    @ManyToOne
    
    @JsonIgnore
    @JoinColumn(name = "bus_id", nullable = false)
    private Buses bus_id;
    
    @ManyToOne
    
    @JsonIgnore
    @JoinColumn(name = "driver1_driver_id",nullable=false)
    private Drivers driver1_driver_id;
    
    @ManyToOne
    
    @JsonIgnore
    @JoinColumn(name = "driver2_driver_id",nullable=false)
    private Drivers driver2_driver_id;
    

    
    
    
    
}
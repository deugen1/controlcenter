package host.remote.controlcenter.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hostname;
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "operating_system_id")
    private OperatingSystem operatingSystem;

    private String operatingSystemDetails;
    private AvailabilityState state;
    private LocalDateTime lastUpdated;
}

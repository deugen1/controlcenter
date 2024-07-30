package host.remote.controlcenter.repository;

import host.remote.controlcenter.BaseTestConfig;
import host.remote.controlcenter.model.OperatingSystem;
import host.remote.controlcenter.model.OperatingSystemType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OperatingSystemRepositoryTest extends BaseTestConfig {

    private final OperatingSystemRepository operatingSystemRepository;

    public OperatingSystemRepositoryTest(OperatingSystemRepository operatingSystemRepository) {
        this.operatingSystemRepository = operatingSystemRepository;
    }

    @Test
    public void testSaveOperatingSystem() {
        OperatingSystem operatingSystem = new OperatingSystem(null, OperatingSystemType.LINUX);
        OperatingSystem savedOperatingSystem = operatingSystemRepository.save(operatingSystem);

        assertThat(savedOperatingSystem.getId()).isNotNull();
        assertThat(savedOperatingSystem.getName()).isEqualTo(OperatingSystemType.LINUX);
    }

    @Test
    public void testFindOperatingSystemById() {
        OperatingSystem operatingSystem = new OperatingSystem(null, OperatingSystemType.LINUX);
        OperatingSystem savedOperatingSystem = operatingSystemRepository.save(operatingSystem);

        Optional<OperatingSystem> foundOperatingSystem = operatingSystemRepository.findById(savedOperatingSystem.getId());
        assertThat(foundOperatingSystem).isPresent();
        assertThat(foundOperatingSystem.get().getName()).isEqualTo(OperatingSystemType.LINUX);
    }

    @Test
    public void testUpdateOperatingSystem() {
        OperatingSystem operatingSystem = new OperatingSystem(null, OperatingSystemType.LINUX);
        OperatingSystem savedOperatingSystem = operatingSystemRepository.save(operatingSystem);

        savedOperatingSystem.setName(OperatingSystemType.WINDOWS);
        OperatingSystem updatedOperatingSystem = operatingSystemRepository.save(savedOperatingSystem);

        assertThat(updatedOperatingSystem.getName()).isEqualTo(OperatingSystemType.WINDOWS);
    }

    @Test
    public void testDeleteOperatingSystem() {
        OperatingSystem operatingSystem = new OperatingSystem(null, OperatingSystemType.LINUX);
        OperatingSystem savedOperatingSystem = operatingSystemRepository.save(operatingSystem);

        operatingSystemRepository.deleteById(savedOperatingSystem.getId());
        Optional<OperatingSystem> foundOperatingSystem = operatingSystemRepository.findById(savedOperatingSystem.getId());

        assertThat(foundOperatingSystem).isNotPresent();
    }
}
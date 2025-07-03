import org.junit.jupiter.api.Test;  
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.project.repository.UserRepository;
import org.project.service.RegistrationService;

@SpringBootTest
public class RegistraionsServiceTest {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testRegister() {
        // TODO: Implement test
    }
}
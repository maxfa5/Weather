import org.junit.jupiter.api.Test;  
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.project.repository.UserRepository;
import org.project.service.RegistrationService;
import org.project.exceptions.ValidationException;
import org.project.exceptions.UserAlreadyExistsException;
import org.project.model.UserModel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    private RegistrationService registrationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registrationService = new RegistrationService(userRepository);
    }

    @Test
    public void testRegisterValidInput() {
        // Настраиваем мок - пользователь не существует
        when(userRepository.findByLogin("testuser")).thenReturn(Optional.empty());
        
        // Тест успешной регистрации
        assertDoesNotThrow(() -> {
            registrationService.register("testuser", "password123");
        });
        
        // Проверяем, что метод save был вызван
        verify(userRepository, times(1)).save(any(UserModel.class));
    }
    
    @Test
    public void testRegisterValidationFailureShortPassword() {
        // Тест на короткий пароль (менее 6 символов)
        assertThrows(ValidationException.class, () -> {
            registrationService.register("user", "123");
        });
    }
    
    @Test
    public void testRegisterValidationFailureEmptyFields() {
        // Тест на пустые поля
        assertThrows(ValidationException.class, () -> {
            registrationService.register("", "password123");
        });
        
        assertThrows(ValidationException.class, () -> {
            registrationService.register("user", "");
        });
    }
    
    @Test
    public void testRegisterDuplicateUser() {
        // Настраиваем мок - пользователь уже существует
        UserModel existingUser = new UserModel();
        existingUser.setLogin("duplicate");
        when(userRepository.findByLogin("duplicate")).thenReturn(Optional.of(existingUser));
        
        // Пытаемся создать пользователя с тем же логином
        assertThrows(UserAlreadyExistsException.class, () -> {
            registrationService.register("duplicate", "password456");
        });
    }
}
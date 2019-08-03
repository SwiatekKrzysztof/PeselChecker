package peselChecker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pesel {
    long peselNumber;
    LocalDate birthDate;
    int ordinalNumber;
    char sex;
    int checkNumber;

}

package pe.com.dashboard.dashboard.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDTO {
    private Integer personId;
    private String name;
    private String lastname;
    private String phone;
    private Integer age;
    private String dni;
    private String mail;
    private String address;
    private Integer state;
}

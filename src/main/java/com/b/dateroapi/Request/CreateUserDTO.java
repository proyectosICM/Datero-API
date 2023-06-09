package com.b.dateroapi.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotBlank
    private String nom_tra;
    private String ape_tra;
    private String dni_tra;
    private Boolean est_tra;
    @NotBlank
    private String username;
    @NotBlank
    private String pass_tra;
    private Set<String> roles;
}

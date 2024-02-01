package com.icm.DateroAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Represents a district within the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "districts")
public class DistrictsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The name of the district.
     */
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    /**
     * Indicates whether the district is currently active or inactive.
     */
    private Boolean status;
}

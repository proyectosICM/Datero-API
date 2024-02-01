package com.icm.DateroAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Represents a company within the system.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "companies")
public class CompaniesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * The name of the company.
     */
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    /**
     * Indicates whether the company is currently active or inactive.
     * This is used to determine when and how to display it.
     */
    private Boolean status;

    /**
     * Date and time when this company was created.
     */
    @Column(name = "createdAt", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime createdAt = ZonedDateTime.now(ZoneId.of("America/Lima"));

    /**
     * Date and time when this company was last updated.
     */
    @Column(name = "updatedAt")
    @UpdateTimestamp
    private ZonedDateTime updatedAt = ZonedDateTime.now(ZoneId.of("America/Lima"));
}

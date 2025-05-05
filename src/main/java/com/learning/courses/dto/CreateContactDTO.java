package com.learning.courses.dto;

import com.learning.courses.model.enums.ContactType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Valid
public class CreateContactDTO implements Serializable {

    @NotBlank
    @Schema(example = "EMAIL")
    private ContactType contactType;

    @NotBlank
    @Schema(example = "jon.doe@example.com")
    private String contact;

    @Schema(example = "test")
    private String additional_information;



}

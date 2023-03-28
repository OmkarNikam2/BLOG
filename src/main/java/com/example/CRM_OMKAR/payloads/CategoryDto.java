package com.example.CRM_OMKAR.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min = 4,message = "Min Size of title is 4")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10,message = "Min Size of title is 10")
    private String categoryDescription;

}

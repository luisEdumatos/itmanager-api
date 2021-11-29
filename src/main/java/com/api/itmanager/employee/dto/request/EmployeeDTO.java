package com.api.itmanager.employee.dto.request;

import com.api.itmanager.client.dto.request.ClientDTO;
import com.api.itmanager.client.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotNull
    private ClientDTO clientDTO;

    @NotEmpty
    @Size(min = 5, max = 50)
    private String name;

    @NotEmpty
    @Size(min = 10, max = 12)
    private String admissionDate;

    @Size(min = 10, max = 12)
    private String integrationDate;

    @Size(min = 10, max = 12)
    private String resignationDate;

    @Size(min = 10, max = 12)
    private String mainPhoneNumber;
}

package com.api.itmanager.client.entity;

import com.api.itmanager.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String address;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.REMOVE
    )
    private List<Employee> employees = new ArrayList<>();
}

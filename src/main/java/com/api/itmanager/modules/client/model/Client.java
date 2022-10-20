package com.api.itmanager.modules.client.model;

import com.api.itmanager.modules.client.dto.ClientRequest;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    @Size(min = 14, max = 14)
    private String cnpj;

    @Column(nullable = false)
    private String address;

    public static Client of (ClientRequest request) {
        var client = new Client();
        BeanUtils.copyProperties(request, client);
        return client;
    }
}



package com.api.itmanager.modules.device.model;

import com.api.itmanager.modules.client.dto.ClientResponse;
import com.api.itmanager.modules.client.model.Client;
import com.api.itmanager.modules.device.dto.DeviceRequest;
import com.api.itmanager.modules.employee_device.model.EmployeeDevice;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype",
        discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
@Entity
@Table(name = "t_device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false, updatable = false)
    private Long dtype;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    @OneToMany(mappedBy = "device")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<EmployeeDevice> employees;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(name = "mac_address", length = 17)
    private String macAddress;

    @Column(name = "ip_address", length = 15)
    private String ipAddress;

    @Column
    private String description;

    public static Device of(DeviceRequest request, ClientResponse clientResponse) {
        var client = new Client();
        BeanUtils.copyProperties(clientResponse, client);

        var device = new Device();
        device.setClient(client);
        device.setBrand(request.getBrand());
        device.setCategory(request.getCategory());
        device.setDescription(request.getDescription());
        device.setIpAddress(request.getIpAddress());
        device.setMacAddress(request.getMacAddress());
        device.setModel(request.getModel());

        return device;
    }
}

package ru.rosreestr.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
@Entity
@Table(name = "WS_SERVICES")
public class WebService implements Serializable {

    private static final long serialVersionUID = 101L;

    /**
     * Идентификатор сервиса
     */
    @Id
    @GeneratedValue
    @Column(name = "SERVICE_ID")
    private Integer serviceId;

    /**
     * Наименование Сервиса
     */
    @Column(name = "NAME")
    private String name;

    @OneToMany
    @JoinColumn(name = "SERVICE_ID", referencedColumnName = "SERVICE_ID")
    private List<WebServiceConfig> params;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}

package ru.rosreestr.persistence.model;

import javax.persistence.*;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
@Entity
@Table(name = "WS_SERVICES")
public class WebService {

    /**
     * Идентификатор сервиса
     */
    @Id
    @GeneratedValue
    @Column(name = "service_id")
    Long serviceId;

    /**
     * Наименование Сервиса
     */
    @Column(name = "name")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
}

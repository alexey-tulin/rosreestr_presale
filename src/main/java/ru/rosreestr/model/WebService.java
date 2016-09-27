package ru.rosreestr.model;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
//@Entity
//@Table(name = "WS_SERVICES")
public class WebService {

    /**
     * Идентификатор сервиса
     */
    Long serviceId;

    /**
     * Наименование Сервиса
     */
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

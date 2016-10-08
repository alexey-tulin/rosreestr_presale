package ru.rosreestr.persistence.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
@Entity
@Table(name = "WS_SERVICES")

//void log(@P(value = ) java.sql.Timestamp sdTime, java.sql.Timestamp fdTime, Integer dbId, Integer serviceId, Integer logType, Integer logLevel, Integer step, String text, String textError, String v_message_id);

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "qwer", procedureName = "LOG",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sdTime", type = java.sql.Timestamp.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "fdTime", type = java.sql.Timestamp.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "dbId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "serviceId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "logType", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "logLevel", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "step", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "text", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "textError", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "messageId", type = String.class)
                }
        )
        ,//serviceId, String message, Integer outbound
        @NamedStoredProcedureQuery(name = "LOG_XML", procedureName = "ws_logger.LOG_XML",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "serviceId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "message", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "outbound", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1", type = Integer.class)
                }
        )
})
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

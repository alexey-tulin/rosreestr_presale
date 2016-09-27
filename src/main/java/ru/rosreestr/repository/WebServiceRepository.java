package ru.rosreestr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import ru.rosreestr.model.WebService;

import java.util.Date;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public interface WebServiceRepository extends JpaRepository<WebService, Long> {

    @Procedure("LOG")
    Long log(Date sdTime, Date fdTime, Long dbId, Long serviceId, int logType, int logLevel, int step, String text, String textError);

    @Procedure("LOG")
    void log(Date sdTime, Date fdTime, Long dbId, Long serviceId, int logType, int logLevel, int step, String text, String textError, String messageId);

    /**
     *
     * @param serviceId
     * @param message
     * @param outbound if outbound = 1 then insert into ws_xml_out

     * @return
     */
    @Procedure("LOG_XML")
    Long logXml(Long serviceId, String message, int outbound);

}

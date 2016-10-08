package ru.rosreestr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import ru.rosreestr.persistence.model.WebService;

import java.util.Date;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public interface WebServiceRepository extends JpaRepository<WebService, Long> {

    /**
     * Логирование ошибкок в БД
     *
     * @param sdTime
     * @param fdTime
     * @param dbId Ссылкана District_servers.id_d bили 0 - если не известно
     * @param serviceId Идентификатор сервиса 1-сервис уведомления
     * @param logType Тип лога 1-JAVA, 2-PLS
     * @param logLevel 1 - Информационное, 2 - Предупреждения, 3 - Ошибка, 4 - Критичекская ошибка
     * @param step Шаг операции лога
     * @param text Текстовая информация лога
     * @param textError Стек ошибки
     * @return
     */
    @Procedure(name = "qwer")
   // void log(java.sql.Timestamp sdTime, java.sql.Timestamp fdTime, Integer dbId, Integer serviceId, Integer logType, Integer logLevel, Integer step, String text, String textError, String v_message_id);
    void log(@Param("sdTime") java.sql.Timestamp sdTime, @Param("fdTime") java.sql.Timestamp fdTime, @Param("dbId") Integer dbId, @Param("serviceId") Integer serviceId, @Param("logType") Integer logType, @Param("logLevel") Integer logLevel, @Param("step") Integer step, @Param("text") String text, @Param("textError") String textError, @Param("messageId") String messageId);


    /**
     * Логирование ошибкок в БД
     *
     * @param sdTime
     * @param fdTime
     * @param dbId Ссылкана District_servers.id_d bили 0 - если не известно
     * @param serviceId Идентификатор сервиса 1-сервис уведомления
     * @param logType Тип лога 1-JAVA, 2-PLS
     * @param logLevel 1 - Информационное, 2 - Предупреждения, 3 - Ошибка, 4 - Критичекская ошибка
     * @param step Шаг операциилога
     * @param text Текстовая информация лога
     * @param textError Стек ошибки
     * @param messageId Иденификатор сообщения
     */
//    @Procedure(procedureName = "LOG")
//    void log(Date sdTime, Date fdTime, Long dbId, Long serviceId, int logType, int logLevel, int step, String text, String textError, String messageId);

    /**
     * Логирование входящих и исходящих SOAP сообщений
     *
     * @param serviceId Идентификатор сервиса
     * @param message Сообщение
     * @param outbound if outbound = 1 then insert into ws_xml_out

     * @return row id
     */
    @Procedure(name = "LOG_XML")
    Integer logXml(@Param("serviceId") Integer serviceId, @Param("message") String message, @Param("outbound") Integer outbound);

}


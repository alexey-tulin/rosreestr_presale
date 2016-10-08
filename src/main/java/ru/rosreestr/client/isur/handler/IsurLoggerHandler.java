package ru.rosreestr.client.isur.handler;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rosreestr.persistence.model.LogLevel;
import ru.rosreestr.persistence.model.LogType;
import ru.rosreestr.persistence.repository.WebServiceRepository;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
@Component
public class IsurLoggerHandler implements SOAPHandler<SOAPMessageContext> {
    private static final Logger LOG = Logger.getLogger(IsurLoggerHandler.class);

    @Autowired
    private WebServiceRepository wsRepository;

    public boolean handleMessage(SOAPMessageContext context) {

        int logStep = 0;
        java.sql.Timestamp logDate = new java.sql.Timestamp(new Date().getTime());
        int serviceId = 1; //todo

        try {

            SOAPMessage msg = context.getMessage();
            Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

            String descriptionStep0 =  outboundProperty.booleanValue() ? " SOAP REQUEST: " : " SOAP RESPONSE: ";
            LOG.info(descriptionStep0);
            wsRepository.logXml(1, descriptionStep0,0);

            wsRepository.log(logDate, logDate, serviceId, 0, LogType.LOG_JAVA, LogLevel.LOG_LEVEL_INFO, logStep++, descriptionStep0, "","");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            msg.writeTo(baos);
            LOG.info(baos);
            //logMessageToDB(baos);
            wsRepository.log(logDate, logDate, serviceId, 0, LogType.LOG_JAVA, LogLevel.LOG_LEVEL_INFO, logStep++, baos.toString(), "","");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            wsRepository.log(logDate, logDate, serviceId, 0, LogType.LOG_JAVA, LogLevel.LOG_LEVEL_INFO, logStep++, e.getMessage(), ExceptionUtils.getStackTrace(e),"");
        }
        return true;
    }

    private void logMessageToDB(ByteArrayOutputStream baos) {
        //TODO LOG Message to database
    }

    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    public void close(MessageContext context) {
        // --
    }

    public Set<QName> getHeaders() {
        // --
        return null;
    }

}

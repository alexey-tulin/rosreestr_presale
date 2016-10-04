package ru.rosreestr.client.isur.handler;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Set;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public class IsurLoggerHandler implements SOAPHandler<SOAPMessageContext> {
    private static final Logger LOG = Logger.getLogger(IsurLoggerHandler.class);

    public boolean handleMessage(SOAPMessageContext context) {
        try {

            SOAPMessage msg = context.getMessage();
            Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
            LOG.info(outboundProperty.booleanValue() ? " SOAP REQUEST: " : " SOAP RESPONSE: ");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            msg.writeTo(baos);
            LOG.info(baos);
            logMessageToDB(baos);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
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

package ru.rosreestr.client.isur.handler;

import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public class IsurLoggerHandler implements SOAPHandler<SOAPMessageContext> {
    private static final Logger LOG = Logger.getLogger(IsurLoggerHandler.class);

    public boolean handleMessage(SOAPMessageContext context) {
        SOAPMessage msg = context.getMessage();
        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        LOG.info(outboundProperty.booleanValue() ? " SOAP REQUEST: " : " SOAP RESPONSE: ");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            msg.writeTo(baos);
        } catch (SOAPException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info(baos);
        logMessageToDB(baos);
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

package ru.rosreestr.client.isur.handler;

import org.apache.log4j.Logger;
import ru.rosreestr.utils.SignatureUtils;

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
public class IsurSignatureHandler  implements SOAPHandler<SOAPMessageContext> {

    private static final Logger LOGGER = Logger.getLogger(IsurSignatureHandler.class);

    public Set<QName> getHeaders() {
        // todo
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        if (context != null) {
            try {
                final Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

                if (outbound != null && outbound) {
                    LOGGER.info(SOAPMessage.class.getSimpleName());
                    SOAPMessage message = context.getMessage();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try {
                        message.writeTo(baos);
                        LOGGER.debug(baos.toString("UTF-8"));
                    } catch (Exception e) {
                        LOGGER.error("SOAPMessage err", e);
                    }

                    SignatureUtils.addSecurityBlock(message, "RaUser-53adea6d-4913-4e3e-af78-410c82d013e3", "1234567890".toCharArray()); // TODO

                    baos = new ByteArrayOutputStream();
                    try {
                        message.writeTo(baos);
                        LOGGER.debug(baos.toString("UTF-8"));
                    } catch (Exception e) {
                        LOGGER.error("SOAPMessage err", e);
                    }

                    // todo временно
                    SignatureUtils.verify(message.getSOAPPart());
                }
                return true;
            }  catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return false;
    }

    public boolean handleFault(SOAPMessageContext context) {
        // todo
        return false;
    }

    public void close(MessageContext context) {
        // todo

    }
}

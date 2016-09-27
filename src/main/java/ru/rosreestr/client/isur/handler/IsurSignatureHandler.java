package ru.rosreestr.client.isur.handler;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public class IsurSignatureHandler  implements SOAPHandler<SOAPMessageContext> {
    public Set<QName> getHeaders() {
        // todo
        return null;
    }

    public boolean handleMessage(SOAPMessageContext context) {
        // todo
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

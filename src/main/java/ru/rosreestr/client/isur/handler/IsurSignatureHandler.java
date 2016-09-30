package ru.rosreestr.client.isur.handler;

import org.apache.log4j.Logger;
import org.springframework.util.Base64Utils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.voskhod.crypto.DigitalSignatureFactory;
import ru.voskhod.crypto.DigitalSignatureProcessor;
import ru.voskhod.crypto.KeyStoreWrapper;
import ru.voskhod.crypto.XMLTransformHelper;

import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;
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
            Marshaller m = null;
            try { // TODO заставить сие работать
                LOGGER.info("handleMessage");
                final Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
                if (outbound != null && outbound) {
                    LOGGER.info(SOAPMessage.class.getSimpleName());
                    SOAPMessage message = context.getMessage();
                    message.getSOAPPart().getEnvelope().addNamespaceDeclaration("def", "http://asguf.mos.ru/rkis_gu/coordinate/v5/");
                    message.getSOAPPart().getEnvelope().addNamespaceDeclaration("u","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try {
                        message.writeTo(baos);
                    } catch (SOAPException e) {
                        LOGGER.error(e.getMessage(), e);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                    LOGGER.info(baos.toString("UTF-8"));

                    //
                    SOAPBody soapBody = context.getMessage().getSOAPBody();
                    DOMSource source = new DOMSource(soapBody);
                    StringWriter stringResult = new StringWriter();
                    TransformerFactory.newInstance().newTransformer().transform(source, new StreamResult(stringResult));
                    String textBody = new String(stringResult.toString().getBytes("UTF-8"));

                    // JAXBElement<SOAPBody> bodyJAXBElement =  new JAXBElement(new QName(body.getElementQName().getLocalPart()), SOAPBody.class, body);
                    LOGGER.info(SOAPBody.class.getSimpleName());
                    LOGGER.info(textBody);

                    Iterator iterator = soapBody.getChildElements();
                    SOAPBodyElement bodyElement = null;
                    if (iterator.hasNext()) {
                        bodyElement = (SOAPBodyElement)iterator.next();
                    }


                    DigitalSignatureFactory.init("JCP");
                    DigitalSignatureProcessor dsp = DigitalSignatureFactory.getDigitalSignatureProcessor();
                    KeyStoreWrapper ksw = DigitalSignatureFactory.getKeyStoreWrapper();
                   // Element e = XMLTransformHelper.buildDocumentFromString(textBody).getDocumentElement();
                    bodyElement.addAttribute(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd","Id"),"_1");

                    SOAPHeader header =  context.getMessage().getSOAPHeader();
                    NodeList nodeList = header.getElementsByTagNameNS("*", "ServiceHeader");// message.getSOAPPart().getDocumentElement().getElementsByTagNameNS("*", "ServiceHeader");

                    Element serviceHeader = null;
                    if (nodeList != null && nodeList.getLength() > 0) {
                        serviceHeader = (Element) nodeList.item(0);
                    }

                    X509Certificate x509Certificate = ksw.getX509Certificate("RaUser-53adea6d-4913-4e3e-af78-410c82d013e3");
                    PrivateKey privateKey = ksw.getPrivateKey("RaUser-53adea6d-4913-4e3e-af78-410c82d013e3", "1234567890".toCharArray());
                    Element sign = dsp.signXMLDSigInfoDetached(Arrays.asList(bodyElement, serviceHeader), privateKey, x509Certificate);

                    final String XSD_WSSE = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

                    // create security
                    QName security = new QName(XSD_WSSE, "Security", "sec");

                    SOAPHeaderElement addHeaderElement = header.addHeaderElement(security);
                    String  actorAttributeName = header.getPrefix() + ":actor";
                    //addHeaderElement.setActor(header.getBaseURI());
                    addHeaderElement.setAttribute(actorAttributeName, "RSMEVAUTH");

                    // create BinarySecurityToken
                    final SOAPElement authElement = addHeaderElement.addChildElement("BinarySecurityToken", "wsse", XSD_WSSE);
                    authElement.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
                    authElement.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
                    authElement.setAttribute("u:Id", "SecurityToken");
                    authElement.addTextNode(Base64Utils.encodeToString(x509Certificate.getEncoded()));

                    // create SecurityTokenReference
                    Element keyInfoElement = sign.getOwnerDocument().createElement("KeyInfo");
                    Element tokenReferenceElement = sign.getOwnerDocument().createElementNS(XSD_WSSE, "SecurityTokenReference");
                    Element referenceElement = sign.getOwnerDocument().createElementNS(XSD_WSSE, "Reference");
                    referenceElement.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
                    referenceElement.setAttribute("URI", "#SecurityToken");

                    tokenReferenceElement.appendChild(referenceElement);
                    keyInfoElement.appendChild(tokenReferenceElement);
                    sign.appendChild(keyInfoElement);
                    addHeaderElement.appendChild(sign);


                    LOGGER.info("reference1");
                    LOGGER.info(XMLTransformHelper.elementToString(sign));


                    baos = new ByteArrayOutputStream();
                    try {
                        message.writeTo(baos);
                    } catch (SOAPException e) {
                        LOGGER.error(e.getMessage(), e);
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                    LOGGER.info(baos.toString("UTF-8"));
                }

            }  catch (Exception ex) {
                LOGGER.error(ex);
                ex.printStackTrace();
            }
        } else {
            //LOGGER.error(writer.toString());
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

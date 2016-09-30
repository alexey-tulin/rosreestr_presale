package ru.rosreestr.utils;

import org.apache.log4j.Logger;
import org.springframework.util.Base64Utils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.voskhod.crypto.DigitalSignatureFactory;
import ru.voskhod.crypto.DigitalSignatureProcessor;
import ru.voskhod.crypto.KeyStoreWrapper;
import ru.voskhod.crypto.exceptions.SignatureProcessingException;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by KatrinaBosh on 30.09.2016.
 */
public class SignatureUtils {

    private static final Logger LOGGER = Logger.getLogger(SignatureUtils.class);

    public static final String XSD_WSS_SEC = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
    public static final String XSD_WSS_U = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

    public static void addSecurityBlock(SOAPMessage message, String certificateAlias, char[] password) throws SOAPException, CertificateException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, SignatureProcessingException {

        SOAPBody soapBody = message.getSOAPBody();
        SOAPHeader header =  message.getSOAPHeader();

        // elements for  sign
        Iterator iterator = soapBody.getChildElements();
        SOAPBodyElement bodyElement = null;
        if (iterator.hasNext()) {
            bodyElement = (SOAPBodyElement)iterator.next();
            bodyElement.addAttribute(new QName(XSD_WSS_U,"Id"),"_1");
        }

        NodeList nodeList = header.getElementsByTagNameNS("*", "ServiceHeader");
        Element serviceHeader = null;
        if (nodeList != null && nodeList.getLength() > 0) {
            serviceHeader = (Element) nodeList.item(0);
        }

        // init JCP
        DigitalSignatureFactory.init("JCP");
        DigitalSignatureProcessor dsp = DigitalSignatureFactory.getDigitalSignatureProcessor();
        KeyStoreWrapper ksw = DigitalSignatureFactory.getKeyStoreWrapper();

        X509Certificate x509Certificate = ksw.getX509Certificate(certificateAlias);
        PrivateKey privateKey = ksw.getPrivateKey(certificateAlias, password);

        // create security
        QName security = new QName(XSD_WSS_SEC, "Security", "sec");
        SOAPHeaderElement addHeaderElement = header.addHeaderElement(security);
        String  actorAttributeName = header.getPrefix() + ":actor";
        addHeaderElement.setAttribute(actorAttributeName, "RSMEVAUTH");

        // create BinarySecurityToken
        final SOAPElement authElement = addHeaderElement.addChildElement("BinarySecurityToken", "wsse", XSD_WSS_SEC);
        authElement.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        authElement.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        authElement.setAttribute("u:Id", "SecurityToken");
        authElement.addTextNode(Base64Utils.encodeToString(x509Certificate.getEncoded()));

        // create and sign SignedInfo
        Element sign = dsp.signXMLDSigInfoDetached(Arrays.asList(bodyElement, serviceHeader), privateKey, x509Certificate);

        // create SecurityTokenReference
        Element keyInfoElement = sign.getOwnerDocument().createElement("KeyInfo");
        Element tokenReferenceElement = sign.getOwnerDocument().createElementNS(XSD_WSS_SEC, "SecurityTokenReference");
        Element referenceElement = sign.getOwnerDocument().createElementNS(XSD_WSS_SEC, "Reference");
        referenceElement.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        referenceElement.setAttribute("URI", "#SecurityToken");

        tokenReferenceElement.appendChild(referenceElement);
        keyInfoElement.appendChild(tokenReferenceElement);
        sign.appendChild(keyInfoElement);
        addHeaderElement.appendChild(sign);
    }

}

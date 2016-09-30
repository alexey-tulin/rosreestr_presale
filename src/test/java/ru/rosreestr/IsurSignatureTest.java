package ru.rosreestr;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.sun.org.apache.xml.internal.security.transforms.Transforms;
import com.sun.xml.internal.bind.api.JAXBRIContext;
import com.sun.xml.internal.ws.developer.WSBindingProvider;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Element;
import ru.rosreestr.client.isur.IService;
import ru.rosreestr.client.isur.Service;
import ru.rosreestr.client.isur.model.*;
import ru.rosreestr.config.AppConfig;
import ru.rosreestr.utils.CommonUtils;
import ru.voskhod.crypto.DigitalSignatureFactory;
import ru.voskhod.crypto.DigitalSignatureProcessor;
import ru.voskhod.crypto.KeyStoreWrapper;
import ru.voskhod.crypto.XMLTransformHelper;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class IsurSignatureTest {

    private static final Logger LOG = Logger.getLogger(IsurSignatureTest.class);
    private static final String FROM_ORG_CODE = "2033";
    private static final String TO_ORG_CODE = "1111";
    private static final String SERVICE_NUMBER_TEMPLATE = "2033-9000085-047202-%s/%s";

    @Autowired
    public Service service;

    @Test
    public void test() {

        Headers headers = new Headers();
        headers.setFromOrgCode(FROM_ORG_CODE);
        headers.setToOrgCode(TO_ORG_CODE);
        headers.setRequestDateTime(CommonUtils.getXmlGregorianCurrentDate());
        headers.setMessageId(UUID.randomUUID().toString());
        headers.setServiceNumber(String.format(SERVICE_NUMBER_TEMPLATE, "000000", CommonUtils.getCurrentYear()));//TODO -000000 NNNNNN - порядковый номер обращения за указанной государственной услугой в организации, зарегистрировавшей обращение, в текущем году;

        CoordinateTaskData coordinateTaskData = new CoordinateTaskData();
        coordinateTaskData.setTask(new RequestTask());
        coordinateTaskData.getTask().setRequestId("320a1d7f-7e94-464c-aace-ae03af7ad248");
        coordinateTaskData.getTask().setValidityPeriod(new XMLGregorianCalendarImpl());
        coordinateTaskData.getTask().setResponsible(new Person());
        coordinateTaskData.getTask().getResponsible().setLastName("Иван");
        coordinateTaskData.getTask().getResponsible().setFirstName("Иванов");
        coordinateTaskData.getTask().getResponsible().setMiddleName("Иванович");
        coordinateTaskData.getTask().getResponsible().setJobTitle("Тестировщик");
        coordinateTaskData.getTask().getResponsible().setPhone("8-999-999-99-99");
        coordinateTaskData.getTask().getResponsible().setEmail("test@test.test");
        coordinateTaskData.getTask().setDepartment(new Department());
        coordinateTaskData.getTask().getDepartment().setName("Департамент информационных технологий города Москвы");
        coordinateTaskData.getTask().getDepartment().setInn("7710878000");
        coordinateTaskData.getTask().getDepartment().setOgrn("1107746943347");
        coordinateTaskData.getTask().getDepartment().setRegDate(new XMLGregorianCalendarImpl());
        coordinateTaskData.getTask().setServiceNumber("9990-9999999-999998-093023/16");
        coordinateTaskData.getTask().setServiceTypeCode("999998");

        IService customBindingIService = service.getCustomBindingIService();
        // add headers
        JAXBElement<ru.rosreestr.client.isur.model.Headers> serviceHeader = new ObjectFactory().createServiceHeader(headers);
        try {
            ((WSBindingProvider) customBindingIService).setOutboundHeaders(
                    com.sun.xml.internal.ws.api.message.Headers.create(JAXBRIContext.newInstance(new Class[]{ru.rosreestr.client.isur.model.Headers.class}, null, null, null, false, null),
                            serviceHeader)
            );
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
        customBindingIService.sendTask(coordinateTaskData);
    }

    public static final String DIGEST_METHOD = "http://www.w3.org/2001/04/xmldsig-more#gostr3411";
    public static final String XMLDSIG_SIGN_METHOD = "http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411";

    protected static final String XMLDSIG_DETACHED_TRANSFORM_METHOD = Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS;
    protected static final String XMLDSIG_ENVELOPED_TRANSFORM_METHOD = Transforms.TRANSFORM_ENVELOPED_SIGNATURE;
    protected static final String WSSU_NS = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";

    protected static final String EDS_ERROR_SIGNATURE_INVALID = "Ошибка проверки ЭП: Нарушена целостность ЭП";



    String string = "<s:Body u:Id=\"_1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><TaskMessage xmlns=\"http://asguf.mos.ru/rkis_gu/coordinate/v5/\"><Task><RequestId>320a1d7f-7e94-464c-aace-ae03af7ad248</RequestId><ValidityPeriod xsi:nil=\"true\"></ValidityPeriod><Responsible><LastName>Иван</LastName><FirstName>Иванов</FirstName><MiddleName>Иванович</MiddleName><JobTitle>Тестировщик</JobTitle><Phone>8-999-999-99-99</Phone><Email>test@test.test</Email></Responsible><Department><Name>Департамент информационных технологий города Москвы</Name><Inn>7710878000</Inn><Ogrn>1107746943347</Ogrn><RegDate xsi:nil=\"true\"></RegDate></Department><ServiceNumber>9990-9999999-999998-093023/16</ServiceNumber><ServiceTypeCode>999998</ServiceTypeCode></Task></TaskMessage></s:Body>";

    @Test
    public void tt () throws Exception {
 // TODO
        DigitalSignatureFactory.init("JCP");
        DigitalSignatureProcessor dsp = DigitalSignatureFactory.getDigitalSignatureProcessor();
        KeyStoreWrapper ksw = DigitalSignatureFactory.getKeyStoreWrapper();
        Element e = XMLTransformHelper.buildDocumentFromString(string).getDocumentElement();

            Element sign = dsp.signXMLDSigDetached(e, "#_2", ksw.getPrivateKey("RaUser-53adea6d-4913-4e3e-af78-410c82d013e3", "1234567890".toCharArray()), ksw.getX509Certificate("RaUser-53adea6d-4913-4e3e-af78-410c82d013e3"));
//            sign.removeAttribute("Id");
//            Element reference2 = (Element) sign.getElementsByTagNameNS("*","Reference");
//            reference2.setAttribute("URI","#_2");
//            System.out.println(XMLTransformHelper.elementToString(sign));


//        XMLSignatureFactory fac = XMLSignatureFactory.getInstance();
//        XMLObject obj = fac.newXMLObject(Collections.singletonList
//                (new DOMStructure(webElem)), "DSig.Object_1", "text/xml", null);
//
//        // create XMLSignature
//        XMLSignature sig = fac.newXMLSignature
//                (si, ki, Collections.singletonList(obj), null, null);

    }

}

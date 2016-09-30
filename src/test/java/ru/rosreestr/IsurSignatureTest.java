package ru.rosreestr;


import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
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
import java.util.Collections;
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


    @Test
    public void testSignXMLDSigInfoDetached() throws Exception {

        String body = "<s:Body u:Id=\"_1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><TaskMessage xmlns=\"http://asguf.mos.ru/rkis_gu/coordinate/v5/\"><Task><RequestId>320a1d7f-7e94-464c-aace-ae03af7ad248</RequestId><ValidityPeriod xsi:nil=\"true\"></ValidityPeriod><Responsible><LastName>Иван</LastName><FirstName>Иванов</FirstName><MiddleName>Иванович</MiddleName><JobTitle>Тестировщик</JobTitle><Phone>8-999-999-99-99</Phone><Email>test@test.test</Email></Responsible><Department><Name>Департамент информационных технологий города Москвы</Name><Inn>7710878000</Inn><Ogrn>1107746943347</Ogrn><RegDate xsi:nil=\"true\"></RegDate></Department><ServiceNumber>9990-9999999-999998-093023/16</ServiceNumber><ServiceTypeCode>999998</ServiceTypeCode></Task><Data><DocumentTypeCode>77290</DocumentTypeCode><Parameter><ServiceProperties xmlns=\"\" xmlns:ns2=\"http://asguf.mos.ru/rkis_gu/coordinate/v5/\"><data>\n" +
                "PFJlcXVlc3RHUlA+PGVEb2N1bWVudCBHVUlEPSI1MDk2MGNmOC01ZGM0LTQ2NjYtYmUzMy04ZGFhNzQ3OGQwMmUiIFZlcnNpb249IjEuMTYiLz48UmVxdWVzdD48UmVxdWlyZWREYXRhPjxSZXF1aXJlZERhdGFSZWFsdHk+PEV4dHJhY3RSZWFsdHk+PE9iamVjdHM+PE9iamVjdD48T2JqS2luZD48QnVpbGRpbmc+PElzTm9uZG9tZXN0aWM+dHJ1ZTwvSXNOb25kb21lc3RpYz48L0J1aWxkaW5nPjwvT2JqS2luZD48Q2FkYXN0cmFsTnVtYmVycz48Q2FkYXN0cmFsTnVtYmVyPjUwOjI2OjAxNzA1MDg6Njc2PC9DYWRhc3RyYWxOdW1iZXI+PC9DYWRhc3RyYWxOdW1iZXJzPjxMb2NhdGlvbj48UmVnaW9uPjc3PC9SZWdpb24+PC9Mb2NhdGlvbj48L09iamVjdD48L09iamVjdHM+PC9FeHRyYWN0UmVhbHR5PjwvUmVxdWlyZWREYXRhUmVhbHR5PjwvUmVxdWlyZWREYXRhPjxEZWNsYXJhbnQgZGVjbGFyYW50X2tpbmQ9IjM1NzAxMzAwMDAwMCIgc2lnbmF0dXJlZD0idHJ1ZSI+PEdvdmVybmFuY2U+PE5hbWU+0JrQvtC80LjRgtC10YIg0L/QviDQsNGA0YXQuNGC0LXQutGC0YPRgNC1INC4INCz0YDQsNC00L7RgdGC0YDQvtC40YLQtdC70YzRgdGC0LLRgyDQs9C+0YDQvtC00LAg0JzQvtGB0LrQstGLPC9OYW1lPjxHb3Zlcm5hbmNlX0NvZGU+MDA3MDAxMDAxMDAyPC9Hb3Zlcm5hbmNlX0NvZGU+PEUtbWFpbD5HYWxhdGVua29BU0Btb3MucnU8L0UtbWFpbD48QWdlbnQ+PEZJTz48U3VybmFtZT7DkCYjMTQ3O8OQwrDDkMK7w5DCsMORJiMxMzA7w5DCtcOQwr3DkMK6w5DCvjwvU3VybmFtZT48Rmlyc3Q+w5AmIzE0NDvDkMK7w5DCtcOQwrrDkSYjMTI5O8OQwrXDkMK5PC9GaXJzdD48UGF0cm9ueW1pYz7DkMKhw5DCtcORJiMxMjg7w5DCs8OQwrXDkMK1w5DCssOQwrjDkSYjMTM1OzwvUGF0cm9ueW1pYz48L0ZJTz48RG9jdW1lbnQ+PENvZGVfRG9jdW1lbnQ+MDA4MDAxMDAyMDAwPC9Db2RlX0RvY3VtZW50PjxTZXJpZXM+OTk5OTwvU2VyaWVzPjxOdW1iZXI+OTk5OTk5PC9OdW1iZXI+PERhdGU+MDAwMS0wMS0wMTwvRGF0ZT48SXNzdWVPcmdhbj7QntCS0JQg0JPQntCgLtCc0J7QodCa0JLQqzwvSXNzdWVPcmdhbj48L0RvY3VtZW50PjxFLW1haWw+R2FsYXRlbmtvQVNAbW9zLnJ1PC9FLW1haWw+PGFnZW50X2tpbmQ+MzU2MDA1MDAwMDAwPC9hZ2VudF9raW5kPjwvQWdlbnQ+PC9Hb3Zlcm5hbmNlPjwvRGVjbGFyYW50PjxQYXltZW50PjxGcmVlPnRydWU8L0ZyZWU+PC9QYXltZW50PjxEZWxpdmVyeT48V2ViU2VydmljZT50cnVlPC9XZWJTZXJ2aWNlPjwvRGVsaXZlcnk+PEFwcGxpZWRfRG9jdW1lbnRzPjxBcHBsaWVkX0RvY3VtZW50PjxDb2RlX0RvY3VtZW50PjU1ODEwMjEwMDAwMDwvQ29kZV9Eb2N1bWVudD48TmFtZT7Ql9Cw0L/RgNC+0YEg0L4g0L/RgNC10LTQvtGB0YLQsNCy0LvQtdC90LjQuCDRgdCy0LXQtNC10L3QuNC5LCDRgdC+0LTQtdGA0LbQsNGJ0LjRhdGB0Y8g0LIg0JXQtNC40L3QvtC8INCz0L7RgdGD0LTQsNGA0YHRgtCy0LXQvdC90L7QvCDRgNC10LXRgdGC0YDQtSDQv9GA0LDQsiDQvdCwINC90LXQtNCy0LjQttC40LzQvtC1INC40LzRg9GJ0LXRgdGC0LLQviDQuCDRgdC00LXQu9C+0Log0YEg0L3QuNC8PC9OYW1lPjxOdW1iZXI+MTg3MjU1MjUyMTwvTnVtYmVyPjxEYXRlPjIwMTYtMDktMjI8L0RhdGU+PFF1YW50aXR5PjxPcmlnaW5hbCBRdWFudGl0eT0iMSIgUXVhbnRpdHlfU2hlZXQ9IjEiLz48L1F1YW50aXR5PjwvQXBwbGllZF9Eb2N1bWVudD48L0FwcGxpZWRfRG9jdW1lbnRzPjwvUmVxdWVzdD48RklSX0luZm8+PE1vbml0b3Jpbmc+MDwvTW9uaXRvcmluZz48L0ZJUl9JbmZvPjwvUmVxdWVzdEdSUD4=\n" +
                "</data><signature>\n" +
                "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\n" +
                "</signature></ServiceProperties></Parameter><IncludeXmlView>true</IncludeXmlView><IncludeBinaryView>true</IncludeBinaryView></Data></TaskMessage></s:Body>";


        DigitalSignatureFactory.init("JCP");
        DigitalSignatureProcessor dsp = DigitalSignatureFactory.getDigitalSignatureProcessor();
        KeyStoreWrapper ksw = DigitalSignatureFactory.getKeyStoreWrapper();
        Element e = XMLTransformHelper.buildDocumentFromString(body).getDocumentElement();

        Element sign = dsp.signXMLDSigInfoDetached(Collections.singletonList(e), ksw.getPrivateKey("RaUser-53adea6d-4913-4e3e-af78-410c82d013e3", "1234567890".toCharArray()), ksw.getX509Certificate("RaUser-53adea6d-4913-4e3e-af78-410c82d013e3"));
//
        System.out.println(XMLTransformHelper.elementToString(sign));

    }

}

package ru.rosreestr.client.isur.processor;

import com.sun.xml.internal.bind.api.JAXBRIContext;
import com.sun.xml.internal.ws.api.message.Headers;
import com.sun.xml.internal.ws.developer.WSBindingProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.rosreestr.client.isur.IService;
import ru.rosreestr.client.isur.Service;
import ru.rosreestr.client.isur.model.*;
import ru.rosreestr.config.AppProperties;
import ru.rosreestr.utils.CommonUtils;
import ru.rosreestr.utils.SignatureUtils;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.util.UUID;

/**
 * Created by Tatiana Chukina on 28.09.2016 23:26.
 * <p/>
 * TODO place the file's description here
 */
@org.springframework.stereotype.Service
public class ServiceImpl {
    private static final Logger LOG = Logger.getLogger(ServiceImpl.class);
    private static final String FROM_ORG_CODE = "2033";
    private static final String TO_ORG_CODE = "1111";
    private static final String SERVICE_NUMBER_TEMPLATE = "2033-9000085-047202-%s/%s";

    @Autowired
    public Service service;

    @Autowired
    private AppProperties properties;


    /**
     * Method for invoking {@link IService#sendTask(CoordinateTaskData)} service
     */
    public void sendTask() {
        LOG.info("start service sendTask");
        IService customBindingIService = service.getCustomBindingIService();
        String serviceNumber = String.format(SERVICE_NUMBER_TEMPLATE, getNewMessageNum(), CommonUtils.getCurrentYear());
        CoordinateTaskData coordinateTaskData = createCoordinateTaskData(serviceNumber);
        addHeader((WSBindingProvider) customBindingIService, createSendTaskHeaders(serviceNumber));
        customBindingIService.sendTask(coordinateTaskData);
        LOG.info("end service sendTask");
    }

    private CoordinateTaskData createCoordinateTaskData(String serviceNumber) {
        CoordinateTaskData coordinateTaskData = new CoordinateTaskData();
        RequestTask requestTask = createRequestTask(serviceNumber);
        DocumentsRequestData documentsRequestData = createDocumentsRequestData();

        coordinateTaskData.setTask(requestTask);
        coordinateTaskData.setData(documentsRequestData);
        return coordinateTaskData;
    }


    private RequestTask createRequestTask(String serviceNumber) {
        RequestTask requestTask = new RequestTask();
        requestTask.setRequestId(UUID.randomUUID().toString());
        requestTask.setServiceNumber(serviceNumber);
        requestTask.setServiceTypeCode("047202");

        Person responsible = new Person();
        responsible.setLastName("Столыпин");
        responsible.setFirstName("Петр");
        responsible.setMiddleName("Аркадьевич");
        responsible.setJobTitle("АСУ ЕИРЦ");
        responsible.setPhone("8-999-999-99-99");
        responsible.setEmail("test@test.test");
        requestTask.setResponsible(responsible);

        Department department = new Department();
        department.setName("Департамент информационных технологий города Москвы");
        department.setCode("707");
        department.setInn("7710878000");
        department.setOgrn("1107746943347");
        requestTask.setDepartment(department);

        return requestTask;
    }

    private DocumentsRequestData createDocumentsRequestData() {
        DocumentsRequestData documentsRequestData = new DocumentsRequestData();
        documentsRequestData.setDocumentTypeCode("77290");
        documentsRequestData.setIncludeXmlView(true);
        documentsRequestData.setIncludeBinaryView(true);
        documentsRequestData.setParameterTypeCode("77290");
        DocumentsRequestData.Parameter parameter = createParameter();
        documentsRequestData.setParameter(parameter);
        return documentsRequestData;
    }

    //TODO fill from db
    private DocumentsRequestData.Parameter createParameter() {
        DocumentsRequestData.Parameter parameter = new DocumentsRequestData.Parameter();
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setRegion("77");
        serviceProperties.setCadastralnumber(getCadastralNumber());
        serviceProperties.setTypeobject("002001002000");
        serviceProperties.setTyperoom("IsNondomestic");

        byte[] base64Props = CommonUtils.encodeObjectToBase64(serviceProperties);
        ru.rosreestr.client.isur.model.base64.ServiceProperties servicePropertiesBase64 = new ru.rosreestr.client.isur.model.base64.ServiceProperties();
        servicePropertiesBase64.setData(base64Props);
        servicePropertiesBase64.setSignature(createSignature(base64Props));
        parameter.setAny(servicePropertiesBase64);

        return parameter;
    }

    //TODO fill in from db
    private String getCadastralNumber() {
        return "77:01:0004028:4359";
    }

    //TODO fill in
    private byte[] createSignature(byte[] data) {
        try {
            return SignatureUtils.sign(data, properties.getSignatureAlias(), properties.getSignaturePassword().toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getNewMessageNum() {
        //TODO retrieve from DB
        return "000000";
    }

    private ru.rosreestr.client.isur.model.Headers createSendTaskHeaders(String serviceNumber) {
        ru.rosreestr.client.isur.model.Headers headers = new ru.rosreestr.client.isur.model.Headers();
        headers.setFromOrgCode(FROM_ORG_CODE);
        headers.setToOrgCode(TO_ORG_CODE);
        headers.setRequestDateTime(CommonUtils.getXmlGregorianCurrentDate());
        headers.setMessageId(UUID.randomUUID().toString());
        headers.setServiceNumber(serviceNumber);
        return headers;
    }

    private void addHeader(WSBindingProvider customBindingIService, ru.rosreestr.client.isur.model.Headers headers) {
        JAXBElement<ru.rosreestr.client.isur.model.Headers> serviceHeader = new ObjectFactory().createServiceHeader(headers);
        try {
            customBindingIService.setOutboundHeaders(
                    Headers.create(JAXBRIContext.newInstance(new Class[]{ru.rosreestr.client.isur.model.Headers.class}, null, null, null, false, null),
                            serviceHeader)
            );
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

package ru.rosreestr.client.isur.processor;

import com.sun.xml.internal.bind.api.JAXBRIContext;
import com.sun.xml.internal.ws.api.message.Headers;
import com.sun.xml.internal.ws.developer.WSBindingProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.rosreestr.client.isur.IService;
import ru.rosreestr.client.isur.Service;
import ru.rosreestr.client.isur.model.CoordinateTaskData;
import ru.rosreestr.client.isur.model.ObjectFactory;
import ru.rosreestr.utils.CommonUtils;

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

    /**
     * Method for invoking {@link IService#sendTask(CoordinateTaskData)} service
     */
    public void sendTask() {
        LOG.info("start service sendTask");
        IService customBindingIService = service.getCustomBindingIService();
        CoordinateTaskData coordinateTaskData = createCoordinateTaskData();
        addHeader((WSBindingProvider) customBindingIService, createSendTaskHeaders());
        customBindingIService.sendTask(coordinateTaskData);
        LOG.info("end service sendTask");
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

    private CoordinateTaskData createCoordinateTaskData() {
        CoordinateTaskData coordinateTaskData = new CoordinateTaskData();
        //TODO fill in
        return coordinateTaskData;
    }

    private ru.rosreestr.client.isur.model.Headers createSendTaskHeaders() {
        ru.rosreestr.client.isur.model.Headers headers = new ru.rosreestr.client.isur.model.Headers();
        headers.setFromOrgCode(FROM_ORG_CODE);
        headers.setToOrgCode(TO_ORG_CODE);
        headers.setRequestDateTime(CommonUtils.getXmlGregorianCurrentDate());
        headers.setMessageId(UUID.randomUUID().toString());
        headers.setServiceNumber(String.format(SERVICE_NUMBER_TEMPLATE, "000000", CommonUtils.getCurrentYear()));//TODO -000000 NNNNNN - порядковый номер обращения за указанной государственной услугой в организации, зарегистрировавшей обращение, в текущем году;
        return headers;
    }

    public Service getService() {
        return service;
    }

}

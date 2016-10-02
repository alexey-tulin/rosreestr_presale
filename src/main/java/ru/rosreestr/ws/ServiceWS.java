package ru.rosreestr.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Tatiana Chukina on 01.10.2016 15:35.
 * <p/>
 * Web service for external systems
 */
@Component
public class ServiceWS implements IService {

    @Autowired
    @Qualifier("serviceWSProcessor")
    ServiceWSProcessor processor;

    public void setFilesAndStatus(@WebParam(partName = "StatusMessage", name = "StatusMessage", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/")
                                  CoordinateStatusData statusMessage) {
        processor.setFilesAndStatus(statusMessage);
    }

    public void acknowledgement(@WebParam(partName = "parameters", name = "ErrorMessage", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/")
                                ErrorMessage parameters) {
        processor.acknowledgement(parameters);
    }
}

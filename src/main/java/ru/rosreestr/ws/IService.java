package ru.rosreestr.ws;

import ru.rosreestr.client.isur.model.CoordinateStatusData;
import ru.rosreestr.client.isur.model.ErrorMessage;
import ru.rosreestr.client.isur.model.ObjectFactory;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

/**
 * This class was generated by Apache CXF 3.1.7
 * 2016-10-02T13:30:57.625+04:00
 * Generated source version: 3.1.7
 * 
 */
@WebService(targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/", name = "IService")
@XmlSeeAlso({ObjectFactory.class})
public interface IService {

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Oneway
    @Action(input = "http://asguf.mos.ru/rkis_gu/coordinate/v5/SetFilesAndStatus")
    @WebMethod(operationName = "SetFilesAndStatus", action = "http://asguf.mos.ru/rkis_gu/coordinate/v5/SetFilesAndStatus")
    public void setFilesAndStatus(
            @WebParam(partName = "StatusMessage", name = "StatusMessage", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/")
            CoordinateStatusData statusMessage
    );

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Oneway
    @Action(input = "http://asguf.mos.ru/rkis_gu/coordinate/v5/Acknowledgement")
    @WebMethod(operationName = "Acknowledgement", action = "http://asguf.mos.ru/rkis_gu/coordinate/v5/Acknowledgement")
    public void acknowledgement(
            @WebParam(partName = "parameters", name = "ErrorMessage", targetNamespace = "http://asguf.mos.ru/rkis_gu/coordinate/v5/")
            ErrorMessage parameters
    );
}
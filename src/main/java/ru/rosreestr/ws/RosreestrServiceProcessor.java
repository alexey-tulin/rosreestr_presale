package ru.rosreestr.ws;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.rosreestr.ws.model.GetInformationRequest;
import ru.rosreestr.ws.model.GetInformationResponse;

/**
 * Created by Tatiana Chukina on 04.10.2016 23:54.
 */
@Service("rosreestrServiceProcessor")
public class RosreestrServiceProcessor {
    private static final Logger LOG = Logger.getLogger(RosreestrServiceProcessor.class);

    public GetInformationResponse getInformation(GetInformationRequest request) {
        LOG.info("ru.rosreestr.ws.RosreestrServiceProcessor.sendRequest");
        GetInformationResponse sendRequestResponse = new GetInformationResponse();
        return sendRequestResponse;
    }
}

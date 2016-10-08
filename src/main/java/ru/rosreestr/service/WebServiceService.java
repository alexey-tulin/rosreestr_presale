package ru.rosreestr.service;

import ru.rosreestr.persistence.model.WebService;

/**
 * Created by KatrinaBosh on 08.10.2016.
 */
public interface WebServiceService {

    void delete(Long id);

    WebService save(WebService webService);
}

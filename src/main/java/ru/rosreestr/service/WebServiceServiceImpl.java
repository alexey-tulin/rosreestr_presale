package ru.rosreestr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rosreestr.persistence.model.WebService;
import ru.rosreestr.persistence.repository.WebServiceRepository;

import javax.annotation.Resource;

/**
 * Created by KatrinaBosh on 08.10.2016.
 */
@Service
public class WebServiceServiceImpl implements WebServiceService {

    @Resource
    private WebServiceRepository webServiceRepository;

    @Override
    @Transactional
    public void delete(Long id) {
        webServiceRepository.delete(id);
    }

    @Override
    @Transactional
    public WebService save(WebService webService) {
        return webServiceRepository.saveAndFlush(webService);
    }
}

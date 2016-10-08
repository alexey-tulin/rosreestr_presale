package ru.rosreestr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosreestr.persistence.model.WebService;

/**
 * Created by KatrinaBosh on 28.09.2016.
 */
public interface WebServiceRepository extends JpaRepository<WebService, Long> {

}


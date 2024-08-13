package com.woojin.model.dd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @PostConstruct
    public void testDatabaseConnection() {
        TestEntity entity = new TestEntity();
        entity.setName("Test Name");

        testRepository.save(entity);

        System.out.println("TestEntity saved: " + entity.getId());
    }
}

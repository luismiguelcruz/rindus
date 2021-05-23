package com.rindus.exerciseRindus.controller;

import com.rindus.exerciseRindus.service.WriteFileServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RindusControllerTest {
    private static final Properties APPLICATION_PROPERTIES = new Properties();
    private static final String RESOURCES_RELATIVE_PATH = "src/main/resources/application.properties";

    @Autowired
    private RindusController rindusController;

    public void before() {
    }

    @Test
    public void testExtractData() {
        final ResponseEntity<Void> responseEntity = rindusController.extractData();

        final File f = new File(WriteFileServiceImpl.RESOURCES_RELATIVE_PATH);
        assertThat(f.length()).isGreaterThan(0);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

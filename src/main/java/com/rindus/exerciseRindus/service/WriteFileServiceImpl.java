package com.rindus.exerciseRindus.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.rindus.exerciseRindus.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Properties;

@Service
public class WriteFileServiceImpl implements WriteFileService{
    private static final Properties APPLICATION_PROPERTIES = new Properties();
    public static final String RESOURCES_RELATIVE_PATH = "src/main/resources/application.properties";

    @Override
    public HttpStatus writeFile(List<Post> elements) {
        HttpStatus status;
        try (InputStream input = new FileInputStream(RESOURCES_RELATIVE_PATH)) {
            APPLICATION_PROPERTIES.load(input);

            final String filePath = APPLICATION_PROPERTIES.getProperty("xml.output.path");

            final XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(new File(filePath), elements);

            status = HttpStatus.OK;
        } catch (FileNotFoundException e) {
            status = HttpStatus.NOT_FOUND;
            e.printStackTrace();
        } catch (IOException e) {
            status = HttpStatus.NOT_FOUND;
            e.printStackTrace();
        }

        return status;
    }
}

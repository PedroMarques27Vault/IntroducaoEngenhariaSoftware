package ies.lab03.ex3_2.jpadata;

import ies.lab03.ex3_2.jpadata.model.Employee;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpadataApplication {



    public static void main(String[] args) {
        SpringApplication.run(JpadataApplication.class, args);
    }

    private ServiceRegistry serviceRegistry;

}

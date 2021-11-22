package com.um.grupo2.instalador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.Scanner;

@Controller
public class InstaladorController {

    @FXML
    private TextField userInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button login;

    private final QueryMgr queryMgr;

    public InstaladorController(QueryMgr queryMgr) {
        this.queryMgr = queryMgr;
    }

    @FXML
    void loginAction(ActionEvent actionEvent) throws IOException {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml"); //hibernate config xml file name
        String newUserName = userInput.getText(),newPassword = passwordInput.getText();//set them as per your needs
        cfg.getProperties().setProperty("hibernate.connection.password",newPassword);
        cfg.getProperties().setProperty("hibernate.connection.username",newUserName);
        cfg.getProperties().setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        cfg.getProperties().setProperty("hibernate.driver-class-name", "com.mysql.cj.jdbc.Driver");
        cfg.getProperties().setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306");
        cfg.getProperties().setProperty("jpa.show-sql","false");
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        String queryString = new Scanner(InstaladorController.class.getResourceAsStream("creacionDeBaseDeDatos.sql"), "UTF-8").useDelimiter("\\A").next();

        Transaction txn = session.beginTransaction();
        EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();
        extracted(queryString, entityManager);
        txn.commit();


//        session.createSQLQuery(queryString);
//        session.
    }

    @Transactional
    @Modifying
    void extracted(String queryString, EntityManager entityManager) {
        Query q = entityManager.createNativeQuery("BEGIN " + queryString + " END;");
        entityManager.joinTransaction();
        q.executeUpdate();
    }


}

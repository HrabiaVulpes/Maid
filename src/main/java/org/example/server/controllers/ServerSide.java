package org.example.server.controllers;

import org.example.server.model.ListElement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
public class ServerSide {

    @GetMapping("/hello")
    String hello() {
        return "hello world!";
    }

    @GetMapping("/hello/{who}")
    String helloYou(@PathVariable String who) {
        return "hello " + who + "!";
    }

    @GetMapping("/addToList/{list}/{element}")
    String addToList(@PathVariable String list, @PathVariable String element) {
        SessionFactory factory;
        Session session;
        Transaction tx;
        Integer employeeID;

        try {
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            ListElement newLe = new ListElement();
            newLe.setList(list);
            newLe.setElement(element);
            employeeID = (Integer) session.save(newLe);
            tx.commit();
            session.close();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return "Saved with id" + employeeID + "success!";
    }

    @GetMapping("/readList/{list}")
    String readList(@PathVariable String list) {
        SessionFactory factory;
        Session session;
        Transaction tx;
        StringBuffer result = new StringBuffer();

        try {
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
            tx = session.beginTransaction();
            List listElements = session.createQuery("FROM ListElement").list();
            for (Iterator iterator = listElements.iterator(); iterator.hasNext();){
                ListElement lem = (ListElement) iterator.next();
                result.append(lem.getElement());
                result.append("\n");
            }
            tx.commit();
            session.close();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return result.toString();
    }
}

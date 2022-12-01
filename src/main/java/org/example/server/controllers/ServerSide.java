package org.example.server.controllers;

import org.example.server.model.ListElement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
            tx = null;

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
        return "";
    }
}

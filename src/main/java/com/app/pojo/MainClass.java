package com.app.pojo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MainClass {
    public static void main(String[] args){

        Student Serge = new Student();
        Serge.setFname("Kusko");
        Serge.setLname("Kuskov");
        Serge.setAge(2);
        Serge.setId(4);
        Configuration con = new Configuration().configure();
        con.addAnnotatedClass (Student.class);
        StandardServiceRegistryBuilder sBilder = new StandardServiceRegistryBuilder()
                .applySettings(con.getProperties());
        SessionFactory sf = con.buildSessionFactory(sBilder.build());

        //create
        Session sessionCreate = sf.openSession();
        Transaction trCreate = sessionCreate.beginTransaction();//Начало транзакции
        sessionCreate.save(Serge);
        trCreate.commit();
        sessionCreate.close();//Завершение транзакции

        //Read
        Session sessionRead = sf.openSession();
        Transaction trRead = sessionRead.beginTransaction();
        Serge = sessionRead.find(Student.class, 1);
        System.out.println(Serge.toString());
        trRead.commit();
        sessionCreate.close();

        //update
        Session sessionUpdate = sf.openSession();
        Transaction trUpdate = sessionUpdate.beginTransaction();
        Serge = sessionUpdate.find(Student.class, 2);
        Serge.setAge(150);
        Serge.setLname("Alexander");
        sessionUpdate.update(Serge);
        trUpdate.commit();
        sessionUpdate.close();

        //delete
        Session sessionDelete = sf.openSession();
        Transaction trDelete = sessionDelete.beginTransaction();
Serge.setId(1);
        sessionDelete.delete(Serge);
        trDelete.commit();
        sessionDelete.close();
    }
}

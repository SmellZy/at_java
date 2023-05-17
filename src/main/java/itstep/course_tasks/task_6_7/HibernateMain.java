package itstep.course_tasks.task_6_7;

import itstep.course_tasks.task_6_7.entity.Data_task6;
import org.hibernate.Session;

//General:
//Install MySQL server
//Make at least two tables (Entities from previous task or any additional if need)
//Make models for those Entities (from Task_5)
//Setup Hibernate for those Entities and local DB
//Check basic CRUD (create, read, update, delete the BD records using Hibernate)
//Generate few rows into all tabled

public class HibernateMain {
    public static void main(String[] args) {
        Data_task6 data = new Data_task6("10:30 AM", "Mary");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //create
        Long id = (Long) session.save(data);
        System.out.println("id:" + id);

        //read
        Data_task6 dataRead = session.get(Data_task6.class, id);
        System.out.println("dataread: "+ dataRead);

        //update
        Data_task6 dataUpdate = dataRead;
        dataUpdate.setPerson("Petro");
        session.saveOrUpdate(dataUpdate);
        System.out.println("dataUpdate:" + session.get(Data_task6.class, id));

        //delete
        session.delete(data);


        session.getTransaction().commit();

        session.close();

        System.exit(0);
    }
}

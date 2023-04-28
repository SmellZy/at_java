package itstep.task_6_7.task_7;

import itstep.task_6_7.entity.Data_task6;
import itstep.task_6_7.entity.Device;
import itstep.task_6_7.entity.Phone;
import itstep.task_6_7.entity.SocialMedia;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.HashSet;

import static itstep.task_6_7.HibernateUtil.getSessionFactory;

//General:
//Install MySQL server
//Make at least two tables (Entities from previous task or any additional if need)
//Make models for those Entities (from Task_5)
//Setup Hibernate for those Entities and local DB
//Check basic CRUD (create, read, update, delete the BD records using Hibernate)
//Generate few rows into all tabled

public class HibernateMain7 {
    public static void main(String[] args) {
        Data_task6 data = new Data_task6("10:30 AM", "Mary");
        Session session = getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //OneToOne
        data.setPhone(new Phone("+38099527579"));
        Long id = (Long) session.save(data);
        System.out.println("OneToOne: "+session.get(Data_task6.class,id));

        //OneToMany
        data.setDevices(new HashSet<>(Arrays.asList(new Device("Samsung"),new Device("LoloPhone"))));
        session.saveOrUpdate(data);
        data.getDevices().forEach(session::save);
        System.out.println("OneToMany: "+session.get(Data_task6.class,id));

        //ManyToMany
        Data_task6 data2=new Data_task6("16:40 PM","Vasyl");
        SocialMedia facebook=new SocialMedia("facebook");
        SocialMedia fiktok=new SocialMedia("fiktok");
        Long id2 = (Long) session.save(data2);

        session.save(facebook);
        session.save(fiktok);

        data.setSocialMedia(Arrays.asList(facebook));
        data2.setSocialMedia(Arrays.asList(facebook,fiktok));

        session.saveOrUpdate(data);
        session.save(data2);

        System.out.println("ManyToMany "+session.get(Data_task6.class,id));
        System.out.println("ManyToMany "+session.get(Data_task6.class,id2));

        session.getTransaction().commit();
        session.close();
        System.exit(0);
    }
}

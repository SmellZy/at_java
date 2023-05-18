package itstep.task_25_Framework.hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import static itstep.task_25_Framework.hibernate.HibernateUtil.getSessionFactory;


public class HibernateMain {
    static Session session = getSessionFactory().getCurrentSession();
    static Integer PRIORITY_HIGH=40;
    static Integer PRIORIRY_NORMAL=30;
    static Integer SEVERITY_HIGH=40;
    static Integer SEVERITY_NORMAL=30;
    public static void main(String[] args) throws NoSuchAlgorithmException {

        session.beginTransaction();
        createBagWithPriorityAndSeveroty(SEVERITY_HIGH, PRIORIRY_NORMAL);
        createBagWithPriorityAndSeveroty(SEVERITY_NORMAL, PRIORIRY_NORMAL);
        createBagWithPriorityAndSeveroty(SEVERITY_NORMAL, PRIORITY_HIGH);

        printAllBugs();

        session.getTransaction().commit();
        session.close();
        System.exit(0);
    }

    private static void createBagWithPriorityAndSeveroty(Integer priority, Integer severity) throws NoSuchAlgorithmException {
        Integer id= new Random().nextInt(999999);
        Integer projectId = new Random().nextInt(999999);
        MantisBugEntity mantisBag=new MantisBugEntity();
        mantisBag.setId(id+0L);
        mantisBag.setProjectId(projectId+0L);
        mantisBag.setReporterId(670041+0L);
        mantisBag.setHandlerId(166837+0L);
        mantisBag.setDuplicateId(0+0L);
        mantisBag.setPriority(priority);
        mantisBag.setSeverity(severity);
        mantisBag.setReproducibility(30);
        mantisBag.setStatus(90);
        mantisBag.setResolution(10);
        mantisBag.setProjection(10);
        mantisBag.setEta(10);
        mantisBag.setBugTextId(id);
        mantisBag.setOs("");
        mantisBag.setOsBuild("");
        mantisBag.setPlatform("");
        mantisBag.setVersion("");
        mantisBag.setFixedInVersion("");
        mantisBag.setBuild("");
        mantisBag.setProfileId(0);
        mantisBag.setViewState(10);
        mantisBag.setSummary("some text");
        mantisBag.setSponsorshipTotal(0);
        mantisBag.setSticky(0);
        mantisBag.setTargetVersion("");
        mantisBag.setCategoryId(1);
        mantisBag.setDateSubmitted(1683533960+0L);
        mantisBag.setDueDate(1+0L);
        mantisBag.setLastUpdated(1684316725+0L);

        session.save(mantisBag);
    }

    private static void printAllBugs() {

        Query query = session.createQuery("FROM "+MantisBugEntity.class.getName());
        List<MantisBugEntity> results = query.list();
        results.forEach(mb -> {System.out.println(mb.getProjectId() +"\t"+mb.getSeverity());
        });
        System.out.println("results "+results);
    }

}
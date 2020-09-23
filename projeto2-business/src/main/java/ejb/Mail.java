package ejb;

import com.sun.mail.smtp.SMTPTransport;
import data.Item;
import data.User;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.Properties;

@Singleton
@Startup
public class Mail {
    @PersistenceContext(name = "EMails")
    EntityManager em;
    Logger log = Logger.getLogger("Mails");

    @Schedule(hour="16", minute="00", dayOfMonth="*" ,month="*", year="*", info="MyTimer")
    public void sendEmails(){

        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtp.socketFactory.port","25");
        props.put("mail.smtps.quitwait", "false");
        props.setProperty("mail.transport.protocol", "smtp");

        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props);

        Query q = em.createQuery("select user from User user");
        List<User> users = q.getResultList();

        System.out.println(users.size());

        q = em.createQuery("select item from Item item");
        List<Item> items = q.getResultList();

        System.out.println(items.size());

        if(items.size() > 2){
            for (User user: users){
                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("mybaycatalog@gmail.com"));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
                    message.setSubject("New Items");
                    String content = items.get(items.size()-1).getName() + "\n" + items.get(items.size()-2).getName() +
                            "\n" + items.get(items.size()-3).getName();
                    message.setText(content);
                    message.setSentDate(new Date());
                    SMTPTransport t = null;
                    try { t = (SMTPTransport)session.getTransport("smtps");
                    } catch (NoSuchProviderException e) { }
                    t.connect("smtp.gmail.com","mybaycatalog@gmail.com","cadeiraIS");

                    t.sendMessage(message, message.getAllRecipients());
                    t.close();
                    log.warning("Sent email from mybaycatalog@gmail.com to "+user.getEmail());
                }catch (MessagingException e) {
                    System.out.println(e);
                    log.severe("Error sending email to "+user.getEmail());
                }
            }
        }



    }


}

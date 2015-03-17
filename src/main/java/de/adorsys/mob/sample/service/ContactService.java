package de.adorsys.mob.sample.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.slf4j.Logger;

import de.adorsys.mob.sample.model.Contact;

@Named
public class ContactService {

    private static final String FROM = System.getProperty("mail.from", "mkn@adorsys.de");

    @Inject
    private Logger log;

    @Inject
    private Session session;

    @Inject
    private MongoCollection collection;

    private void sendMail(String to) {

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(FROM));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("This is the Subject Line!");

            message.setContent("<h1>This is actual message</h1>", "text/html");

            Transport.send(message);
            log.debug("Sent message successfully to " + to + " ....");
        } catch (MessagingException mex) {
            log.error("ERROR", mex);
        }
    }
    
    public List<Contact> list() {
        List<Contact> contacts = new ArrayList<Contact>();
        MongoCursor<Contact> cursor = collection.find().as(Contact.class);
        log.debug("CURSOR: " + cursor.count());
         while (cursor.hasNext()) {
            contacts.add(cursor.next());
        }
        return contacts;
    }

    public void add(Contact contact) {
        collection.insert(contact);
        sendMail(contact.getMail());
    }
}

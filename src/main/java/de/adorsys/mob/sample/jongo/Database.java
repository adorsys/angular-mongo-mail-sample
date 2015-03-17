package de.adorsys.mob.sample.jongo;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.MongoClient;

@Singleton
public class Database {

    protected Logger LOG = LoggerFactory.getLogger(Database.class);

    Jongo jongo;

    @PostConstruct
    private void init() {
        try {
            String host = System.getProperty("mongo.host", "192.168.254.254");
            String port = System.getProperty("mongo.port", "27017");
            String database = System.getProperty("mongo_database", "sample");
            DB db = new MongoClient(host, Integer.parseInt(port)).getDB(database);
            jongo = new Jongo(db);
        } catch (Exception e) {
            LOG.error("ERRO", e);
        }
    }

    @Produces
    public MongoCollection getCollection() {
        return jongo.getCollection("contact");
    }

}

package tour.lifecycle;


import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class DatabaseLoader
{
    private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

    private Server databaseServer;

    public void onContextInitialized(@Observes @Initialized(ApplicationScoped.class) Object event)
    {
        logger.info("Context ApplicationScoped init Event");
        try
        {
            synchronized(this)
            {
                if(databaseServer == null)
                {
                    databaseServer = Server.createTcpServer("-tcpPort", "2000").start();
                    logger.info("Started H2 db on port " + databaseServer.getPort());
                }
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void onStart(@Observes StartupEvent event)
    {
        logger.info("Application Startup Event");
    }

    public void onStop(@Observes ShutdownEvent event)
    {
        logger.info("Application Shutdown Event");

        synchronized(this)
        {
            if(databaseServer != null)
            {
                databaseServer.stop();
                databaseServer = null;
                logger.info("Stopped H2 database server");
            }
        }
    }
}

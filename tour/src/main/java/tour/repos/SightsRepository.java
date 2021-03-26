package tour.repos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tour.model.Sight;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@ActivateRequestContext
@ApplicationScoped
public class SightsRepository implements PanacheRepository<Sight>{

    private final Logger logger = LoggerFactory.getLogger(SightsRepository.class);

    public Sight findByName(final String name)
    {
        logger.info("Call to findByName({})", name);

        if (name == null)
        {
            return null;
        }

        return find("name", name).firstResult();
    }

    @Override
    @Transactional
    public void persist(final Sight sight)
    {
        logger.info("Call to addSight({})", sight);

        // using a lambda expression
        try
        {
            PanacheRepository.super.persist(sight);
        }
        catch (final Exception e)
        {
            logger.error("Exception during add", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(final Sight sight)
    {
        logger.info("Call to deleteSight({})", sight);

        Optional.ofNullable(sight).map(Sight::getName).ifPresent(this::deleteByName);
    }

    @Transactional
    public boolean deleteByName(final String key)
    {
        logger.info("Call to deleteByName({})", key);

        if (key != null)
        {
            getEntityManager()
                    .createNativeQuery("DELETE FROM sights WHERE name = :name")
                    .setParameter("name", key)
                    .executeUpdate();

            final long updateNum = delete("name", key);

            return updateNum > 0;
        }

        return false;
    }

    @Override
    @Transactional
    public long deleteAll()
    {
        logger.info("Call to deleteAll()");

        getEntityManager().createQuery("DELETE FROM Sight").executeUpdate();

        return 0;
    }

}

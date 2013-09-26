package fr.gouv.defense.dga.sillage.saxo.jpa.business.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.gouv.defense.dga.sillage.saxo.jpa.business.BusinessException;
import fr.gouv.defense.dga.sillage.saxo.jpa.business.VilleManager;
import fr.gouv.defense.dga.sillage.saxo.jpa.dao.Filter;
import fr.gouv.defense.dga.sillage.saxo.jpa.dao.Sort;
import fr.gouv.defense.dga.sillage.saxo.jpa.dao.VilleDAO;
import fr.gouv.defense.dga.sillage.saxo.model.Ville;

/**
 * La classe <code>VilleManagerImpl</code> implémente l'interface <code>VilleManager</code>.
 */
@Component("villeManager")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class VilleManagerImpl implements VilleManager
{
    /** le gestionnaire de traces */
    private final static Logger LOGGER = LoggerFactory.getLogger(VilleManager.class);

    /** le gestionnaire d'objets persistents */
    @Autowired
    private VilleDAO villeDao;

    /** la source de messages */
    @Autowired
    private MessageSource messageSource;

    @Override
    @Transactional(readOnly = false)
    public Ville save(Ville ville) throws BusinessException
    {
        List<Ville> others = villeDao.findDuplicate(ville);
        if (others != null && !others.isEmpty()) { throw new BusinessException(messageSource.getMessage("duplicate-ville", null, "msg not found", null)); }
        return villeDao.synchronize(ville);
    }

    @Override
    public List<Ville> list()
    {
        return villeDao.listAll();
    }

    @Override
    public List<Ville> list(Sort sort)
    {
        return villeDao.list(sort);
    }

    @Override
    public List<Ville> list(int start, int end)
    {
        return villeDao.list(start, end);
    }

    @Override
    public List<Ville> list(int start, int end, Sort sort)
    {
        return villeDao.list(start, end, sort);
    }

    @Override
    public List<Ville> list(int start, int end, List<? extends Filter> filters)
    {
        return villeDao.list(start, end, filters);
    }

    @Override
    public List<Ville> list(int start, int end, List<? extends Filter> filters, Sort sort)
    {
        if (sort == null)
        {
            sort = new Sort("nom");
        }
        return villeDao.list(start, end, filters, sort);
    }

    @Override
    public List<Ville> findByNom(String nom)
    {
        return villeDao.findByNom(nom);
    }

    @Override
    public List<Ville> findByNomAndCodePostal(String nom, String codePostal)
    {
        if (codePostal == null) { return villeDao.findByNom(nom); }
        return villeDao.findByNomAndCodePostal(nom, codePostal);
    }

    @Override
    public Ville findById(long id)
    {
        return villeDao.findById(id);
    }

    @Override
    public List<Ville> findDuplicate(Ville ville)
    {
        return villeDao.findDuplicate(ville);
    }

    @Override
    public List<Ville> lookupByNom(String start)
    {
        return villeDao.lookupByNom(start);
    }

    @Override
    public List<Ville> lookupByNom(String start, int maxCount)
    {
        return villeDao.lookupByNom(start, maxCount);
    }

    @Override
    public List<Ville> lookupByNomAndCodePostal(String nomStart, String codePostalStart)
    {
        return villeDao.lookupByNomAndCodePostal(nomStart, codePostalStart);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Ville ville) throws BusinessException
    {
        if (ville == null || ville.getId() == null) { throw new BusinessException(messageSource.getMessage(
                "non-persistent-object-delete",
                null,
                "non-persistent-object-delete",
                null)); }
        Ville villeToDelete = villeDao.findById(ville.getId());
        villeDao.delete(villeToDelete);
        LOGGER.info("Ville supprimée: " + ville.getId());
    }

    @Override
    public long count()
    {
        return villeDao.count();
    }

    @Override
    public long count(List<? extends Filter> filters)
    {
        return villeDao.count(filters);
    }
}

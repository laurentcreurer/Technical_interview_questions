package fr.gouv.defense.dga.sillage.saxo.jpa.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import fr.gouv.defense.dga.sillage.saxo.jpa.dao.VilleDAO;
import fr.gouv.defense.dga.sillage.saxo.model.Ville;

/**
 * La classe <code>VilleDAOImpl</code> implémente l'interface <code>VilleDAO</code>.
 */
@Repository("villeDAO")
public class VilleDAOImpl extends GenericDAOImpl<Ville, Long> implements VilleDAO
{
    @Override
    public List<Ville> findByNom(String nom)
    {
        Assert.notNull(nom, "nom");

        TypedQuery<Ville> query = entityManager.createNamedQuery("findVilleByNom", Ville.class);
        query.setParameter("nom", nom);

        List<Ville> list = query.getResultList();

        return list;
    }

    @Override
    public List<Ville> findByNomAndCodePostal(String nom, String codePostal)
    {
        Assert.notNull(nom, "nom");

        TypedQuery<Ville> query = entityManager.createNamedQuery("findVilleByNomAndCodePostal", Ville.class);
        query.setParameter("nom", nom);
        query.setParameter("codePostal", codePostal);

        List<Ville> list = query.getResultList();

        return list;
    }

    @Override
    public List<Ville> findDuplicate(Ville ville)
    {
        Assert.notNull(ville, "ville");
        Assert.notNull(ville.getNom(), "nom");

        TypedQuery<Ville> query = entityManager.createNamedQuery("findDuplicateVille", Ville.class);
        query.setParameter("nom", ville.getNom());
        query.setParameter("codePostal", ville.getCodePostal());
        if (ville.getId() != null)
        {
            query.setParameter("id", ville.getId());
        }
        else
        {
            query.setParameter("id", Integer.valueOf(0));
        }

        List<Ville> list = query.getResultList();

        return list;
    }

    @Override
    public List<Ville> lookupByNom(String nomStart)
    {
        return lookupByNom(nomStart, -1);
    }

    @Override
    public List<Ville> lookupByNom(String nomStart, int maxCount)
    {
        Assert.notNull(nomStart, "nom");

        TypedQuery<Ville> query = entityManager.createNamedQuery("lookupVilleByNom", Ville.class);
        query.setParameter("nomStart", nomStart + '%');
        if (maxCount > 0)
        {
            query.setMaxResults(maxCount);
        }
        List<Ville> list = query.getResultList();

        return list;
    }

    @Override
    public List<Ville> lookupByNomAndCodePostal(String nomStart, String codePostalStart)
    {
        TypedQuery<Ville> query = entityManager.createNamedQuery("lookupVilleByNomAndCodePostal", Ville.class);

        String nomPattern = "%";
        if (nomStart != null && !nomStart.isEmpty())
        {
            nomPattern = nomStart + '%';
        }
        String codePostalPattern = "%";
        if (codePostalStart != null && !codePostalStart.isEmpty())
        {
            codePostalPattern = codePostalStart + '%';
        }
        query.setParameter("nomStart", nomPattern);
        query.setParameter("codePostalStart", codePostalPattern);

        List<Ville> list = query.getResultList();

        return list;
    }

}

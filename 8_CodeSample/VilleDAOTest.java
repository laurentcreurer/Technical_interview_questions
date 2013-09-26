package fr.gouv.defense.dga.sillage.saxo.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import fr.gouv.defense.dga.sillage.saxo.jpa.BaseTest;
import fr.gouv.defense.dga.sillage.saxo.model.Ville;

@DataSet("TestSaxoMission.xml")
public class VilleDAOTest extends BaseTest
{
    @SpringApplicationContext
    private ApplicationContext applicationContext;

    @SpringBean("villeDAO")
    private VilleDAO villeDao;

    @Test(expectedExceptions =
    { InvalidDataAccessApiUsageException.class })
    public void findByIdNull()
    {
        villeDao.findById(null);
    }

    @Test
    public void findByNom()
    {
        List<Ville> result = villeDao.findByNom("MONTREUIL");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 4);

        List<String> noms = new ArrayList<String>();
        for (int i = 0; i < result.size(); i++)
        {
            noms.add("MONTREUIL");
        }
        ReflectionAssert.assertPropertyReflectionEquals("nom", noms, result);
    }

    @Test
    public void findByNomAndCodePostal()
    {
        List<Ville> result = villeDao.findByNomAndCodePostal("MONTREUIL", "93100");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);

        ReflectionAssert.assertPropertyReflectionEquals("nom", "MONTREUIL", result.get(0));
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", "93100", result.get(0));
        ReflectionAssert.assertPropertyReflectionEquals("districtParis", true, result.get(0));
        ReflectionAssert.assertPropertyReflectionEquals("banlieueParis", true, result.get(0));
        ReflectionAssert.assertPropertyReflectionEquals("agglomeration", false, result.get(0));
    }

    @Test
    public void findDuplicateNew()
    {
        Ville ville = new Ville();
        ville.setNom("MONTREUIL");
        ville.setCodePostal("93100");
        List<Ville> result = villeDao.findDuplicate(ville);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);

        ReflectionAssert.assertPropertyReflectionEquals("nom", "MONTREUIL", result.get(0));
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", "93100", result.get(0));
    }

    @Test
    public void findDuplicateExisting()
    {
        List<Ville> result = villeDao.findByNomAndCodePostal("MONTREUIL", "93100");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);

        Ville ville = result.get(0);
        ville.setNom("PARIS");
        ville.setCodePostal("75000");
        result = villeDao.findDuplicate(ville);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);

        ReflectionAssert.assertPropertyReflectionEquals("nom", "PARIS", result.get(0));
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", "75000", result.get(0));
    }
}

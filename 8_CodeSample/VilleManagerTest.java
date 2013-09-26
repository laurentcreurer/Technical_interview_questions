package fr.gouv.defense.dga.sillage.saxo.jpa.business;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import fr.gouv.defense.dga.sillage.saxo.jpa.BaseTest;
import fr.gouv.defense.dga.sillage.saxo.jpa.dao.Filter;
import fr.gouv.defense.dga.sillage.saxo.jpa.dao.Sort;
import fr.gouv.defense.dga.sillage.saxo.jpa.dao.impl.LikeFilter;
import fr.gouv.defense.dga.sillage.saxo.jpa.dao.impl.NotNullFilter;
import fr.gouv.defense.dga.sillage.saxo.model.Ville;

@DataSet("TestBusinessSaxoMission.xml")
public class VilleManagerTest extends BaseTest
{
    @SpringApplicationContext
    private ApplicationContext applicationContext;

    @SpringBean("villeManager")
    private VilleManager villeManager;

    @Test
    public void add()
    {
        Ville ville = new Ville();
        ville.setNom("LILLE");
        ville.setCodePostal("59000");
        try
        {
            ville = villeManager.save(ville);
            Assert.assertTrue(ville.getId() > 0);
            ReflectionAssert.assertPropertyReflectionEquals("nom", "LILLE", ville);
            ReflectionAssert.assertPropertyReflectionEquals("codePostal", "59000", ville);
        }
        catch (BusinessException e)
        {
            Assert.fail(e.getLocalizedMessage(), e);
        }
    }

    // @Test
    // public void modify() {
    // Ville ville = villeManager.findById(1L);
    // Assert.assertNotNull(ville);
    // ville.setCodePostal("75021");
    // try {
    // villeManager.save(ville);
    // } catch (BusinessException e) {
    // Assert.fail(e.getLocalizedMessage());
    // }
    //
    // ville = villeManager.findById(1L);
    // ReflectionAssert.assertPropertyReflectionEquals("nom", "PARIS", ville);
    // ReflectionAssert.assertPropertyReflectionEquals("codePostal", "75021", ville);
    // }

    @Test(expectedExceptions =
    { BusinessException.class })
    public void duplicateAdd() throws Exception
    {
        Ville ville = new Ville();
        ville.setNom("PARIS");
        ville.setCodePostal("75000");
        villeManager.save(ville);
    }

    @Test(expectedExceptions =
    { BusinessException.class })
    public void duplicateModify() throws Exception
    {
        Ville ville = villeManager.findById(1L);
        ville.setNom("LYON");
        ville.setCodePostal("69000");
        villeManager.save(ville);
    }

    @Test
    public void list()
    {
        long count = villeManager.count();
        Assert.assertEquals(count, 8L);

        List<Ville> result = villeManager.list();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 8);

        result = villeManager.list(2, 4);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 3);
        ReflectionAssert.assertPropertyReflectionEquals("nom", Arrays.asList(new String[]
        { "PALAISEAU", "MONTREUIL", "MONTREUIL" }), result);
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", Arrays.asList(new String[]
        { "91120", "28500", "62170" }), result);

        result = villeManager.list(5, 7, new Sort("nom", true));
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 3);
        ReflectionAssert.assertPropertyReflectionEquals("nom", Arrays.asList(new String[]
        { "MONTREUIL", "PALAISEAU", "PARIS" }), result);
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", Arrays.asList(new String[]
        { "28500", "91120", "75000" }), result);

        List<? extends Filter> filters = Collections.singletonList(new LikeFilter("nom", "PA%"));
        count = villeManager.count(filters);
        Assert.assertEquals(count, 2L);

        result = villeManager.list(0, 1, filters, new Sort("nom", true));
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
        ReflectionAssert.assertPropertyReflectionEquals("nom", Arrays.asList(new String[]
        { "PALAISEAU", "PARIS" }), result);
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", Arrays.asList(new String[]
        { "91120", "75000" }), result);

        filters = Collections.singletonList(new NotNullFilter("pays", true));
        count = villeManager.count(filters);
        Assert.assertEquals(count, 7L);

        result = villeManager.list(0, 1, filters, new Sort("nom", true));
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
        ReflectionAssert.assertPropertyReflectionEquals("nom", Arrays.asList(new String[]
        { "LYON", "MONTREUIL" }), result);
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", Arrays.asList(new String[]
        { "69000", "93100" }), result);

        filters = Collections.singletonList(new NotNullFilter("pays", false));
        count = villeManager.count(filters);
        Assert.assertEquals(count, 1L);

        result = villeManager.list(0, 1, filters);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        ReflectionAssert.assertPropertyReflectionEquals("nom", Arrays.asList(new String[]
        { "LONDRES" }), result);
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", Arrays.asList(new String[]
        { null }), result);
    }

    @Test
    public void findByNom()
    {
        List<Ville> result = villeManager.findByNom("LYON");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        ReflectionAssert.assertPropertyReflectionEquals("id", 2L, result.get(0));
        ReflectionAssert.assertPropertyReflectionEquals("nom", "LYON", result.get(0));
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", "69000", result.get(0));
    }

    @Test
    public void lookupByNom()
    {
        List<Ville> result = villeManager.lookupByNom("PA");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
        ReflectionAssert.assertPropertyReflectionEquals("id", Arrays.asList(new Long[]
        { 3L, 1L }), result);
        ReflectionAssert.assertPropertyReflectionEquals("nom", Arrays.asList(new String[]
        { "PALAISEAU", "PARIS" }), result);
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", Arrays.asList(new String[]
        { "91120", "75000" }), result);

        result = villeManager.lookupByNom("PA", 1);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
    }

    @Test
    public void lookupByNomAndCodePostal()
    {
        List<Ville> result = villeManager.lookupByNomAndCodePostal("M", null);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 4);
        ReflectionAssert.assertPropertyReflectionEquals("id", Arrays.asList(new Long[]
        { 4L, 5L, 6L, 7L }), result);
        ReflectionAssert.assertPropertyReflectionEquals("nom", Arrays.asList(new String[]
        { "MONTREUIL", "MONTREUIL", "MONTREUIL", "MONTREUIL" }), result);
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", Arrays.asList(new String[]
        { "28500", "62170", "85200", "93100" }), result);

        result = villeManager.lookupByNomAndCodePostal(null, "9");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
        ReflectionAssert.assertPropertyReflectionEquals("id", Arrays.asList(new Long[]
        { 3L, 7L }), result);
        ReflectionAssert.assertPropertyReflectionEquals("nom", Arrays.asList(new String[]
        { "PALAISEAU", "MONTREUIL" }), result);
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", Arrays.asList(new String[]
        { "91120", "93100" }), result);

        result = villeManager.lookupByNomAndCodePostal("P", "7");
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        ReflectionAssert.assertPropertyReflectionEquals("id", Arrays.asList(new Long[]
        { 1L }), result);
        ReflectionAssert.assertPropertyReflectionEquals("nom", Arrays.asList(new String[]
        { "PARIS" }), result);
        ReflectionAssert.assertPropertyReflectionEquals("codePostal", Arrays.asList(new String[]
        { "75000" }), result);
    }
}

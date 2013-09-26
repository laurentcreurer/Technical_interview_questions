package fr.gouv.defense.dga.sillage.saxo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ForeignKey;

/**
 * La classe <code>Ville</code> représente une ville en base de données.
 */
@Entity
@Table(name = "VILLE")
@NamedQueries(
{ @NamedQuery(name = "findVilleByNom", query = "FROM Ville WHERE upper(nom) = upper(:nom)"),
        @NamedQuery(name = "findVilleByNomAndCodePostal", query = "FROM Ville WHERE upper(nom) = upper(:nom) AND codePostal = :codePostal"),
        @NamedQuery(name = "findDuplicateVille", query = "FROM Ville WHERE nom = upper(:nom) AND codePostal = :codePostal AND (:id IS NULL OR id != :id)"),
        @NamedQuery(name = "lookupVilleByNom", query = "FROM Ville WHERE upper(nom) LIKE upper(:nomStart) ORDER BY nom"),
        @NamedQuery(name = "lookupVilleByNomAndCodePostal", query = "FROM Ville WHERE upper(nom) LIKE upper(:nomStart) AND codePostal LIKE :codePostalStart") })
@SequenceGenerator(name = "SEQ_VIL_ID", sequenceName = "SEQ_VIL_ID")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL, region = "saxoCacheRefVille")
public class Ville implements Serializable
{
    /** l'identifiant de sérialisation */
    private static final long serialVersionUID = -4210795543123572453L;

    /** l'identifiant de l'objet */
    @Id
    @Column(name = "VIL_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VIL_ID")
    private Long id;

    /** le nom de la ville */
    @Column(name = "VIL_NOM", length = 30)
    private String nom;

    /** le code postal de la ville */
    @Column(name = "VIL_CODE_POSTAL", length = 5)
    private String codePostal;

    /** indique si la ville donne droit aux même indemnités que Paris */
    @Column(name = "VIL_TAUX_PARIS")
    private boolean districtParis;

    /** indique si la ville est dans la banlieue de Paris */
    @Column(name = "VIL_ACCES_PARIS")
    private boolean banlieueParis;

    /** indique si la ville est une agglomération de plus de 200000 habitants */
    @Column(name = "VIL_TAUX_200")
    private boolean agglomeration;

    /** le pays dans lequel se trouve la ville */
    @ManyToOne
    @JoinColumn(name = "VIL_CODE_PAYS", referencedColumnName = "MISS_ETR_CODE_PAYS", nullable = true)
    @ForeignKey(name = "FK_VIL_CODE_PAYS")
    private Pays pays;

    /** indique si un taux exclusif s'pplique à cette ville */
    @Column(name = "VIL_TAUX_EXC")
    private boolean tauxExclusif;

    /**
     * Construit un objet <code>Ville</code>.
     */
    public Ville()
    {
    }

    /**
     * Retourne l'identifiant (champ <code>VIL_ID</code>).
     * 
     * @return l'identifiant de la ville
     */
    @NonVisual
    public Long getId()
    {
        return id;
    }

    /**
     * Modifie l'identifiant (champ <code>OMI_NO_MISSION</code>).
     * 
     * @param newId le nouvel identifiant
     */
    public void setId(Long newId)
    {
        this.id = newId;
    }

    /**
     * Retourne le nom (champ <code>VIL_NOM</code>).
     * 
     * @return le nom de la ville
     */
    
    public String getNom()
    {
        return nom;
    }

    /**
     * Modifie le nom (champ <code>VIL_NOM</code>).
     * 
     * @param newNom le nouveau nom
     */
    public void setNom(String newNom)
    {
        this.nom = newNom;
    }

    /**
     * Retourne le code postal (champ <code>VIL_CODE_POSTAL</code>).
     * 
     * @return le code postal de la ville
     */
    public String getCodePostal()
    {
        return codePostal;
    }

    /**
     * Modifie le code postal (champ <code>VIL_CODE_POSTAL</code>).
     * 
     * @param newCodePostal le nouveau code postal
     */
    public void setCodePostal(String newCodePostal)
    {
        this.codePostal = newCodePostal;
    }

    /**
     * Indique si la ville donne droit aux même indemnités que Paris (champ
     * <code>VIL_TAUX_PARIS</code>).
     * 
     * @return <code>true</code> si la ville donne droit aux même indemnités que Paris
     */
    public boolean isDistrictParis()
    {
        return districtParis;
    }

    /**
     * Modifie le statut d'indemnisation aux même taux que Paris (champ <code>VIL_TAUX_PARIS</code>
     * ).
     * 
     * @param newDistrictParis le nouveau statut d'indemnisation
     */
    public void setDistrictParis(boolean newDistrictParis)
    {
        this.districtParis = newDistrictParis;
    }

    /**
     * Indique si la ville est dans la banlieue de Paris (champ <code>VIL_ACCES_PARIS</code>).
     * 
     * @return <code>true</code> si la ville est dans la banlieue de Paris
     */
    public boolean isBanlieueParis()
    {
        return banlieueParis;
    }

    /**
     * Modifie le statut de situation dans la banlieue de Paris (champ <code>VIL_ACCES_PARIS</code>
     * ).
     * 
     * @param newBanlieueParis le nouveau statut de situation
     */
    public void setBanlieueParis(boolean newBanlieueParis)
    {
        this.banlieueParis = newBanlieueParis;
    }

    /**
     * Indique si la ville est une agglomération de plus de 200000 habitants (champ
     * <code>VIL_TAUX_200</code>).
     * 
     * @return <code>true</code> si la ville est une agglomération
     */
    public boolean isAgglomeration()
    {
        return agglomeration;
    }

    /**
     * Modifie le statut d'agglomération de la ville (champ <code>VIL_TAUX_200</code>).
     * 
     * @param newAgglomeration le nouveau statut d'aggloméraztion
     */
    public void setAgglomeration(boolean newAgglomeration)
    {
        this.agglomeration = newAgglomeration;
    }

    /**
     * Retourne le pays (champ <code>VIL_CODE_PAYS</code>).
     * 
     * @return le pays de la ville
     */
    public Pays getPays()
    {
        return pays;
    }

    /**
     * Modifie le pays (champ <code>VIL_CODE_PAYS</code>).
     * 
     * @param newPays le nouveau pays
     */
    public void setPays(Pays newPays)
    {
        this.pays = newPays;
    }

    /**
     * Indique si un taux spécifique s'applique à la ville (champ <code>VIL_TAUX_EXC</code>).
     * 
     * @return <code>true</code> si un taux spécifique s'applique à la ville
     */
    public boolean isTauxExclusif()
    {
        return tauxExclusif;
    }

    /**
     * Modifie le statut de taux spécifique de la ville (champ <code>VIL_TAUX_EXC</code>).
     * 
     * @param newTauxExclusif le nouveau statut de taux spécifique
     */
    public void setTauxExclusif(boolean newTauxExclusif)
    {
        this.tauxExclusif = newTauxExclusif;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (agglomeration ? 1231 : 1237);
        result = prime * result + (banlieueParis ? 1231 : 1237);
        result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
        result = prime * result + (districtParis ? 1231 : 1237);
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((pays == null) ? 0 : pays.hashCode());
        result = prime * result + (tauxExclusif ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        Ville other = (Ville) obj;
        if (agglomeration != other.agglomeration) { return false; }
        if (banlieueParis != other.banlieueParis) { return false; }
        if (codePostal == null)
        {
            if (other.codePostal != null) { return false; }
        }
        else if (!codePostal.equals(other.codePostal)) { return false; }
        if (districtParis != other.districtParis) { return false; }
        if (nom == null)
        {
            if (other.nom != null) { return false; }
        }
        else if (!nom.equals(other.nom)) { return false; }
        if (pays == null)
        {
            if (other.pays != null) { return false; }
        }
        else if (!pays.equals(other.pays)) { return false; }
        if (tauxExclusif != other.tauxExclusif) { return false; }
        return true;
    }
}

package fr.gouv.defense.dga.sillage.saxo.jpa.dao;

import java.util.List;

import fr.gouv.defense.dga.sillage.saxo.model.Ville;

/**
 * La classe <code>VilleDAO</code> permet l'accès aux objets <code>Ville</code>.
 */
public interface VilleDAO extends GenericDAO<Ville, Long> {
	/**
	 * Recherche des objets <code>Ville</code> selon son champ <code>nom</code>.
	 * 
	 * @param nom le champ <code>nom</code> de l'objet <code>Ville</code>
	 * @return les objets <code>Ville</code> trouvés.
	 */
	List<Ville> findByNom(String nom);
	
	/**
	 * Recherche des objets <code>Ville</code> selon leurs champs <code>nom</code> et <code>codePostal</code>.
	 * 
	 * @param nom le champ <code>nom</code> de l'objet <code>Ville</code>
	 * @param codePostal le champ <code>codePostal</code> de l'objet <code>Ville</code>
	 * @return l'objet <code>Ville</code> trouvé.
	 */
	List<Ville> findByNomAndCodePostal(String nom, String codePostal);
	
	/**
	 * Recherche des objets <code>Ville</code> en doublon avec l'objet <code>ville</code>.
	 * 
	 * @param ville l'objet dont les doublons sont recherchés
	 * @return les objets <code>Ville</code> trouvés.
	 */
	List<Ville> findDuplicate(Ville ville);
	
	/**
	 * Recherche les objets <code>Ville</code> dont le champ <code>nom</code> commence par <code>nomStart</code>.
	 * 
	 * @param nomStart le début du champ <code>nom</code>
	 * @return les objets <code>Ville</code> correspondants
	 */
	List<Ville> lookupByNom(String nomStart);
	
	/**
	 * Recherche les objets <code>Ville</code> dont le champ <code>nom</code> commence par <code>nomStart</code>.
	 * 
	 * @param nomStart le début du champ <code>nom</code>
	 * @param maxCount le nombre maximum de résultats, ou -1 pour ne pas limiter
	 * @return les objets <code>Ville</code> correspondants
	 */
	List<Ville> lookupByNom(String nomStart, int maxCount);
	
	/**
	 * Recherche les objets <code>Ville</code> dont le champ <code>nom</code> commence par <code>nomStart</code> et le
	 * champ <code>codePostal</code> commence par <code>codePostalStart</code>.
	 * 
	 * @param nomStart le début du champ <code>nom</code>
	 * @param codePostalStart le début du champ <code>codePostal</code>
	 * @return les objets <code>Ville</code> correspondants
	 */
	List<Ville> lookupByNomAndCodePostal(String nomStart, String codePostalStart);
}

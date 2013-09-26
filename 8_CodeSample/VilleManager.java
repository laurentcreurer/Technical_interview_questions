package fr.gouv.defense.dga.sillage.saxo.jpa.business;

import java.util.List;

import fr.gouv.defense.dga.sillage.saxo.model.Ville;

/**
 * La classe <code>VilleManager</code> permet de gérer les objets <code>Ville</code>.
 */
public interface VilleManager extends ExtendedListProvider<Ville> {
	/**
	 * Enregistre l'objet <code>ville</code>.
	 * 
	 * @param ville l'objet ajouté
	 * @return l'objet ville sauvegardé
	 * @throws BusinessException en cas d'incohérence
	 */
	Ville save(Ville ville) throws BusinessException;
	
	/**
	 * Recherche des objets <code>Ville</code> selon leurs champ <code>nom</code>.
	 * 
	 * @param nom le champ <code>nom</code> de l'objet <code>Ville</code>
	 * @return les objets <code>Ville</code> trouvés
	 */
	List<Ville> findByNom(String nom);
	
	/**
	 * Recherche des objets <code>Ville</code> selon leurs champ <code>nom</code> et <code>codePostal</code>.
	 * 
	 * @param nom le champ <code>nom</code> de l'objet <code>Ville</code>
	 * @param codePostal le champ <code>codePostal</code> de l'objet <code>Ville</code>
	 * @return les objets <code>Ville</code> trouvés
	 */
	List<Ville> findByNomAndCodePostal(String nom, String codePostal);
	
	/**
	 * Recherche un objet <code>Ville</code> selon son champ <code>id</code>.
	 * 
	 * @param id le champ <code>id</code> de l'objet <code>Ville</code>
	 * @return l'objet <code>Ville</code> trouvé.
	 */
	Ville findById(long id);
	
	/**
	 * Recherche des objets <code>Ville</code> en doublon avec l'objet <code>ville</code>.
	 * 
	 * @param ville l'objet dont les doublons sont recherchés
	 * @return les objets <code>Ville</code> trouvés.
	 */
	List<Ville> findDuplicate(Ville ville);

	/**
	 * Recherche les objets <code>Ville</code> dont le champ <code>nom</code> commence par <code>start</code>.
	 * 
	 * @param start le début du champ <code>nom</code>
	 * @return les objets <code>Ville</code> correspondants
	 */
	List<Ville> lookupByNom(String start);

	/**
	 * Recherche les objets <code>Ville</code> dont le champ <code>nom</code> commence par <code>start</code>.
	 * 
	 * @param start le début du champ <code>nom</code>
	 * @param maxCount le nombre maximum de résultats
	 * @return les objets <code>Ville</code> correspondants
	 */
	List<Ville> lookupByNom(String start, int maxCount);
	
	/**
	 * Recherche les objets <code>Ville</code> dont le champ <code>nom</code> commence par <code>nomStart</code> et le
	 * champ <code>codePostal</code> commence par <code>codePostalStart</code>.
	 * 
	 * @param nomStart le début du champ <code>nom</code>
	 * @param codePostalStart le début du champ <code>codePostal</code>
	 * @return les objets <code>Ville</code> correspondants
	 */
	List<Ville> lookupByNomAndCodePostal(String nomStart, String codePostalStart);
	
	/**
	 * Supprime la ville <code>ville</code>.
	 * 
	 * @param ville la ville à supprimer
	 * @throws BusinessException en cas d'erreur
	 */
	void delete(Ville ville) throws BusinessException;
	
	/**
	 * Retourne le nombre de villes disponibles.
	 * 
	 * @return le nombre de villes disponibles
	 */
	long count();
}

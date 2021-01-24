package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;

public class RechercheSynchroneSequentielle extends RechercheSynchroneAbstraite {

	NomAlgorithme nom;
	
	public RechercheSynchroneSequentielle(NomAlgorithme nom) {
		this.nom = nom;
	}	
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {		
		for (HyperLien<Bibliotheque> h : bibliotheques) {
			Optional<HyperLien<Livre>> retour = rechercheSync(h, l, client);
			if(!retour.isEmpty()) {
				return retour;
			}
		}		
		return Optional.empty();
	}

	@Override
	public NomAlgorithme nom() {
		return this.nom;
	}

}

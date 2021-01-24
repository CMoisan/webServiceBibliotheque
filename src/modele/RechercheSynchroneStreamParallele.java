package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;

public class RechercheSynchroneStreamParallele extends RechercheSynchroneAbstraite{

	NomAlgorithme nom;
	
	public RechercheSynchroneStreamParallele(NomAlgorithme nom) {
		this.nom = nom;
	}	
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		return bibliotheques.stream().map(h -> {
			return rechercheSync(h, l, client);
		}).filter(Optional::isPresent).findFirst().orElse(Optional.empty());
	}

	@Override
	public NomAlgorithme nom() {
		return this.nom;
	}

}

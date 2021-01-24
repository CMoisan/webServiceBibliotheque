package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;

public class RechercheAsynchroneStreamParallele extends RechercheAsynchroneAbstraite{

	NomAlgorithme nom;
	
	public RechercheAsynchroneStreamParallele(NomAlgorithme nom) {
		this.nom = nom;
	}	
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		return bibliotheques.stream().map(h -> {
			return rechercheAsync(h, l, client);
		}).map(Outils::remplirPromesse).filter(Optional::isPresent).findFirst().orElse(Optional.empty());
	}

	@Override
	public NomAlgorithme nom() {
		return this.nom;
	}

}

package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;

public class RechercheAsynchroneSequentielle extends RechercheAsynchroneAbstraite {

	NomAlgorithme nom;
	
	public RechercheAsynchroneSequentielle(NomAlgorithme nom) {
		this.nom = nom;
	}	
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {		
		
		List<Future<Optional<HyperLien<Livre>>>> futures = new ArrayList<>();
		
		Optional<HyperLien<Livre>> result = Optional.empty();
		
		for (HyperLien<Bibliotheque> h : bibliotheques) {
			futures.add(rechercheAsync(h, l, client));
		}		
		for (Future<Optional<HyperLien<Livre>>> future : futures) {
			try {
				Optional<HyperLien<Livre>> optional = future.get();
				if (optional.isPresent()) {
					result = optional;
				}
			} catch (Exception e) {}
		}
		return result;
	}

	@Override
	public NomAlgorithme nom() {
		return this.nom;
	}

}

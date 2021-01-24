package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;

public class ImplemRechercheSynchrone extends RechercheSynchroneAbstraite {

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NomAlgorithme nom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Optional<HyperLien<Livre>> rechercheSync(HyperLien<Bibliotheque> h, Livre l, Client client) {
		Bibliotheque b = infrastructure.jaxrs.LienVersRessource.proxy(client, h, Bibliotheque.class);
		return b.chercher(l);
	}
}

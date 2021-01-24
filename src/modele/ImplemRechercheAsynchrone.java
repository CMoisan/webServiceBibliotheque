package modele;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.langage.Types;

public class ImplemRechercheAsynchrone extends RechercheAsynchroneAbstraite{

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
	protected Future<Optional<HyperLien<Livre>>> rechercheAsync(HyperLien<Bibliotheque> h, Livre l, Client client) {
		//Cible : client
		//->
		//Recherche Asynchrone 		Types.typeRetourChercherAsync();
		
		WebTarget wt = client.target(h.getUri());
		return wt.path(JAXRS.SOUSCHEMIN_ASYNC).request().accept(JAXRS.TYPE_MEDIATYPE).async()
				.put(Entity.entity(l, JAXRS.TYPE_MEDIATYPE), Types.typeRetourChercherAsync());
	}

	@Override
	protected Future<Optional<HyperLien<Livre>>> rechercheAsyncAvecRappel(HyperLien<Bibliotheque> h, Livre l,
			Client client, InvocationCallback<Optional<HyperLien<Livre>>> retour) {
		// TODO Auto-generated method stub
		return null;
	}

}

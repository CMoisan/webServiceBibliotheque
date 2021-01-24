package modele;

import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;

public abstract class RechercheSynchroneAbstraite implements AlgorithmeRecherche {

	protected Optional<HyperLien<Livre>> rechercheSync(HyperLien<Bibliotheque> h, Livre l, Client client)
	{
		Bibliotheque b = infrastructure.jaxrs.LienVersRessource.proxy(client, h, Bibliotheque.class);
		return b.chercher(l);
	}

}

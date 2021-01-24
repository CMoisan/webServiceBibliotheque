package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RechercheSynchroneStreamRx extends RechercheSynchroneAbstraite{
	NomAlgorithme nom;
	
	public RechercheSynchroneStreamRx(NomAlgorithme nom) {
		this.nom = nom;
	}

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		Observable<HyperLien<Bibliotheque>> O = Observable.fromIterable(bibliotheques);
		return O.flatMap(h -> Observable.fromCallable (() -> rechercheSync(h, l, client))
				.subscribeOn(Schedulers.io())).filter(Optional::isPresent).blockingFirst();
		}

	@Override
	public NomAlgorithme nom() {
		return this.nom;
	}
}

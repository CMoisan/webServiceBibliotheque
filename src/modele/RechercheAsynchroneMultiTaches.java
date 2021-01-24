package modele;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.InvocationCallback;

import infrastructure.jaxrs.HyperLien;

public class RechercheAsynchroneMultiTaches extends RechercheAsynchroneAbstraite {
	
	NomAlgorithme nom;
	
	public RechercheAsynchroneMultiTaches(NomAlgorithme nom) {
		this.nom = nom;
	}	

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		CountDownLatch CDL = new CountDownLatch(bibliotheques.size());
		ExecutorService exe = Executors.newCachedThreadPool();
		AtomicReference<Optional<HyperLien<Livre>>> ar = new AtomicReference<Optional<HyperLien<Livre>>>();
		ar.set(Optional.empty());
		try {
			for(HyperLien<Bibliotheque> h : bibliotheques) {
				InvocationCallback<Optional<HyperLien<Livre>>> callback = new InvocationCallback<Optional<HyperLien<Livre>>>() {
					@Override
					public void completed(Optional<HyperLien<Livre>> response) {
						CDL.countDown();
					}

					@Override
					public void failed(Throwable throwable) {
					}
				};
				exe.submit((Runnable) () -> {				
					Future<Optional<HyperLien<Livre>>> result = rechercheAsyncAvecRappel(h, l, client, callback);
					try {
						Optional<HyperLien<Livre>> optional = result.get();
					if (optional.isPresent()) {
						ar.set(optional);
						liberer(CDL);
					}
					} catch (Exception e){}
				});
				CDL.await();
			}
		} catch(Exception e) {}
		return ar.get();
	}

	@Override
	public NomAlgorithme nom() {
		return this.nom;
	}

	public void liberer(CountDownLatch CDL) 
	{
		while(CDL.getCount() > 0) {
			CDL.countDown();
		}
	}
	
}

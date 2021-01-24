package modele;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;

public class RechercheSynchroneMultiTaches extends RechercheSynchroneAbstraite{

	NomAlgorithme nom;
	
	public RechercheSynchroneMultiTaches(NomAlgorithme nom) {
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
				exe.submit(() -> {				
					Optional<HyperLien<Livre>> result = rechercheSync(h, l, client);
					CDL.countDown();
					if (result.isPresent()) {
						ar.set(result);
						liberer(CDL);
					}
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

TODO
3.2.2.2
3.2.2.3


- Repertoire {

	@PUT
	@Produces(TYPE_MEDIA)
	@Consumes(TYPE_MEDIA)
	@ReponsesPUTOption
	// Requ�te (m�thode http + url) : 
	// Corps : 
	// R�ponses (� sp�cifier par code) :
	// - code : 
	Optional<HyperLien<Livre>> chercher(Livre l);


	@PUT
	@ReponsesPUTOption
	@Path(JAXRS.SOUSCHEMIN_ASYNC)
	@Consumes(JAXRS.TYPE_MEDIA)
	@Produces(JAXRS.TYPE_MEDIA)
	// Requ�te (m�thode http + url) : 
	// Corps : 
	// R�ponses (� sp�cifier par code) :
	// - code : 
	Future<Optional<HyperLien<Livre>>> chercherAsynchrone(Livre l, @Suspended final AsyncResponse ar);

	@GET
	@Path(SOUSCHEMIN_CATALOGUE)
	@Produces(TYPE_MEDIA)
	// Requ�te (m�thode http + url) : 
	// Corps : 
	// R�ponses (� sp�cifier par code) :
	// - code : 
	HyperLiens<Livre> repertorier();

- Archive 
	@Path("{id}")
	@ReponsesGETNullEn404
	// Adresse de la sous-ressource : 
	// Requ�te sur la sous-ressource (m�thode http + url) : 
	// Corps : 
	// R�ponses (� sp�cifier par code) :
	// - code : 
	Livre sousRessource(@PathParam("id") IdentifiantLivre id) ;

	@Path("{id}")
	@GET 
	@Produces(JAXRS.TYPE_MEDIA)
	@ReponsesGETNullEn404
	// Requ�te (m�thode http + url) : 
	// Corps : 
	// R�ponses (� sp�cifier par code) :
	// - code : 
	Livre getRepresentation(@PathParam("id") IdentifiantLivre id);

	@POST
	@ReponsesPOSTEnCreated
	@Consumes(JAXRS.TYPE_MEDIA)
	@Produces(JAXRS.TYPE_MEDIA)
	// Requ�te (m�thode http + url) : 
	// Corps : 
	// R�ponses (� sp�cifier par code) :
	// - code : 
	HyperLien<Livre> ajouter(Livre l);
}

- AdminAlgo
	@PUT
	@Path(JAXRS.SOUSCHEMIN_ALGO_RECHERCHE)
	@Consumes(JAXRS.TYPE_MEDIA)
	// Requ�te (m�thode http + url) : 
	// Corps : 
	// R�ponses (� sp�cifier par code) :
	// - code : 
	void changerAlgorithmeRecherche(NomAlgorithme
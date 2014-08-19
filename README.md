FlickrClusterization
==============
#1. O projektu
Tema projekta je da se kreira aplikacija koja će omogućiti da se pristupi podacima o slikama korišćenjem Flickr API-a (https://www.flickr.com/services/api/). Potrebno je preuzeti podatke o slikama, zatim obraditi podatke i sačuvati ih, kasnije pomoću servisa Geonames (http://www.geonames.org/) preuzeti podatke o lokaciji, odnosno državi.
Takođe, potrebno je klasterizovati preuzete slike.

Faze u razvoju aplikacije:
* Preuzimanje podataka o slikama kroz Flickr API i njihovo čuvanje
* Obrada podataka (biranje podataka koji sadrže podatak o lokaciji)
* Dobijanje i čuvanje podataka o lokaciji
* Klasterizacija podataka

#2. Domenski model
Nakon analize podataka koje pruža Flickr API (https://www.flickr.com/services/api/), kreiran je domenski model koji je prikazan na slici ispod (Slika 1).

![Slika 1 - Domenski model](images/domainmodel.jpg)

Slika 1 - Domenski model

Klasa Photo sadrži podatke o slici kao što su: id, id vlasnika, naslov slike, server, secret kod i podatak o lokaciji koji se dobija korišćenjem Geonames servisa. 

#3. Rešenje
Aplikacija preuzima podatke sa dva različita izvora ([Flickr](https://www.flickr.com/services/api/), [Geonames](http://www.geonames.org/)), zatim spaja te podatke i stavlja ih u JSON fajl. 

[Flickr](https://www.flickr.com/services/api/) daje podatke o slici u željenom formatu. U sledećem primeru poziva korišćen je JSON format:
>[https://api.flickr.com/services/rest/?method=flickr.photos.search&text=shark&sort=relevance&page=1&api_key=64a298d057676a6d7298262797a23440&format=json]
(https://api.flickr.com/services/rest/?method=flickr.photos.search&text=shark&sort=relevance&page=1&api_key=64a298d057676a6d7298262797a23440&format=json)

Parametar *method* ukazuje na metodu koja se poziva na sajtu, u ovom slučaju je to metoda search. Da bi se koristila metoda search, potrebno je uneti kriterijum pretrage, što je u primeru zadata reč shark. Parametar *sort* se odnosi na način sortiranja, ovde je to po relevantnosti. Zatim, parametar *page* nam pomaže da se lakše kećemo po dobijenim rezultatima pretrage, odnosno da nam kao rezultat metode bude vraćena samo prva stranica. Parametar *api_key* se dobija prilikom registracije za korišćenje Flickr API-a, i obavezan je deo poziva svake metode. Parametar *format* nam omogućava da rezultat metode bude vraćen u formatu koji nama odgovara, u ovom slučaju je to JSON. Podaci koji se na osnovu ovog poziva preuzimaju su: id, userId, title, server, secret i location.

[Geonames](http://www.geonames.org/) daje podatke o geografskoj lokaciji unetog mesta. Ovde se mogu dobiti informacije kao što su geografska dužina i širina, kod države, populacija, naziv države. U sledećem primeru dat je poziv ovog servisa:

>[http://api.geonames.org/searchJSON?q=Lake%20Worth,%20FL,%20USA&maxRows=1&username=jelena_tabas](http://api.geonames.org/searchJSON?q=Lake%20Worth,%20FL,%20USA&maxRows=1&username=jelena_tabas)

Parametar *q* se odnosi na kriterijum pretrage, odnosno tu stoji informacija koje mesto želimo da nađemo, u ovom slučaju to je Lake Worth, FL, USA. Parametar *max_rows* nam omogućava da ograničimo rezultate pretrage, odnosno da dobijemo samo jedan rezultat, ovde je to prvi, koji smatramo najtačnijim, odnosno preciznijim za našu pretragu. Parametar *username* je potreban kako bi se izvršilo pozivanje Geonames servisa, dobija se prilikom registracije za korišćenje servisa. Preuzeti podaci: countryName.

Nakon preuzimanja podataka, može se primetiti da za pojedine slike ne postoji podatak o lokaciji, te nije mogao da se izvrši poziv servisa [Geonames](http://www.geonames.org/), odnosno nisu dobijeni podaci. Zbog toga su slike koje nemaju podatak o lokaciji izbačene iz obrade, tačnije, one koje imaju podatak o lokaciji su premeštene u drugi fajl.

Takođe, kako bi se kasnije omogućila klasterizacija slika na osnovu vrste ajkule koja je prikazana, bilo je potrebno izvršiti obradu podataka. Zbog toga se iz naslova slika zaključivalo koja je vrsta ajkule na slici, te se sama slika nazivala po vrsti. Rezultat ove operacije je dobijeno dvadeset i jedna vrsta ajkule i jedna kao nedefinisana koja ima naziv ajkula.

Svi podaci koji su dobijeni smeštani su u JSON fajl. Primer JSON objekta dat je niže:

```
	[
	   {
	    "id": "3873710525",
	    "userId": "21915962@N02",
	    "secret": "9460b2dd37",
	    "server": "2480",
	    "title": "White",
	    "location": "Australia",
	    "lon": 135.0,
	    "lat": -25.0,
	    "cluster": 0
	  },
	  {
	    "id": "8856286135",
	    "userId": "36937610@N08",
	    "secret": "b55718d380",
	    "server": "3825",
	    "title": "White",
	    "location": "United States",
	    "lon": -80.41394,
	    "lat": 37.22957,
	    "cluster": 0
	  }
	]
```

Kao što se može videti, JSON fajl se sastoji od JSON objekata. Svaki objekat sadrži podatke o slici: id, userId, secret, server, title, location, lon, lat i cluster. Dalje su ovi podaci konvertovani u ARFF fajl. ARFF (Attribute-Relation File Format) fajl je tekstualan ASCII koji opisuje listu instanci koje dele set atributa. ARFF fajl se kasnije koristi za klasterizaciju. Za klasterizaciju je izabrano četiri klastera, jer je probom ustanovljeno da se oko te vrednosti dešava najmanje rasipanje podataka, odnosno najmanja promena greške nastale usled klasterovanja. Nakon klasterovanja, u podacima je promenjena vrednost podatka cluster, pa je dobjen novi JSON fajl:

```
	[
	   {
	    "id": "3873710525",
	    "userId": "21915962@N02",
	    "secret": "9460b2dd37",
	    "server": "2480",
	    "title": "White",
	    "location": "Australia",
	    "lon": 135.0,
	    "lat": -25.0,
	    "cluster": 3
	  },
	  {
	    "id": "8856286135",
	    "userId": "36937610@N08",
	    "secret": "b55718d380",
	    "server": "3825",
	    "title": "White",
	    "location": "United States",
	    "lon": -80.41394,
	    "lat": 37.22957,
	    "cluster": 1
	  }
	]
```


#4. Korišćena tehnologija

Aplikacija je napisana u programskom jeziku Java. 

Prilikom realizacije aplikacije korišćene su sledeće tehnologije:

1. [Biblioteka za izvrsenje HTTP poziva](http://hc.apache.org/httpcomponents-client-ga/) - za izvršavanje poziva Flickr i Geonames metoda, kako bi se omogućilo dobijanje podataka na kojima će se kasnije raditi.

```
    	setMethod(new GetMethod(getRequest()));
	setStatusCode(getClient().executeMethod(getMethod()));
	if (getStatusCode() != HttpStatus.SC_OK) {
	   System.err.println("Method failed: " + getMethod().getStatusLine());
	}
	setRstream(null);
	setRstream(getMethod().getResponseBodyAsStream());
```

2. [Biblioteka za rad sa JSON-om](http://json.org/java/) - za korišćenje JSON formata i objekata. JSON omogućava lakšu manipulaciu podacima, s obzirom da su podaci smešteni u stablo. Identifikator podatka je njegov naziv preko koga mu se i pristupa. JSON fajl je i čitljiv, odnosno može se jasno razumeti, i ima jednostavnu formu.

```
      JSONObject jobj = new JSONObject(jstr);
      JSONObject owner = jobj.getJSONObject("photo").getJSONObject("owner");
```

3. [GSON biblioteka](https://code.google.com/p/google-gson/) - omogućava korišćenje JSON-a tako što konvertuje Java objekte u JSON objekte. Takođe, može se koristiti i obrnuto za pretvaranje JSON stringa u Java objekte.

```
	JsonArray photosArray = new JsonArray();
	JsonObject photoJson = new JsonObject();
	photoJson.addProperty("id", p.getId());
```

4. [Weka](http://weka.wikispaces.com/Use+WEKA+in+your+Java+code) - (Waikato Environment for Knowledge Analysis) - softver za mašinsko učenje koji omogućuje da izvršimo klasterizaciju podataka (slika). Weka čita iz ARFF fajla podatke, i na osnovu zadatih parametara (filtera, broja klastera, načina klasterizacije) vrši klasterizaciju i ispisuje rezultat.

```
	ClusterEvaluation eval = new ClusterEvaluation();
	eval.setClusterer(filteredClusterer);
	eval.evaluateClusterer(data);
```

5. [Google maps] (https://developers.google.com/maps/documentation/javascript/) - koriščeno za prikaz klastera na mapi sveta. Omogućava postavljanje različitih markera na mapi, kako bi se označila pripadnost određenoj grupi klastera. Za korišćenje je potreban *api key* kako bi se pozvao servis:

```
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBksrT0O5yUkw0zc2J7YhaYo_zy5FmM5Y4&sensor=false">
        </script>
```

Za postavljanje markera napravljena je posebna funkcija:

```
        if (data.cluster === 0) {
           var latLng = new google.maps.LatLng(data.lat, data.lon);
           // Creating a marker and putting it on the map
           var marker = new google.maps.Marker({
		   position: latLng,
		   title: data.cluster.toString(),
		   icon: imageB
           });
           marker.setMap(map);
       } else if (data.cluster === 1) {
          var latLng = new google.maps.LatLng(data.lat, data.lon);
           // Creating a marker and putting it on the map
           var marker = new google.maps.Marker({
		   position: latLng,
		   title: data.cluster.toString(),
		   icon: imageP
           });
           marker.setMap(map);
       }
```

U ovom primeru korišćen je SimpleKMeans algoritam za klasterovanje. Jedan je od najpoznatijih algoritama za klasterovanje. Koristi se tako što mu se zada broj klastera, a zatim on prolazeći kroz iteracije razvrstava podatke. SimpleKMeans grupiše instance na osnovu Euklidske udaljenosti u ravni koja je postavljena atributima tih instanci. Na početku, prilikom inicijalizacije nasumično bira onoliki broj težišta klastera koliko je zadati broj klastera. U sledećoj iteraciji razvrstava instance na osnovu udaljenosti od težišta klastera. Zatim pomera težište klastera na osnovu izračunatih proseka vrednosti instanci u klasteru. Ovaj postupak se ponavlja sve dok algoritam ne konvergira, jer daljim razvrstavanjem se nece dobiti značajnije promene, pa se proces zaustavlja. Primenom ovog algoritma, dobijeni su sledeći klasteri:

![Slika 2 - Type clusters](images/results.jpg)

Kao što se na slici 2 može videti, dobijeno je pet klastera sa po 140, 279, 49, 72 i 37 instanci respektivno. U prvom klasteru se nalaze vrste ajkula među kojima je najvise nurse ajkula. U drugom su pretežno bele ajkule. U trećem limun, tigar i reef ajkule. U četvrtom najviše ima kit ajkula. U petom je najviše neimenovanih ajkula, zatimreef i belih ajkula.

U prvom se nalaze instance čija je lokacija najbliža Evropi (njih 140). U drugom se nalaze one koje su najbliže Sjedinjenim Američkim Državama (njih 279). U trećem se nalaze one koje su po lokaciji najbliže Kini (njih 49). U četvrtom se nalaze one instance koje su po lokaciji najbliže Australiji (njih 72). U petom se nalaze one koje su najbliže Južnoj Americi i Africi (njih 37).

Na slici 2 se može videti koliko instanci je u kom klasteru. Od ukupno 577 instanci 140 (24%) je u prvom klasteru; 279 (48%) u drugom; 49 (8%) u trećem; 72 (12%) u četvrtom i 37 (6%) u petom. Metodom probanja dobijeno je da se se prilikom klasterizacije na pet klastera dobija najmanje osipanje podataka uz najmanju kvadratnu grešku od 4.942677457897851.

Na slici 3 se može videti prikaz klasterizovanih podataka prikazanih na mapi sveta.

![Slika 3 - Clusters](images/map.jpg)

#5. Priznanja
Ova aplikacija je nastala kao rezultat seminarskog rada iz predmeta [Inteligentni sistemi](http://is.fon.rs/) na Fakultetu organizacionih nauka, Univerziteta u Beogradu, Srbija, 2014. godine.

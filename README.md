FlickrClusterization
==============
#1. O projektu
Tema projekta je da se kreira aplikacija koja će omogućiti da se na osnovu raspoloživih podataka o slikama dobijenih korišćenjem Flickr API-a (https://www.flickr.com/services/api/) klasterizuju slike. S obzirom na raznovrsnost slika koje se svakodnevno postavljaju na Flickr, odlučeno je da se domen aplikacije ograniči na slike o ajkulama.

Faze u razvoju aplikacije:
* Preuzimanje podataka o slikama kroz Flickr API i njihovo čuvanje
* Obrada podataka (biranje podataka koji sadrže podatak o lokaciji)
* Dobijanje i čuvanje podataka o lokaciji
* Klasterizacija podataka

#2. Domenski model
Nakon analize podataka koje pruža Flickr API (https://www.flickr.com/services/api/), kreiran je domenski model koji je prikazan na slici ispod (Slika 1).

![Slika 1 - Domenski model](images/domainmodel.jpg)

Slika 1 - Domenski model

Klasa Photo sadrži podatke o slici kao što su: id, id vlasnika, naslov slike, server, secret kod, podatak o lokaciji, geografsku dužinu i širinu i klaster kome slika pripada nakon što je izvršena klasterizacija.

#3. Rešenje

Flickr je sajt koji sčuži za postavljanje slika kako bi korisnici, i ostali posetioci sajta mogli da ih vide. Flickr omogućava pristup svojim podacima preko ([API-ja](https://www.flickr.com/services/api/). Kako bi se ova mogućnost koristila, prvo je potrebno registrovati se na sajt. Nakon registracije potrebno je zatražiti *API key* koji se kasnije koristi za pozivanje različitih funkcija ovog servisa. Primer korišćenja API-ja za dobijanje slika:

[Flickr](https://www.flickr.com/services/api/) daje podatke o slici u željenom formatu. U sledećem primeru poziva korišćen je JSON format:
>[https://api.flickr.com/services/rest/?method=flickr.photos.search&text=shark&sort=relevance&page=1&api_key=64a298d057676a6d7298262797a23440&format=json]
(https://api.flickr.com/services/rest/?method=flickr.photos.search&text=shark&sort=relevance&page=1&api_key=64a298d057676a6d7298262797a23440&format=json)

Parametri koji se šalju:
* *method* ukazuje na metodu koja se poziva na sajtu, u ovom slučaju je to metoda search. Da bi se koristila metoda search, potrebno je uneti kriterijum pretrage, što je u primeru zadata reč *shark*
* *sort* se odnosi na način sortiranja, ovde je to po relevantnosti.
* *page* nam pomaže da se lakše krećemo po dobijenim rezultatima pretrage, odnosno da nam kao rezultat metode bude vraćena samo prva stranica
* *api_key* se dobija prilikom registracije za korišćenje Flickr API-a.
* *format* nam omogućava da rezultat metode bude vraćen u formatu koji nama odgovara, u ovom slučaju je to JSON.

Primer odgovora Flickr API-ja:

```
jsonFlickrApi({"photos":{"page":1,"pages":12505,"perpage":100,"total":"1250481","photo":[{"id":"5266243108","owner":"21915962@N02","secret":"8bb6ebc8b8","server":"5284","farm":6,"title":"Caribbean Reef Shark at Tiger Beach in the Bahamas","ispublic":1,"isfriend":0,"isfamily":0},{"id":"1038089969","owner":"94802649@N00","secret":"e01b5dd141","server":"1012","farm":2,"title":"Tiger shark","ispublic":1,"isfriend":0,"isfamily":0}]
```

U podacima koji su dobijeni na ovaj način ne postoji podatak o geografskoj širini i dužini, pa je zbog toga potrebno koristiti eksterni servis Geonames.

Geonames je baza geografskih podataka koji su dostupni korišćenjem [Geonames API-ja](http://www.geonames.org/)). Kako bi se omogućilo korišćenje API-ja, potrebno je prvo registrovati se. Prilikom registracije bira se *username* koji se kasnije koristi za poziv operacija ovog servisa. Primer poziva operacije za dobijanje naziva države:

>[http://api.geonames.org/searchJSON?q=Lake%20Worth,%20FL,%20USA&maxRows=1&username=jelena_tabas](http://api.geonames.org/searchJSON?q=Lake%20Worth,%20FL,%20USA&maxRows=1&username=jelena_tabas)

Poslati parametri:
* *q* se odnosi na kriterijum pretrage, odnosno tu stoji informacija koje mesto želimo da nađemo, u ovom slučaju to je Lake Worth, FL, USA.
* *max_rows* nam omogućava da ograničimo rezultate pretrage, odnosno da dobijemo samo jedan rezultat, ovde je to prvi, koji smatramo najtačnijim, odnosno preciznijim za našu pretragu.
* *username* je potreban kako bi se izvršilo pozivanje Geonames servisa, dobija se prilikom registracije za korišćenje servisa.

Primer odgovora Geonames API-ja:

```
{"totalResultsCount":60,"geonames":[{"countryId":"6252001","adminCode1":"FL","countryName":"United States","fclName":"city, village,...","countryCode":"US","lng":"-80.05699","fcodeName":"populated place","toponymName":"Lake Worth","fcl":"P","name":"Lake Worth","fcode":"PPL","geonameId":4161422,"lat":"26.6159","adminName1":"Florida","population":34910}]}
```

Za potrebe aplikacije preuzeto je 800 slika korišćenjem Flickr API-ja. Ove slike su prošle selekciju, odnosno odbačene su one koje nemaju podatak o lokaciji, nakon čega je ostalo 577 slika. Podatak o lokaciji, odnosno nazivu mesta i/ili države poslat je Geonames API-ju, a natrag su dobijeni podaci o geografskoj širini i dužini koji će se kasnije koristiti za klasterizaciju.
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
	    "lat": -25.0
	  },
	  {
	    "id": "8856286135",
	    "userId": "36937610@N08",
	    "secret": "b55718d380",
	    "server": "3825",
	    "title": "White",
	    "location": "United States",
	    "lon": -80.41394,
	    "lat": 37.22957
	  }
	]
```

Kao što se može videti, JSON fajl se sastoji od JSON objekata. Svaki objekat sadrži podatke o slici: id, userId, secret, server, title, location, lon, lat. Dalje su ovi podaci konvertovani u ARFF fajl. ARFF (Attribute-Relation File Format) fajl je tekstualan ASCII koji opisuje listu instanci koje dele set atributa. ARFF fajl se kasnije koristi za klasterizaciju.

#4. Proces klasterovanja

U ovom primeru korišćen je SimpleKMeans algoritam za klasterovanje. Jedan je od najpoznatijih algoritama za klasterovanje. Koristi se tako što mu se zada broj klastera, a zatim on prolazeći kroz iteracije razvrstava podatke. SimpleKMeans grupiše instance na osnovu Euklidske udaljenosti u ravni koja je postavljena atributima tih instanci. Na početku, prilikom inicijalizacije nasumično bira onoliki broj težišta klastera koliko je zadati broj klastera. U sledećoj iteraciji razvrstava instance na osnovu udaljenosti od težišta klastera. Zatim pomera težište klastera na osnovu izračunatih proseka vrednosti instanci u klasteru. Ovaj postupak se ponavlja sve dok algoritam ne konvergira, jer daljim razvrstavanjem se nece dobiti značajnije promene, pa se proces zaustavlja.

Podaci koji su u ovoj aplikaciji korišćeni za klasterizaciju su geografska širina i dužina, a kako bi se utvrdilo koji broj klastera je najpogodniji, odnosno koji broj je onaj koji pravi najmanju srednju kvadratnu grešku, potrebno je izvršiti probu. Srednja kvadratna greška merenja je mera odstupanja vrednosti od srednje vrednosti. Ova mera pokazuje prosečno rasipanje rezultata merenja, što je posledica nesavršene preciznosti mernog uređaja.

Rezultat probe se može videti na slici 2. Menjanjem broja klastera ustanovljeno je da se prilikom klasterizacije na pet klastera dobija najmanje osipanje podataka uz najmanju srednju kvadratnu grešku od 4.942677457897851. Nakon klasterovanja, u podacima je promenjena vrednost podatka cluster, pa je dobjen novi JSON fajl:

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

![Slika 2 - Graph](images/graph.jpg)

Slika 2- Grafikon srednjih kvadratnih grešaka

Primenom SimpleKMeans algoritma, dobijeni su sledeći klasteri:

![Slika 3 - Type clusters](images/results.jpg)

Slika 3 - Rezultat klasterizacije

Kao što se na slici 3 može videti, dobijeno je pet klastera sa po 140, 279, 49, 72 i 37 instanci respektivno. U prvom klasteru se nalaze vrste ajkula među kojima je najvise nurse ajkula. U drugom su pretežno bele ajkule. U trećem limun, tigar i reef ajkule. U četvrtom najviše ima kit ajkula. U petom je najviše neimenovanih ajkula, zatim reef i belih ajkula.

U prvom se nalaze instance čija je lokacija najbliža Evropi (njih 140). U drugom se nalaze one koje su najbliže Sjedinjenim Američkim Državama (njih 279). U trećem se nalaze one koje su po lokaciji najbliže Kini (njih 49). U četvrtom se nalaze one instance koje su po lokaciji najbliže Australiji (njih 72). U petom se nalaze one koje su najbliže Južnoj Americi i Africi (njih 37).

Na slici 4 se može videti prikaz klasterizovanih podataka prikazanih na mapi sveta.

![Slika 4 - Clusters](images/map.jpg)

Slika 4- Prikaz klasterizovanih podataka

#5. Korišćene tehnologije

Aplikacija je napisana u programskom jeziku Java. 

Prilikom realizacije aplikacije korišćene su sledeće tehnologije:

1. [Apache Commons](http://hc.apache.org/httpcomponents-client-ga/) - za izvršavanje poziva Flickr i Geonames metoda, kako bi se omogućilo dobijanje podataka na kojima će se kasnije raditi.

```
    	setMethod(new GetMethod(getRequest()));
	setStatusCode(getClient().executeMethod(getMethod()));
	if (getStatusCode() != HttpStatus.SC_OK) {
	   System.err.println("Method failed: " + getMethod().getStatusLine());
	}
	setRstream(null);
	setRstream(getMethod().getResponseBodyAsStream());
```
2. [GSON biblioteka](https://code.google.com/p/google-gson/) - omogućava korišćenje JSON-a tako što konvertuje Java objekte u JSON objekte. Takođe, može se koristiti i obrnuto za pretvaranje JSON stringa u Java objekte.

```
	JsonArray photosArray = new JsonArray();
	JsonObject photoJson = new JsonObject();
	photoJson.addProperty("id", p.getId());
```

3. [Weka](http://weka.wikispaces.com/Use+WEKA+in+your+Java+code) - (Waikato Environment for Knowledge Analysis) - softver za mašinsko učenje koji omogućuje da izvršimo klasterizaciju podataka (slika). Weka čita iz ARFF fajla podatke, i na osnovu zadatih parametara (filtera, broja klastera, načina klasterizacije) vrši klasterizaciju i ispisuje rezultat.

```
	ClusterEvaluation eval = new ClusterEvaluation();
	eval.setClusterer(filteredClusterer);
	eval.evaluateClusterer(data);
```

4. [Google maps] (https://developers.google.com/maps/documentation/javascript/) - koriščeno za prikaz klastera na mapi sveta. Omogućava postavljanje različitih markera na mapi, kako bi se označila pripadnost određenoj grupi klastera. Za korišćenje je potreban *api key* kako bi se pozvao servis:

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

#6. Priznanja
Ova aplikacija je nastala kao rezultat seminarskog rada iz predmeta [Inteligentni sistemi](http://is.fon.rs/) na Fakultetu organizacionih nauka, Univerziteta u Beogradu, Srbija, 2014. godine.

# Onlineverkaufsplattform für Tickets #

Das Ticketsystem soll eine Verkaufsplattform für Eintrittskarten zu unterschiedlichen Veranstaltungen sein.
Benutzer dieses Ticketsystems sind *Veranstalter* und *Kunden*.
Die Veranstalter richten die Orte und Veranstaltungen ein. Die Kunden können über das Portal Tickets kaufen.
Weitere Akteure sind der *Administrator* und potentielle *externe Systeme*.

Es existieren *Veranstaltungsorte* (locations), *Veranstaltungen* (events) und *Veranstaltungsserien* (event_series).


#### Veranstalter kann folgende Handlungen durchführen: ####

* sich als Veranstalter registrieren.
* Bilder für Veranstaltungen und Orte hochladen.
* Veranstaltungsort / Veranstaltung / Veranstaltungsserie registrieren.
* laufende Verkäufe einsehen.


#### Kunde kann folgende Handlungen durchführen: ####

* sich als Kunde registieren.
* Veranstaltungen nach verschiedenen Kriterien sortieren.
* Tickets buchen. Auswahl mehrer Tickets möglich, aber nicht mehrerer Veranstaltungen pro Buchung.
* Buchung stornieren (BuchungsID) --> Tickets fließen wieder in Verkauf.


#### Ein Veranstaltungsort (location) besteht aus folgenden Informationselementen: ####

 * Name
 * Bild
 * textuelle Beschreibung
 * Ticketkategorie

	* Anzahl zur Verfügung stehender Tickets unterteilt in ggf.
          Anzahl Reihen und Anzahl Plätze pro Reihe.
	* Raum, Klasse (Loge, VIP), andere Zusätze... 


#### Eine Veranstaltung (event) besteht aus folgenden Informationselementen: ####

 * Name
 * Bild
 * textuelle Beschreibung
 * Art der Veranstaltung
 * Veranstaltungsort


#### Administrator darf folgende Handlungen durchführen: ####

 * Veranstaltungen editieren und/oder löschen.
 * Kundenkonten editieren und/oder löschen.
 * Veranstalterkonten editieren und/oder löschen.


#### Externe Systeme ####

 * können eine Liste der aktuellen Veranstaltungen über einen RESTful Service abrufen (JSON)

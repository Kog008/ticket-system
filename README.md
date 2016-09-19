# Onlineverkaufsplattform für Tickets #

Das Ticketsystem soll eine Verkaufsplattform für Veranstaltungseintrittskarten sein.
Benutzer dieses Ticketsystems sind *Veranstalter* und *Kunde*.
Weitere Akteure sind der *Administrator* und potentielle *externe Systeme*.

Es existieren *Veranstaltungsorte* (locations) und *Veranstaltungen* (events) und *Veranstaltungsserien* (event_series).


#### Ein Veranstaltungsort (location) besteht aus folgenden Informationselementen: ####

 * Name
 * Bild
 * textuelle Beschreibung
 * Ticketkategorie

	* Anzahl zur Verfügung stehender Tickets unterteilt in ggf.
          Anzahl Reihen und Anzahl Plätze pro Reihe.
	* Ort, Klasse (Loge, VIP), Raum, ... 


#### Eine Veranstaltung (event) besteht aus folgenden Informationselementen: ####

 * Name
 * Bild
 * Beschreibung
 * Art der Veranstaltung
 * verfügbare Tickets


#### Veranstalter kann folgende Handlungen durchführen: ####

* registrieren
* Bilder hochladen (um diese später als Thumbnail einer Veranstaltung zu hinterlegen)
* Veranstaltungsort / Veranstaltung / Veranstaltungsserie registrieren
* laufende Verkäufe einsehen
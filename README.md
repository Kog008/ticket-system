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


# Der Systementwurf #
Die folgenden Abschnitte zeigen in steigenden Detailierungsgraden die unterschiedlichen Entwurfsschritte des Systems, welches durch dieses Projekt implementiert wird.


## Das System als Blackbox ##
![00_entwurf_blackbox.png](https://bitbucket.org/repo/BnRroj/images/2851040462-00_entwurf_blackbox.png)
Die primären Benutzer der Onlineticketplattform sind die Veranstalter und ihre Kunden. Veranstalter definieren die Locations, an denen die Events stattfinden. Die Location bestimmt außerdem die zur Verfügung stehenden Tickets. Interessenten können erst einmal ohne Verbindlichkeiten über das Portal durch die Events surfen. Eine Bestellung kann jedoch nur über einen gesonderten Kundenaccount getätigt werden, sofern ein Veranstalter seinerseits über einen gesonderten Veranstalteraccount eine Örtlichkeit und die entsprechende Veranstaltung eingerichtet hat.


## Anwendungsfälle ##
![01_entwurf_usecases.png](https://bitbucket.org/repo/BnRroj/images/2185370120-01_entwurf_usecases.png)
Obige Abbildung zeigt grob die möglichen use cases, die von den in der Systembeschreibung genannten Nutzern über die verschiedenen Schnittstellen durchgeführt werden können. Diesen Anwendungsfällen geben aber nur einen ersten Ausblick auf die noch zu implementierende Geschäftslogik. Im Folgenden werden die abgegrenzten use cases in Aktivitätendiagrammen genauer ausgeführt.


### Use case Kontoverwaltung ###
![02b_entwurf_usecase_kontoVerwalten.png](https://bitbucket.org/repo/BnRroj/images/904828375-02b_entwurf_usecase_kontoVerwalten.png)
Eine Grundfunktionalität der Ticketplattform ist die Anmeldung am Portal. Damit dies möglich ist muss der Nutzer sich ein Konto erstellen. Während des Registrierungsprozess entscheidet der Benutzer, ob ein Veranstalterkonto, oder ein Kundenkonto erstellen möchte. Ersters wird benötigt, um Locations zu erstellen und Events auszurichten. Zu diesen Veranstaltungen können nun geneigte Benutzer über deren Kundenkonto Eintrittskarten zur gewählten Veranstaltung.
Hat man sich an seinem Konto angemeldet können die im Konto hinterlegten Benutzerdaten geändert, oder das Konto selbst wieder gelöscht werden.

#### Activity Kontoverwaltung ####
![03a_activity_kontoVerwalten.png](https://bitbucket.org/repo/BnRroj/images/1507695757-03a_activity_kontoVerwalten.png)
Der Prozess der Kontoverwaltung spielt sich schematisch dargestelt wie folgt ab:
Sofern noch kein entsprechendes Konto existiert, entscheidet sich der Benutzer eingangs für die Art der Registrierung. Möchte er ein Veranstalterkonto eröffnen, so muss zusätzlich noch ein Geschaftsnachweis erbracht werden. Dieser ist in Form einer Kopie des Handelsregistereintrages, oder Gewerbescheins denkbar, wird im Zuge dieser Projektaufgabe aber via Checkbox implementiert. Ist ein Konto bereits vorhanden, kann direkt mit der Anmeldung fortgefahren werden.
Dieser Prozess ist 
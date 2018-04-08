Im mechanisierten Beweisen geometrischer Theoreme wie auch im Design von
Dynamischer Geometrie-Software (DGS) spielen Konstruktionsbeschreibungen in
Form von linearen Geometrieprogrammen (Geometric straight-line programs) eine
zentrale Rolle, wobei in DGS zusätzliche Layoutinformationen zu den einzelnen
geometrischen Objekten vorgehalten werden müssen.

Es gab über die Zeit mehrere Versuche, eine einheitliche Darstellungsform für
derartige Konstruktionsbeschreibungen zu vereinbaren, darunter das im Rahmen
eines [gemeinsamen EU-Projekts](http://i2geo.net/?language=de) in den Jahren
2007-2010 entwickelte [Intergeo-Format](http://svn.activemath.org/intergeo/).

Parallel dazu liegen mit den
[GeoProofSchemes](https://symbolicdata.github.io/Geo) des
[Symbolicdata-Projekts](https://symbolicdata.github.io/) "generische"
Konstruktionsbeschreibungen vor, die als Eingaben für das mechanisierte
Geometriebeweisen dienen und teilweise in ein Format überführt wurden, das auch
für den Import in DGS prinzipiell geeignet ist, wenn die freien Parameter
angemessen belegt werden.  Dies lässt sich für verschiedene Typen der
GeoProofSchemes mit den aktuell verfügbaren DGS-Konzepten verschieden genau
realisieren.

Mit der Javascript basierten DGS [JSXGraph](https://jsxgraph.org) liegt
zugleich eine Abspielumgebung vor, die
* quelloffen bei [github](https://github.com/jsxgraph/jsxgraph) gehostet wird
* und behauptet, Konstruktionsbeschreibungen in verschiedenen Formaten einlesen
zu können.


Im Praktikum sollen

(1) für die DGS [GeoGebra](https://www.geogebra.org/),
[Cinderella](https://www.cinderella.de) sowie [Zirkel und
Lineal](http://zul.rene-grothmann.de) die entsprechenden Eingabeformate
analysiert und jeweils eine Datenbasis mit verschiedenen Beispielen aufgebaut
werden (Abschluss mit Seminarvortrag und Beispielsammlung im Repo),

(2) Transformationen der Eingabeformate zum Intergeo-Format entwickelt werden
  (proof of concept: Ladbarkeit der Beschreibungen in JSXGraph)

(3) GeoProofSchemes für den Upload in JSXGraph aufbereitet werden (Hierfür muss
das GeoProofScheme-Format ggf. modifiziert werden).

Dazu werden verschiedene Teams gebildet, die sich zunächst mit folgenden Fragen
befassen:

* Studium des Formats und Aufbau einer Datenbasis für
  [GeoGebra](https://www.geogebra.org/)
* Studium des Formats und Aufbau einer Datenbasis für
  [Cinderella](https://www.cinderella.de)
* Studium des Formats und Aufbau einer Datenbasis für [Zirkel und
Lineal](http://zul.rene-grothmann.de)
* Studium des Formats und Aufbau einer Datenbasis im Intergeo-Format und
  Demonstration mit JSXGraph.

In einer zweiten Etappe werden auf der Basis der erreichten Ergebnisse die
Fragen (2) und (3) in Angriff genommen. 

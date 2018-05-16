# GeoProofSchemes

138 von 297 Beispielen sind konstruktiv. Alles wird aus Punkten
konstruiert. 

Dynamische Elemente:
* Point(x,y)  - freier Punkt
* varpoint(A,B,u) - Geradengleiter auf Gerade durch zwei gegebene Punkte
* line_slider(a,u) - Geradengleiter auf Gerade ohne zwei Punkte
* circle_slider(M,A,u) - Gleiter auf Kreis um M mit A auf der Peripherie

Typen:
* Point
* Distance (eigentlich auch Scalar)
* Scalar
* Line
* Circle
* Boolean
* Angle

Die Konstruktionswerkzeuge sind in der Datei GeoCode.ttl im Turtle-Format
beschrieben.

Das Format der GeoProofSchemes hat sich über die Zeit mehrfach geändert und
kann ein weiteres Mal geändert werden, um näher an der Intergeo-Notation zu
sein.  Die Intergeo-Notation sieht allerdings keine Belegung mit Variablen vor,
die müsste hier noch geeignet eingebaut werden. Das bleibt weiter zu
diskutieren.

Außerdem muss geklärt werden, wie sich die geometrische Behauptung sinnvoll
visualisieren lässt: Diese ist in (fast) allen Fällen der Aufruf einer
booleschen Funktion is_*[...]. Zur Visualisierung könnten die Aufrufparameter
hergenommen werden und daraus geeignete geometrische Objekte erzeugt und
speziell farblich hervorgehoben werden.  

# GeoGebra

Speicherformat und Zusammenhang zu JSXGraph ist in 
- Peter Wilfahrt: Geogebra in JSXGraph. Hausarbeit, Bayreuth 2009
genauer beschrieben. Download unter `Texte/GeogebraInJSXGraph.pdf`.

Siehe auch `examples/ggb/praesentation/ggb-in-jxg.pdf` im jsxgraph Repo. 

Typische Strukturelemente (Elementname, Attribute)
* tag "element": Attribute type="point", label - Punkte als elementare Objekte
  * coords x,y,z=1
  * animation -> wenn beweglicher Punkt

* tag "command": Attribute name="Line", label
  * input a0, a1, a2 ...
  * output a0, a1, a2, ...

* Nach jedem tag "command" ein tag "element", welches die Style-Eigenschaften
  weiter beschreibt.
  * coords x,y,z  - Koordinatenattribut
  * eqnstyle="implicit"

* command names: Line, Midpoint, Segment, OrthogonalLine, Intersect, 

* name="Circle" type="conic"
  * eigenvectors x0,y0,z0, x1,y1,z1
  * matrix A0..A5

**Gruppe 1** präsentiert eine Lösung, mit der eine ggb-Datei geparst und als
JSXGraph-Bild visualisiert wird. Verwendet die Datei `src/reader/geogebra.js`
aus dem jsxgraph Repo zum Parsen der ggb-Datei.

Wurde auf einigen Beispielen ausprobiert, eine systematische Auswertung der
Beispiele im Verzeichnis GeoGebra-v3 steht noch aus.

Ein möglicher Weg zur Visualisierung von (konstruktiven) GeoProofSchemes wäre
also die Verwandlung eines solchen ProofSchemes in eine ggb-Datei und dann
diese über das Werkzeug anzeigen lassen.

**Aufgabe:** Setze das für 10 konstruktive GeoProofSchemes um. 

# Cinderella

Cinderella verwendet eine an klassischen OO-Notationen orientierte
Skriptsprache.

**Gruppe 2** liest die Konstruktion in eine interne Datenstruktur, erzeugt
daraus eine Intergeo-Datei. Das scheint aber ein intermediäres Produkt zu sein,
denn am Ende von `main.py` wird die Datei `JS/geocheck.js` geschrieben, die
direkten Code für eine jxbox erzeugt, die in einem JSX-Board visualisiert
werden kann. 

Der Ansatz ist vielversprechend, weil man damit auch andere Ausgaben
(JessieCode, Intergeo-Code) erzeugen könnte.

Kann man mit diesem Ansatz (konstruktive) GeoProofSchemes in einen
(erweiterten) Intergeo-Code transformieren und dann auch mit JSXGraph
visualisieren oder auch direkt in JSXGraph visualisieren? 

**Aufgabe:** Setze das für 10 konstruktive GeoProofSchemes um. 

# Zirkel und Lineal

Rückgabetyp von Konstruktionswerkzeugen wird nicht ausgezeichnet.

Alle Parameter werden als Attribute übergeben, damit keine Reihenfolge,
sondern Unterscheidung der Aufrufparameter durch Attributnamen. Im Textbereich
des Elements wird eine menschenlesbare Beschreibung mitgeliefert.

* Line - from, to - pp_line
* Midpoint - first, second - midpoint
* Plumb - point, line - ortho_line
* Intersection - first, second 
   - überladen als Schnitt zweier Objekte
   - Geraden: intersection_point
   - Kreise: Zusatzattribut which (first, second)
* Segment - from, to - Strecke
* Circle - through, midpoint - pc_circle

Konstruktionswerkzeuge haben weitere allgemeine Attribute
  - name - ein eindeutiger Bezeichner, der zur weiteren Referenzierung
    verwendet wird.
  - type - thin (bei Line)
  - shape - circle/diamond (Aussehen des Punkts)
  - hidden - true/false (bei Segment)
  - valid - true/false (bei Plumb) 
  - acute - true/false (bei Circle) 

**Gruppe 3** hat einige Beispiele analysiert. Visualisierung von Beispielen aus
ZuL (siehe das entsprechende Verzeichnis) könnte im Framework des Praktikums
über eine Transformation in das Intergeo-Format und Verwendung des
Intergeo-Visualisierers erfolgen.

**Aufgabe:** Transformiere die im ZuL-Format gegebenen
Konstruktionsbeschreibungen aus dem Verzeichnis ZuL in das Intergeo-Format und
visualisiere das mit den Techniken der Gruppe 4.

# Intergeo

Das Format ist genauer in
- The Intergeo Consortium: i2g Common File Format Specification. (2008)
beschrieben, der Text ist unter `Texte/CommonFileFormatSpecification.pdf` zu 
finden. 
* Beschreibt den genaueren Aufbau von Intergeo-Dateien.
* Unklar bleibt die genaue Syntaxbeschreibung der Konstruktionswerkzeuge.

zip-File mit den Verzeichnissen
* construction - enthält `intergeo.xml` sowie mglw. mehrere previews
* resources - enthält Bilddateien und anderes, das in construction referenziert
  wird 
* metadata (im LOM format)
* private (private Dateien des jeweiligen CAS)

`intergeo.xml` besteht aus den Sektionen elements, constraints und
(mglw. keiner oder auch mehreren Sektionen) display.

Die Beschreibung des Formats von intergeo.xml ist im XSchema intergeo.xsd
enthalten, die Datei war aber bisher nicht (mehr) aufzufinden.

"Weiche" Randbedingungen:
* Alle verwendeten geometrischen Objekte müssen im elements-Teil vereinbart
  werden.
* Parameter können nur geometrische Objekte oder Konstanten sein (GLP in ihrer
  strengen Form)
* Ausgabeparameter dürfen nur einmal verwendet werden.

Bisher wurde keine Datei gefunden, in welcher die einzelnen
Konstruktionswerkzeuge alle beschrieben sind, siehe aber die Wikiseiten
* http://i2geo.net/xwiki/bin/view/I2GFormat/
* http://i2geo.net/xwiki/bin/view/I2GFormat/allFormatPages
* http://i2geo.net/xwiki/bin/view/I2GFormat/FileFormatSymbols

Konstruktionswerkzeuge sind in "File Format Specification" ab S. 20 genauer
beschrieben.

**Gruppe 4** verwendet den im jsxgraph Repo implementierten Workflow zur
Anzeige von Konstruktionen, die im Intergeo-Format in einer Datei vorliegen:
* Starte einen HTTP-Server auf Port 8000
* Öffne `examples/Intergeo/testcases.html` in einem Webbrowser. Benötigt
  * distrib/jsxgraph.css
  * distrib/prototype.js
  * src/loadjsxgraph.js
  * src/reader/intergeo.js 
  * loadJSXG.js - definiert onchange-Funktion loadJSXG() zur Auswertung der
    Select-Tabelle
* Mit `board = JXG.JSXGraph.loadBoardFromFile('jxgbox', fname , 'Intergeo')`
  wird vom File `fname` die Konstruktion in das JXG.Board `board` geladen.
* Das Board wird in ein div-Element mit id="jxbox" und class="jxbox" geladen
  und dort interaktiv angezeigt. 

Damit existiert eine einfache Möglichkeit, eine Konstruktion im Intergeo-Format
in JSXGraph einzuladen, wenn sie nur die generisch gegebenen
Konstruktionswerkzeuge verwendet.  Es bleibt zu klären, wie man das um weitere
Konstruktionswerkzeuge erweitern kann, wie sie etwa im GeoCode als Macros
(sd:defaultDefinition) gegeben sind.

Ein möglicher Weg zur Visualisierung von (konstruktiven) GeoProofSchemes wäre
also die Verwandlung eines solchen ProofSchemes in eine Intergeo-Datei und dann
diese über diesen Zugang anzeigen lassen.

**Aufgabe:** Setze das für 10 konstruktive GeoProofSchemes um. 

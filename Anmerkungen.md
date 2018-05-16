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

# GeoGebra

Speicherformat und Zusammenhang zu JSXGraph ist in 
  Peter Wilfahrt: Geogebra in JSXGraph. Hausarbeit, Bayreuth 2009
genauer beschrieben. Download unter Texte/GeogebraInJSXGraph.pdf

Siehe auch examples/ggb/praesentation/ggb-in-jxg.pdf im jsxgraph Repo. 

Typische Strukturelemente (Elementname, Attribute)
* tag element: Attribute type="point", label - Punkte als elementare Objekte
   coords x,y,z=1
   animation -> wenn beweglicher Punkt

* tag command: Attribute name="Line", label
   input a0, a1, a2 ...
   output a0, a1, a2, ...

     Nach jedem <command> ein <element>, welches die Style-Eigenschaften
     weiter beschreibt.
   
   coords x,y,z  - Koordinatenattribut
   eqnstyle="implicit"

command names: Line, Midpoint, Segment, OrthogonalLine, Intersect, 

name="Circle" type="conic"
  eigenvectors x0,y0,z0, x1,y1,z1
  matrix A0..A5

**Gruppe 1** präsentiert eine Lösung, mit der eine ggb-Datei geparst und als
JSXGraph-Bild visualisiert wird. Verwendet die Datei src/reader/geogebra.js aus
dem jsxgraph Repo zum Parsen der ggb-Datei.

Wurde auf einigen Beispielen ausprobiert, eine systematische Auswertung der
Beispiele im Verzeichnis GeoGebra-v3 steht noch aus.

Ein möglicher Weg zur Visualisierung von (konstruktiven) GeoProofSchemes wäre
also die Verwandlung eines solchen ProofSchemes in eine ggb-Datei und dann
diese über das Werkzeug anzeigen lassen.

# Cinderella

Verwendet eine an klassischen OO-Notationen orientierte Skriptsprache.

**Gruppe 2** liest die Konstruktion in eine interne Datenstruktur, erzeugt
daraus eine Intergeo-Datei. Das scheint aber ein intermediäres Produkt zu sein,
denn am Ende von main.py wird die Datei JS/geocheck.js geschrieben, die
direkten Code für eine jxbox erzeugt, die in einem JSX-Board visualisiert
werden kann. 

Der Ansatz ist vielversprechend, weil man damit auch andere Ausgaben
(JessieCode, Intergeo-Code) erzeugen könnte.

Kann man mit diesem Ansatz (konstruktive) GeoProofSchemes in einen
(erweiterten) Intergeo-Code transformieren und dann auch mit JSXGraph
visualisieren? 

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
  name - 
  type - "thin"
  shape - "circle"

**Gruppe 3** hat einige Beispiele analysiert. Visualisierung von Beispielen aus
ZuL (siehe das entsprechende Verzeichnis) könnte im Framework des Praktikums
über eine Transformation in das Intergeo-Format und Verwendung des
Intergeo-Visualisierers erfolgen.

# Intergeo

Das Format ist genauer in
  The Intergeo Consortium: i2g Common File Format Specification. (2008)
beschrieben, der Text ist unter Texte/CommonFileFormatSpecification.pdf zu
finden. 
* Beschreibt den genaueren Aufbau von Intergeo-Dateien.
* Unklar bleibt die genaue Syntaxbeschreibung der Konstruktionswerkzeuge.

zip-File mit den Verzeichnissen
* construction - enthält intergeo.xml sowie mglw. mehrere previews
* resources - enthält Bilddateien und anderes, das in construction referenziert wird
* metadata (im LOM format)
* private (private Dateien des jeweiligen CAS)

intergeo.xml besteht aus den Sektionen elements, constraints und
(mglw. keiner oder auch mehreren Sektionen) display.

Die Beschreibung des Formats von intergeo.xml ist im XSchema intergeo.xsd
enthalten, die Datei war aber bisher nicht (mehr) aufzufinden.

Alle verwendeten geometrischen Objekte müssen im elements-Teil vereinbart
werden. 


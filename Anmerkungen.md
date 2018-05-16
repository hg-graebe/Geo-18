

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

Typische Strukturelemente (Elementname, Attribute)

<element> type="point", label - Punkte als elementare Objekte
  coords x,y,z=1
  animation -> wenn beweglicher Punkt

<command> name="Line", label
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

Gruppe 1 

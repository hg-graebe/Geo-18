# Intergeo Aufbau

Da weder eine *echte* Dokumentation zum Intergeo-Fromat noch eine `intergeo.xsd` aufzufinden war, wird nun im Folgenden ein Überblick zu vorhandenen Typen (*types*) und Konstruktionwerkzeugen (*constraints*) gegeben. Diese Informationen sind entnommen aus dem Paper [The Intergeo File Format in Progress](https://git.informatik.uni-leipzig.de/graebe/Geo-18/blob/master/Gruppe-4/doc/The%20Intergeo%20File%20Format%20in%20Progress.pdf) sowie [iG2 Common File Format Specification](https://git.informatik.uni-leipzig.de/graebe/Geo-18/blob/master/Gruppe-4/doc/i2g%20Common%20File%20Format%20Specification.pdf).

### Dead Links

* [HTML Dokumentation](http://i2geo.net/xwiki/bin/view/I2GFormat/FileFormatSymbols)
* [SVN Activemath](http://svn.activemath.org/intergeo/Format/)

## Elements (Typen)

Vergleich mit [iG2 Common File Format Specification](https://git.informatik.uni-leipzig.de/graebe/Geo-18/blob/master/Gruppe-4/doc/i2g%20Common%20File%20Format%20Specification.pdf) (Kapitel 4.2, S. 17-20).

* point (*point_coordinates*)
* line (*homogeneous_coordinates*)
* line_segment (*point_coordinates, point_coordinates [, point_coordinates]*)
* directed_line_segment (*point_coordinates, point_coordinates [, point_coordinates]*)
* ray (*point_coordinates, direction | point_coordinates [, point_coordinates]*)
* polygon (*list_of_vertices_coordinates*)
* vector (*point_coordinates*)
* conic (*matrix [, dualmatrix]*)
* circle (*matrix [, dualmatrix]*)
* ellipse (*matrix [, dualmatrix]*)
* parabola (*matrix [, dualmatrix]*)
* hyperbola (*matrix [, dualmatrix]*)
* locus (*void | a sef of points*)

### Families

* line_family = {line, ray, line_segment, direction_line_segment}
* circle_family = {circle, arc}
* conic_famitly = {circle, circle arc, conic, parabola, ellipse, hyperbola}
* element_family = {all elements}

### Auxiliary sumbols

* point_coordinates (*homogeneous_coordinates | euclidean_coordinates | polar_coordinates*)
  * homogeneous_coordinates (*scalar, scalar, scalar*)
  * euclidean_coordinates (*scalar, scalar*)
  * polar_coordinates (*scalar, scalar*)
* direction (*scalar, scalar [, scalar]*)
* list_of_vertices_coordinates (*point\**)
* list_of_vertices (*point_reference\**)

## Constraints (Konstruktionswerkzeuge)

Vergleich mit [iG2 Common File Format Specification](https://git.informatik.uni-leipzig.de/graebe/Geo-18/blob/master/Gruppe-4/doc/i2g%20Common%20File%20Format%20Specification.pdf) (Kapitel 4.2, S. 20-26).

* free_point
* free_line
* point_on_line
* point_on_line_segment
* point_on_circle
* line_through_point
* line_through_two_points
* line_angular_bisector_of_three_points
* line_angular_bisector_of_two_points
* line_segment_by_points
* directed_line_segment_by_points
* ray_from_point_and_vector
* ray_from_point_through_point
* line_parallel_to_line_through_point
* line_perpendicular_to_line_through_point
* point_intersection_of_two_lines
* midpoint_of_two_lines
* midpoint_of_line_segment
* endpoints_of_line_segment
* carry_line_of_line_segment
* starting_point_of_directed_line_segment
* end_point_of_directed_line_segment
* line_segment_of_directed_line_segment
* vector_of_ray
* starting_point_of_ray
* carrying_line_of_ray
* circle_by_center_and_radius
* circle_by_center_and_point
* circle_by_three_points
* intersection_points_of_two_circles
* other_intersection_point_of_two_circles
* intersectrions_points_of_circle_and_line
* other_intersection_points_of_circle_and_line
* intersection_points_of_two_conics
* intersection_points_of_conic_and_line
* other_intersection_points_of_conic_and_line
* circle_tangent_lines_by_point
* foci_of_conic
* center_of_circle
* locus_defined_by_point_on_line
* locus_defined_by_point_on_line_segment
* locus_defined_by_point_on_circle
* locus_defined_by_point_on_locus
* locus_defined_by_line_through_point
* symmetry_by_point
* symmetry_by_line
* symmetry_by_circle
* translate

## Display

Vergleich mit [iG2 Common File Format Specification](https://git.informatik.uni-leipzig.de/graebe/Geo-18/blob/master/Gruppe-4/doc/i2g%20Common%20File%20Format%20Specification.pdf) (Kapitel 4.2, S. 26f).

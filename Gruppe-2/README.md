# CIPS
**C**inderella - **I**ntergeo - Geo<b>P</b>roof<b>S</b>cheme

CIPS is a Java application for conversion [Cinderella](https://www.cinderella.de) and [GeoProofSchemes](https://symbolicdata.github.io/Geo) format to [Intergeo](http://i2geo.net/) format.
CIPS also offers a simple visualization of the above formats by means of [JSXGraph](https://jsxgraph.org) (for more details see [*cips.pdf*](https://git.informatik.uni-leipzig.de/graebe/Geo-18/blob/master/Gruppe-2/cips.pdf)).

# Build CIPS
Prerequisites: [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), [Maven](https://maven.apache.org/)

```
git clone git@git.informatik.uni-leipzig.de:graebe/Geo-18.git
cd Geo-18/Gruppe-2/cips
mvn clean install
```

# Usage
```
java -jar cips.jar <mode (optional)> <input file> <output file (optional)> -p [default parameter file]
java -jar cips.jar -c2i <cinderella file path> <intergeo file path>
java -jar cips.jar -g2i <geoproofscheme file> <intergeo file> -p [default parameter file]
java -jar cips.jar -vc  <cinderella file> <visualization file>
java -jar cips.jar -vi  <intergeo file> <visualization file>
java -jar cips.jar -vg  <geoproofscheme file> <visualization file> -p [default parameter file]
example:
java -jar cips.jar -vg Gruppe-2/testdata/Chou.28_1.xml Gruppe-2/jsx_Chou_test.html

 -c2i                   cinderella to intergeo
 -g2i                   geoproofscheme to intergeo
 -h,--help              print this message
 -i,--input <arg>       input file path
 -o,--output <arg>      output file path
 -p,--parameter <arg>   default parameter file path
 -vc                    cinderella visualisation with jsxgraph
 -vg                    geoproofscheme visualisation with jsxgraph
 -vi                    intergeo visualisation with jsxgraph
```

# Examples
*Test data found in folder [testdata](https://git.informatik.uni-leipzig.de/graebe/Geo-18/tree/master/Gruppe-2/testdata)*
* Multimode GeoProofScheme: conversion to Intergeo format & jsxGraph visualisation
(automatically sets output and parameter file; in the source directory)
```
java -jar cips.jar geoproofscheme_test.xml
```
* Convert GeoProofScheme format to Intergeo format
```
java -jar cips.jar -g2i geoproofscheme_test.xml intergeo_from_geoproofscheme.xml -p defaultparameters.txt
```
* Convert Cinderella format to Intergeo format
```
java -jar cips.jar -c2i cinderella_test.cdy intergeo_from_cinderella.xml
```
* Visualize GeoProofScheme format
```
java -jar cips.jar -vg geoproofscheme_test.xml geoproofscheme_test.html -p defaultparameters.txt
```
* Visualize Intergeo format
```
java -jar cips.jar -vi intergeo_from_geoproofscheme.xml intergeo_from_geoproofscheme.html
```
* Visualize Cinderella format
```
java -jar cips.jar -vc cinderella_test.cdy cinderella_test.html
```

# Team

* Kevin Marco Shrestha (PL) - ks12dyle@studserv.uni-leipzig.de
* Duong Trung Duong - bss13ard@studserv.uni-leipzig.de
* Akber Sarchaddi
* Johannes Michael - jm85copi@studserv.uni-leipzig.de

# Next Sprint
21.06.2018
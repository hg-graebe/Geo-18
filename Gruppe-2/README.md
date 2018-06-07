# CIPS
**C**inderella - **I**ntergeo - Geo<b>P</b>roof<b>S</b>cheme

CIPS is a Java application for conversion [Cinderella](https://www.cinderella.de) and [GeoProofSchemes](https://symbolicdata.github.io/Geo) format to [Intergeo](http://i2geo.net/) format.
CIPS also offers a simple visualization of the above formats by means of [JSXGraph](https://jsxgraph.org). (for more details see *cips.pdf*)

# Build CIPS
Prerequisites: [Maven](https://maven.apache.org/)

```
git clone git@git.informatik.uni-leipzig.de:graebe/Geo-18.git
cd Geo-18/Gruppe-2/cips
mvn clean install
```

# Usage
```
java -jar cips.jar -j c2i -i <cinderella file> -o <intergeo file>
java -jar cips.jar -j g2i -i <geoproofscheme file> -o <intergeo file> -p [default parameter file]
java -jar cips.jar -j vc -i <cinderella file> -o <visualization file>
java -jar cips.jar -j vi -i <intergeo file> -o <visualization file>
java -jar cips.jar -j vg -i <geoproofscheme file> -o <visualization file> -p [default parameter file]

 -h,--help              print this message
 -i,--input <arg>       input file path
 -j,--job-type <arg>    "c2i": cinderella to intergeo,
                        "g2i": geoproofscheme to intergeo,
                        "vc":  cinderella visualisation with jsxgraph,
                        "vi":  intergeo visualisation with jsxgraph,
                        "vg":  geoproofscheme visualisation with jsxgraph
 -o,--output <arg>      output file path
 -p,--parameter <arg>   default parameter file path
```

# Examples
Test data found in folder testdata
* Convert GeoProofScheme format to Intergeo format
```
java -jar cips.jar -j g2i -i geoproofscheme_test.xml -o intergeo_from_geoproofscheme.xml -p defaultparameters.txt
```
* Convert Cinderella format to Intergeo format
```
java -jar cips.jar -j c2i -i cinderella_test.cdy -o intergeo_from_cinderella.xml
```
* Visualize GeoProofScheme format
```
java -jar cips.jar -j vg -i geoproofscheme_test.xml -o geoproofscheme_test.html -p defaultparameters.txt
```
* Visualize Intergeo format
```
java -jar cips.jar -j vi -i intergeo_from_geoproofscheme.xml -o intergeo_from_geoproofscheme.html
```
* Visualize Cinderella format
```
java -jar cips.jar -j vc -i cinderella_test.cdy -o cinderella_test.html
```

# Team

* Kevin Marco Shrestha (PL) - ks12dyle@studserv.uni-leipzig.de
* Duong Trung Duong - bss13ard@studserv.uni-leipzig.de
* Akber Sarchaddi
* Johannes Michael - jm85copi@studserv.uni-leipzig.de

# Next Sprint
21.06.2018
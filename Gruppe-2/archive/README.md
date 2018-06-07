Gruppe 2 soll für die DGS [Cinderella](https://www.cinderella.de) die
entsprechenden Eingabeformate analysieren, eine Datenbasis mit verschiedenen
Beispielen aufbauen, die Möglichkeit des Imports in JSXGraph untersuchen und
die Ergebnisse auf einem gemeinsamen Meeting im Praktikum vorstellen.

# Team

* Kevin Marco Shrestha (PL) - ks12dyle@studserv.uni-leipzig.de
* Duong Trung Duong - bss13ard@studserv.uni-leipzig.de
* Akber Sarchaddi
* Johannes Michael - jm85copi@studserv.uni-leipzig.de

# Info
* __C2I_Java__: Java Implementation of the Cinderella to Intergeo converter
* __CinderellaReaderPatch__: patch for reader/cinderella.js to make it compatible with the most recent version of Cinderella 2's .cdy save file format. Use the prepatched version in the TestWithJSXGraph folder or use the following commands:
    * `git clone https://github.com/jsxgraph/jsxgraph`
    * `cp CinderellaReaderPatch/cinderella.patch jsxgraph/src/reader/cinderella.patch` 
    * `cd jsxgraph/src/reader/` 
    * `patch < cinderella.patch` 
* __data__: data files
    * __intergeo__: intergeo files
    * __original__: zipped Cinderella 2 files, unchanged
    * __unpacked__: unzipped Cinderella 2 files
* __docs__: Documents related to the project
* __src__: Python 3 implementation fo the Cinderella to Intergeo converter as well as the direct to JavaScript solution
* __TestWithJSXGraph__: Visualisation of the above project components
    * It is required to relax the Cross Origin Resource Sharing (CORS) security limitations of your browser when opening the .html files, or:
    * Use a webserver, example:
        * `cd TestWithJSXGraph` 
        * `python -m SimpleHTTPServer 8000`

# Organisatorisch
21.07.2018 - next sprint
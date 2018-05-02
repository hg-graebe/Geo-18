from src.helperFunctions import *
from src.documentGeneration import *
from src.cindyParser import *
import webbrowser, os


def main():
    print(timestamp() + "Starting")

    unzip("../data/original/test2.cdy", "../data/unpacked")
    unzip("../data/original/construction.cdy", "../data/unpacked")

    readFile("../data/unpacked/construction.cdy/private/de.cinderella/construction.cdy")
    writeFile("JS/geoCheck.js")

    document = generateIntergeo(getPoints(), getLines(), getCircles(), getMidPoints(), getParallelLines())
    saveToFile(document, '../data/intergeo/generated.xml')

    # won't work anymore nor for everyone: XHR cross origin
    webbrowser.open('file://' + os.path.realpath('jsxgraph.html'))
    webbrowser.open('file://' + os.path.realpath('JS/GeoProving.htm'))

    print(timestamp() + "Done")


def generateIntergeo(points, lines, circles, midPoints, parallelLines):
    construction = etree.Element('construction')

    elements = etree.SubElement(construction, 'elements')
    constraints = etree.SubElement(construction, 'constraints')

    createSubElements(elements, constraints, points)
    createSubElements(elements, constraints, lines)
    createSubElements(elements, constraints, circles)
    createSubElements(elements, constraints, midPoints)
    createSubElements(elements, constraints, parallelLines)

    return construction


main()

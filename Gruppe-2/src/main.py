from src.helperFunctions import *
from src.documentGeneration import *
from src.cindyParser import *
import webbrowser, os


def main():
    print(timestamp() + "Starting")

    unzip("../data/original/test2.cdy", "../data/unpacked")

    document = generateIntergeo()
    saveToFile(document, '../data/intergeo/generated.xml')

    # webbrowser.open('file://' + os.path.realpath('jsxgraph.html'))

    print(timestamp() + "Done")


def cindyParser():
    print(timestamp() + "Starting2")

    unzip("../data/original/construction.cdy", "../data/unpacked/construction")
    readFile("../data/unpacked/construction/private/de.cinderella/construction.cdy")
    writeFile("JS/geoCheck.js")

    print(timestamp() + "Done2")


def generateIntergeo():
    construction = etree.Element('construction')

    elements = etree.SubElement(construction, 'elements')
    constraints = etree.SubElement(construction, 'constraints')

    createSubElement(elements, 'point')  # TODO: pass geometry object
    createSubElement(elements, 'circle')  # TODO: pass geometry object
    createSubElement(elements, 'line')  # TODO: pass geometry object

    return construction


main()
cindyParser()

import xml.etree.ElementTree as etree


def createHomogeneousCoordinates(parent, x, y, z):
    coords = etree.SubElement(parent, 'homogeneous_coordinates')

    xElem = etree.SubElement(coords, 'double')
    yElem = etree.SubElement(coords, 'double')
    zElem = etree.SubElement(coords, 'double')

    xElem.text = str(x)
    yElem.text = str(y)
    zElem.text = str(z)


def createMatrix(parent, array):
    matrix = etree.SubElement(parent, 'matrix')

    for i in array:
        double = etree.SubElement(matrix, 'double')
        double.text = str(i)


def createPoint(parent, thingObject):
    element = etree.SubElement(parent, 'point')
    element.set('id', 'A')

    createHomogeneousCoordinates(element, 1, 2, 3)

    return element


def createCircle(parent, thingObject):
    element = etree.SubElement(parent, 'circle')
    element.set('id', 'a')

    createMatrix(element, [1, 2, 3, 4, 5, 6, 7, 8, 9, 0, -1])

    return element


def createLine(parent, thingObject):
    element = etree.SubElement(parent, 'line')
    element.set('id', 't1')

    createHomogeneousCoordinates(element, 5, 2, 1)

    return element


def createSubElement(parent, element):
    # TODO: meaningful architecture
    if element == 'point':
        sub_element = createPoint(parent, element)
    if element == 'circle':
        sub_element = createCircle(parent, element)
    if element == 'line':
        sub_element = createLine(parent, element)

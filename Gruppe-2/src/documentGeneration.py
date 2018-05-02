import xml.etree.ElementTree as etree
from src.classes import *


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


def createConstraints(constraints, name, type, geoObject, points):
    nameElem = etree.SubElement(constraints, name)
    typeElem = etree.SubElement(nameElem, type)
    typeElem.set('out', 'true')
    typeElem.text = geoObject.getName()

    for point in points:
        referencePoint = etree.SubElement(nameElem, 'point')
        referencePoint.text = point.getName()


def createCircle(parent, constraints, geoObject):
    element = etree.SubElement(parent, 'circle')
    element.set('id', geoObject.getName())

    createMatrix(element, [1, 2, 3, 4, 5, 6, 7, 8, 9, 0, -1])

    createConstraints(constraints, 'circle_by_center_and_point', 'circle', geoObject, [geoObject.getPointA(), geoObject.getPointB()])

    return element


def createLine2D(parent, constraints, geoObject):
    element = etree.SubElement(parent, 'line')
    element.set('id', geoObject.getName())

    testpoint = geoObject.getPointA().getPoint3D()

    createHomogeneousCoordinates(element, testpoint.x, testpoint.y, testpoint.z)

    line_through_two_points = etree.SubElement(constraints, 'line_through_two_points')
    line = etree.SubElement(line_through_two_points, 'line')
    line.set('out', 'true')
    line.text = geoObject.getName()
    pointA = etree.SubElement(line_through_two_points, 'point')
    pointB = etree.SubElement(line_through_two_points, 'point')
    pointA.text = geoObject.getPointA().getName()
    pointB.text = geoObject.getPointB().getName()

    return element

def createPoint3D(parent, constraints, geoObject):
    element = etree.SubElement(parent, 'point')
    element.set('id', geoObject.getName())

    point3D = geoObject.getPoint3D()

    createHomogeneousCoordinates(element, point3D.x, point3D.y, point3D.z)

    free_point = etree.SubElement(constraints, 'free_point')
    point = etree.SubElement(free_point, 'point')
    point.set('out', 'true')
    point.text = geoObject.getName()

    return element


def createSubElements(parent, constraints, elements):
    for element in elements:
        if type(element) is Point:
            createPoint3D(parent, constraints, element)
        if type(element) is MidPoint:
            print('TODO') # createPoint3D(parent, element)
        if type(element) is Line:
            createLine2D(parent, constraints, element)
        if type(element) is ParallelLine:
            print('TODO') # createPoint3D(parent, element)
        if type(element) is Circle:
            createCircle(parent, constraints, element)

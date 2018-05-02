import zipfile
from src.classes import *

counter = 0

points = []
midpoints = []
lines = []
parallelLines = []
circles = []


def getPoints():
    return points


def getMidPoints():
    return midpoints


def getLines():
    return lines


def getParallelLines():
    return parallelLines


def getCircles():
    return circles


# read content of a file
def readFile(path):
    file = open(path, "r")
    lineArr = file.read().split('\n')
    for x in range(0, len(lineArr)):
        if (lineArr[x][:1] == '('):
            kind = lineArr[x].split('=')[1].split('(')[0]
            if (kind == 'FreePoint'):
                print('FreePoint \t' + initFreePoint(lineArr[x]))
            elif (kind == 'Mid'):
                print('MidPoint \t' + initMidPoint(lineArr[x]))
            elif (kind == 'Join'):
                print('Line \t\t' + initLine(lineArr[x]))
            elif (kind == 'Parallel'):
                print('ParallelLine \t' + initParallelLine(lineArr[x]))
            elif (kind == 'CircleMP'):
                print('Circle \t\t' + initCircle(lineArr[x]))
    print(len(points))
    print(len(midpoints))
    print(len(lines))
    print(len(parallelLines))
    print(len(circles))


def writeFile(path):
    file = open(path, "w+")
    file.write('var b = JXG.JSXGraph.initBoard("jxgbox",{boundingbox: [-5, 5, 5, -5], axis:true});\n')

    # declare all points in JSXGraph
    for x in range(0, len(points)):
        file.write('var p' + str(x) + ' = b.create("point",[' + str(points[x].getPoint2D().x) + ',' +
                   str(points[x].getPoint2D().y) + '], {name:"' + points[x].getName() + '",size: 4, face: "o"});\n')

    for x in range(0, len(midpoints)):
        file.write('var mp' + str(x) + ' = b.create("midpoint",[p' + str(
            findIndexOfPoint(midpoints[x].getPointA().getName())) + ', p' +
                   str(findIndexOfPoint(midpoints[x].getPointB().getName())) + '], {name:"' + points[
                       x].getName() + '",size: 4, face: "o"});\n')

    for x in range(0, len(lines)):
        file.write('var l' + str(x) + ' = b.create("line",["' + lines[x].getPointA().getName() + '","' + lines[
            x].getPointB().getName() +
                   '"], {strokeColor:"#00ff00",strokeWidth:2});\n')

    for x in range(0, len(parallelLines)):
        file.write('var pl' + str(x) + ' = b.create("parallel",["' +
                   parallelLines[x].getLine().getName() + '","' + parallelLines[
                       x].getPoint().getName() + '"], {strokeColor:"#00ff00",strokeWidth:2});\n')

    for x in range(0, len(circles)):
        file.write('var c' + str(x) + ' = b.create("circle",["' +
                   circles[x].getPointA().getName() + '","' + circles[
                       x].getPointB().getName() + '"], {strokeColor:"#00ff00",strokeWidth:2});\n')


def findIndexOfPoint(name):
    for x in range(0, len(points)):
        if (points[x].getName() == name):
            return x


def findIndexOfLine(name):
    for x in range(0, len(lines)):
        if (lines[x].getName() == name):
            return x


# Initialize FreePoint and add them to the list "points"
def initFreePoint(line):
    name = line.split('"')[1]
    coord = line.split('[')[1].split(']')[0].split(',')
    x = coord[0]
    y = coord[1]
    z = coord[2]
    p = Point(name, x, y, z)
    points.append(p)
    return p.getName()


# Initialize Mid Point
def initMidPoint(line):
    name = line.split('"')[1]
    refs = line.split('(')[2].split(')')[0]
    refA = refs.split('"')[1]
    refB = refs.split('"')[3]
    pA = points[findIndexOfPoint(refA)].getPoint2D()
    pB = points[findIndexOfPoint(refB)].getPoint2D()
    p = MidPoint(name, pA, pB)
    midpoints.append(p)
    return p.getName()


def initLine(line):
    name = line.split('"')[1]
    pointNames = line.split('(')[2].split(')')[0].split('"')
    aPointName = pointNames[1]
    pointA = points[findIndexOfPoint(aPointName)]
    bPointName = pointNames[3]
    pointB = points[findIndexOfPoint(bPointName)]

    l = Line(name, pointA, pointB)
    lines.append(l)

    return l.getName()


def initParallelLine(line):
    name = line.split('"')[1]

    # Define dependant Point
    pointName = line.split('(')[2].split(')')[0].split('"')[3]
    point = points[findIndexOfPoint(pointName)]

    # Define dependant Line
    lineName = line.split('(')[2].split(')')[0].split('"')[1]
    line = lines[findIndexOfLine(lineName)];
    parallelLine = ParallelLine(name, line, point)
    parallelLines.append(parallelLine)
    return parallelLine.getName()


def initCircle(line):
    name = line.split('"')[1]

    pNames = line.split('(')[2].split(')')[0]
    pNameA = pNames.split('"')[1]
    pNameB = pNames.split('"')[3]

    pointA = points[findIndexOfPoint(pNameA)]
    pointB = points[findIndexOfPoint(pNameB)]

    circle = Circle(name, pointA, pointB)
    circles.append(circle)
    return circle.getName()


def createJSX(file, p):
    file.write('var p' + counter)

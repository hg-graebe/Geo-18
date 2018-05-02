import sympy as sp


class Point:
    def __init__(self, n, x, y):
        self.name = n
        self.point = sp.Point2D(x, y)

    def getName(self):
        return self.name

    def getPoint2D(self):
        return self.point


class MidPoint:
    def __init__(self, n, pA, pB):
        self.name = n
        self.pointA = pA
        self.pointB = pB
        self.point = pA.midpoint(pB)

    def getName(self):
        return self.name

    def getPointA(self):
        return self.pointA

    def getPointB(self):
        return self.pointB

    def getPoint(self):
        return self.point


class Line:
    def __init__(self, n, pA, pB):
        self.name = n
        self.pointA = pA
        self.pointB = pB
        self.line = sp.Line2D(pA.getPoint2D(), pB.getPoint2D())

    def getName(self):
        return self.name

    def getPointA(self):
        return self.pointA

    def getPointB(self):
        return self.pointB

    def getPoints(self):
        return self.line.points

    def getLine(self):
        return self.line


class ParallelLine:
    def __init__(self, n, l, p):
        self.name = n
        self.point = p
        self.line = l
        self.parallelLine = l.getLine().parallel_line(p.getPoint2D())

    def getName(self):
        return self.name

    def getPoint(self):
        return self.point

    def getLine(self):
        return self.line


class Circle:
    def __init__(self, n, pA, pB):
        self.name = n
        self.pointA = pA
        self.pointB = pB
        self.circle = sp.Circle(pA.getPoint2D(), pA.getPoint2D().distance(pB.getPoint2D()))

    def getName(self):
        return self.name

    def getPointA(self):
        return self.pointA

    def getPointB(self):
        return self.pointB

    def getCircle(self):
        return self.circle

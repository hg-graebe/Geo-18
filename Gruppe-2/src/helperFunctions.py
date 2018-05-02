import datetime
import zipfile
import xml.etree.ElementTree as etree
from xml.dom import minidom


class bcolors:
    HEADER = '\033[95m'
    OKBLUE = '\033[94m'
    OKGREEN = '\033[92m'
    WARNING = '\033[93m'
    FAIL = '\033[91m'
    ENDC = '\033[0m'
    BOLD = '\033[1m'
    UNDERLINE = '\033[4m'


def timestamp():
    return bcolors.HEADER + str(datetime.datetime.now().strftime("%d %B %Y %H:%M:%S.%f")) + " " + bcolors.ENDC


def unzip(path, destination_folder):
    print(timestamp() + 'Unpacking: ' + bcolors.OKGREEN + path + bcolors.ENDC)
    temp = path.split("/")
    zip_ref = zipfile.ZipFile(path, 'r')
    zip_ref.extractall(destination_folder + "/" + temp[len(temp) - 1])
    zip_ref.close()


def prettify(elem):
    """Return a pretty-printed XML string for the Element.
    """
    rough_string = etree.tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="  ")


def saveToFile(document, path):
    print(timestamp() + "Saving following document to: " + bcolors.OKGREEN + path + bcolors.ENDC + "\n")
    print(prettify(document))

    file = open(path, 'w')
    file.write(prettify(document))
    file.close()

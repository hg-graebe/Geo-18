import zipfile


def unzip(path, destinationFolder):
    temp = path.split("/")
    zip_ref = zipfile.ZipFile(path, 'r')
    zip_ref.extractall(destinationFolder + "/" + temp[len(temp)-1])
    zip_ref.close()

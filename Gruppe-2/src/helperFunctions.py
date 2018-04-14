import zipfile


def unzip(path, destinationFolder):
    zip_ref = zipfile.ZipFile(path, 'r')
    zip_ref.extractall(destinationFolder)
    zip_ref.close()

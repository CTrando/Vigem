from PIL import Image
import os, pprint

os.chdir("C:\\Users\Cameron\\Desktop\\LibGDXGames\\Vigem\\rawassets")

#Storage for the filtered files
desired_files =[]

# @param-endswith checks if the word ends with the string
# @param-filter is the string we are searching for
def filterFiles(files, filter, endswith=""):
    list = []
    for file in files:
        file_name = str(file)
        if filter in file_name and file_name.endswith(endswith):
            list.append(file_name)
    return list

def swap_pixels(image, xy, uv):
    temp_pixel = image.getpixel(xy)
    image.putpixel(xy, image.getpixel(uv))
    image.putpixel(uv, temp_pixel)

#0,0 is in the top left hand corner
def reverse_image(image):
    for x in range(0, int(image.width/2)):
        for y in range(0, int(image.height)):
            swap_pixels(image, (x, y), (image.width-x-1, y))

for root, dirs, filepaths in os.walk(os.getcwd()):
    desired_files = filepaths

desired_files = filterFiles(desired_files, ".png")
pprint.pprint(desired_files)
desired_files = filterFiles(desired_files, "right", endswith=".png")
pprint.pprint(desired_files)

for file in desired_files:
    image = Image.open(file)
    reverse_image(image)
    file = file.replace("right", "left")
    image.save(file, "PNG")


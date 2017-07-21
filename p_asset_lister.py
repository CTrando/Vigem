import os
os.chdir("C:\\Users\\Cameron\\Desktop\\LibGDXGames\\Vigem\\core\\assets")

current_dir = os.path.join(os.getcwd(), "asset_list.txt")

current_file = open(current_dir, "w")

for root, dirs, files in os.walk(os.getcwd()):

    for file in files:
        if file.endswith("atlas"):
            current_file.write(file+"\n")


current_file.close()

current_file = open(current_dir, "r")
print(current_file.read())
current_file.close()


    

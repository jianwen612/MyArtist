import os
import cv2
files =  os.listdir("./")
print(files)
for file in files:
	if file.endswith(".jpg"):
		img = cv2.imread(file)
		img = cv2.resize(img,(400,250),interpolation=cv2.INTER_CUBIC)
		cv2.imwrite('reshape_'+file,img)
		# print(img.shape)
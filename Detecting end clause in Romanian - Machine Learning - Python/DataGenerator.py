from __future__ import division
import csv

def dataGenerator(inputfileDataset, inputfileLabels, inputfileAbbreviations, csvFile):

	#read abbreviation list
	with open(inputfileAbbreviations,  encoding="utf8") as f:
		abbreviation = f.read().splitlines()

	#read labels for points
	with open(inputfileLabels,  encoding="utf8") as f:
		pointLabels = f.read().splitlines()


	#view already labeled points
	noOfLabels = len(pointLabels)


	#declaration for sentence reader
	filein = open(inputfileDataset, "r+")

	#create objects for write to csv
	fileOutCSV = open(csvFile, 'w+', newline='')
	writerCSV = csv.writer(fileOutCSV)

	##put the name of features in table
	myList = ['urmEOLN', 'isWeb', 'letEnd', 'predPresc', 'uppAftSpace', 'shortWords', 'frLsPoint', 'frLsUpper', 'prevNum', 'ellipsisLow', 'ellipsisHighEol', 'class']
	writerCSV.writerow(myList)
	myList.clear()

	contor = 0
	ind = 0

	lastPozI = 0

	lastUpper = 0

	for line in filein:
		for i, character in enumerate(line):
			if character == '.':

				contor += 1
				isabbrev = False
				isabbreviation = False

				isWeb = False
				isEndingLetter = False

				if contor <= noOfLabels:

					ind += 1

					#urmEOLN -> next char is an eoln
					if len(line) > i+1 and line[i + 1] == '\n':
						myList.append('Y')
					else:
						myList.append('N')


					#isWeb
					if i > 2 and line[i-3]=='w' and line[i-2]=='w' and line[i-1]=='w':
						myList.append('Y')
						isWeb = True
					elif len(line) > i+3 and ((line[i+1]=='c' and line[i+2]=='o' and line[i+3]=='m') or (line[i+1]=='r' and line[i+2]=='o')):
						myList.append('Y')
						isWeb= True
					else:
						myList.append('N')

					#letEnd -> possible sentences end in letter
					if i > 1 and line[i-1].isalpha() and line[i-2] == ' ' and len(line) > i+1 and line[i+1]=='\n':
						myList.append('Y')
						isEndingLetter = True
					elif i > 1 and line[i-1].isalpha() and line[i-2] == ' ' and len(line) > i+2 and line[i+1] == ' ' and line[i+2].isupper():
						myList.append('Y')
						isEndingLetter = True
					else:
						myList.append('N')

					#predPresc -> previous word is an abbrevation
					try:
						words = line[:i].split(' ')
						abbreviation.index((words[len(words) - 1].lower()).lower())
						if isEndingLetter == False:
							isabbrev = True
							isabbreviation = True
					except ValueError:
						if isEndingLetter == False and isWeb == False and i > 0 and len(line) > i+1 and line[i-1].isalpha() and line[i+1].isalpha():
							isabbrev = True
							isabbreviation = True


					#unlAbb -> unlabeled abbreviations
					try:
						words = line[:i].split(' ')
						if isEndingLetter == False and len(words[len(words) - 1]) < 4 and len(line) > i+2 and line[i+1] == ' ' and line[i+2].islower():
							isabbreviation = True
						elif isEndingLetter == False and len(words[len(words) - 1]) < 4 and len(line) > i+1 and (line[i+1] == ',' or line[i+1] == ':' or line[i+1] == ';'):
							isabbreviation = True
					except ValueError:
						isabbreviation = False


					#nameAbbrev -> the letter before dot is a name abbreviation
					if (i > 0 and len(line) > i+1 and line[i-1].isupper() and line[i+1] == ' ') or isabbreviation == True:
						myList.append('Y')
						isabbrev = True
					else:
						myList.append('N')


					#uppAftSpace -> upper after space
					if isabbrev == False and i > 0 and len(line) > i+2 and line[i-1] != '.' and line[i+1]==' ' and line[i+2].isupper():
						myList.append('Y')
					else:
						myList.append('N')


					#shortWords
					try:
						words = line[:i].split(' ')
						if len(words[len(words) - 1]) < 4:
							myList.append('Y')
						else:
							myList.append('N')
					except ValueError:
						myList.append('N')


					#frLsPoint -> distance from last point
					if i-lastPozI >= 10:
						myList.append('Y')
					else:
						myList.append('N')


					#frLsUpper -> distance from last uppercase
					if i-lastUpper >= 3:
						myList.append('Y')
					else:
						myList.append('N')


					#prevNum -> prev character is a number
					if (i>0 and str(line[i - 1]).isdigit()) :
						myList.append('Y')
					else:
						myList.append('N')

					#ellipsisLow -> three dots followed by a word starting with lower
					if len(line) > i+4 and line[i+1] == '.' and line[i+2] == '.' and line[i+4].islower():
						myList.append('Y')
					elif len(line) > i+3 and i > 0 and line[i-1] == '.' and line[i+1] == '.' and line[i+3].islower():
						myList.append('Y')
					elif len(line) > i+2 and i > 1 and line[i-1] == '.' and line[i-2] == '.' and line[i+2].islower():
						myList.append('Y')
					else:
						myList.append('N')

					#ellipsisHighEol -> three dots followed by a word starting with upper or an endline
					if len(line) > i+4 and line[i+1] == '.' and line[i+2] == '.' and line[i+4].isupper():
						myList.append('Y')
					elif len(line) > i+3 and i > 0 and line[i-1] == '.' and line[i+1] == '.' and line[i+3].isupper():
						myList.append('Y')
					elif len(line) > i+2 and i > 1 and line[i-1] == '.' and line[i-2] == '.' and line[i+2].isupper():
						myList.append('Y')
					elif len(line) > i+3 and line[i+1] == '.' and line[i+2] == '.' and line[i + 3] == '\n':
						myList.append('Y')
					elif len(line) > i+2 and i > 0 and line[i-1] == '.' and line[i+1] == '.' and line[i + 2] == '\n':
						myList.append('Y')
					elif len(line) > i+1 and i > 1 and line[i-1] == '.' and line[i-2] == '.' and line[i + 1] == '\n':
						myList.append('Y')
					else:
						myList.append('N')


					##class from labels
					myList.append(pointLabels[ind-1])

					writerCSV.writerow(myList)
					myList.clear()


					#last i followed by upper
					if (len(line) > i+2 and line[i+1] == ' ' and line[i+2].isupper()) or (len(line) > i+1 and line[i+1] == '\n'):
						lastPozI = i

				else:
					break

			if str(character).isalpha() and str(character).isupper():
				lastUpper = i

	filein.close()
	fileOutCSV.close()

	print("Successfull!")

def main():
	inputfileDataset = 'dataset.txt'
	inputfileLabels = 'labels.txt'
	inputfileAbbreviation = 'abrevieri.txt'
	csvFile = 'trainAndTestData.csv'
	dataGenerator(inputfileDataset, inputfileLabels, inputfileAbbreviation, csvFile)


if __name__ == "__main__":
	main()

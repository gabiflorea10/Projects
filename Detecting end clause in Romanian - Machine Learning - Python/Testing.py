import DataGenerator
from sklearn.preprocessing import LabelEncoder
import pickle
import pandas as pd

def main():

	DataGenerator.dataGenerator('newText.txt', 'labels.txt', 'abrevieri.txt', 'outcsv.csv')

	inputData = pd.read_csv('outcsv.csv', header=0)
	inputData.head()

	label = LabelEncoder()
	inputDataLabeled = inputData.apply(label.fit_transform)

	X = inputDataLabeled[inputDataLabeled.columns.drop('class')]

	loaderModel = pickle.load(open('model.sav', 'rb'))

	predictions = loaderModel.predict(X)

	contor = 0
	for pred in predictions:
		contor += 1
		if pred == 0:
			print('Point ', contor, ': N')
		else:
			print('Point ', contor, ': Y')


if __name__=="__main__":
		main()
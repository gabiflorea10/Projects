from sklearn.preprocessing import LabelEncoder
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.neighbors import KNeighborsClassifier
from sklearn.model_selection import train_test_split
from sklearn import linear_model
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
import pickle
import warnings

import pandas as pd

def dataTrainer(inputfileTrain, inputfileTest):

	dataSet = pd.read_csv(inputfileTrain, header=0)
	dataSet.head()

	label = LabelEncoder()
	dataLabeled = dataSet.apply(label.fit_transform)

	X = dataLabeled[dataSet.columns.drop('class')]
	y = dataLabeled['class']

	X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3)


	warnings.filterwarnings("ignore")


	###################################
	##Method 1 - logistic regression
	###################################

	print()
	print("Method 1 - Logistic regression")
	print()

	logreg = LogisticRegression(solver='liblinear', max_iter=1000, multi_class='ovr', verbose=True)
	logreg.fit(X_train, y_train)

	#showing train and test score
	print()
	print("Train score:")
	print(logreg.score(X_train, y_train))

	print()
	print("Test score:")
	print(logreg.score(X_test, y_test))
	print()


	###################################
	##Method 2 -K-Nearest Neighbors
	###################################

	print()
	print("Method 2 - K-Nearest Neighbors")
	print()

	knn = KNeighborsClassifier(weights='uniform', algorithm='auto', leaf_size=30)
	knn.fit(X_train, y_train)

	# showing train and test score
	print()
	print("Train score:")
	print(knn.score(X_train, y_train))

	print()
	print("Test score:")
	print(knn.score(X_test, y_test))
	print()


	###################################
	##Method 3 - Support vector machine
	###################################

	print()
	print("Method 3 - Support vector machine")
	print()

	svm = SVC(kernel='rbf', degree=3, max_iter=1000, decision_function_shape='ovr')
	svm.fit(X_train, y_train)

	print()
	print("Train score:")
	print(svm.score(X_train, y_train))

	print()
	print("Test score:")
	print(svm.score(X_test, y_test))
	print()


	###################################
	##Method 4 -Stochastic gradient descent
	###################################

	print()
	print("Method 4 -Stochastic gradient descent")
	print()

	clf = linear_model.SGDClassifier(loss='hinge', verbose=True, max_iter=1000)
	clf.fit(X, y)

	#showing train and test score
	print()
	print("Train score:")
	print(clf.score(X_train, y_train))

	print()
	print("Test score:")
	print(clf.score(X_test, y_test))
	print()



	###################################
	##Method 5 - multi-layer perceptron
	###################################

	print()
	print("Method 5 - multi-layer perceptron")
	print()


	#training the neural-network
	mlp = MLPClassifier(solver='adam', alpha=1e-5, verbose=True, hidden_layer_sizes=(100,), activation='relu',  max_iter=750)
	mlp.fit(X_train, y_train)

	#showing train and test score
	print()
	print("Train score:")
	print(mlp.score(X_train, y_train))

	print()
	print("Test score:")
	print(mlp.score(X_test, y_test))
	print()

	#make predictions on test set
	predictions = mlp.predict(X_test)


	#Show classification report
	print()
	print("Classification report:")
	print(classification_report(y_test, predictions))

	print()

	#show predictions
	print("Predictions:")
	print(predictions)

	#show values of predictions
	print("Example of prediction proba: ")
	print(mlp.predict_proba(X_test))

	#show coefficients of neural-network

	#coeficients
	print()
	print("Show coefficients of neural-network:")
	print()
	print(mlp.coefs_)

	#coefficients shape
	print()
	print("Show coefficients shape of neural-network:")
	for coef in mlp.coefs_:
		print(coef.shape)


	#Confusion matrix
	print()
	print("Confusion matrix:")
	print(confusion_matrix(y_test, predictions))



	#save the mlp model
	modelName = 'model.sav'
	pickle.dump(mlp, open(modelName, 'wb'))




def main():
	inputfile = 'trainAndTestData.csv'
	inputfileTest = 'newTextFeatures.csv'
	dataTrainer(inputfile, inputfileTest)


if __name__ == "__main__":
	main()
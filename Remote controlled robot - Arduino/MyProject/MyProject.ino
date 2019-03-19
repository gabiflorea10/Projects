#include <Wire.h> 

// Pinii motor 1
#define mpin00 5
#define mpin01 6

// Pinii motor 2
#define mpin10 10
#define mpin11 11

// defines pins numbers
const int trigPin = 9;
const int echoPin = 12;

// defines variables
long duration;
int distance;

char x;

void setup() {

 Wire.begin(9);
  
 // Atasam o functie care sa se declanseze atunci cand primim ceva
 Wire.onReceive(receiveEvent); 
  
 analogReference(DEFAULT);
 digitalWrite(mpin00, 0);
 digitalWrite(mpin01, 0);
 digitalWrite(mpin10, 0);
 digitalWrite(mpin11, 0);
 
 pinMode (mpin00, OUTPUT);
 pinMode (mpin01, OUTPUT);
 pinMode (mpin10, OUTPUT);
 pinMode (mpin11, OUTPUT); 

pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
pinMode(echoPin, INPUT); // Sets the echoPin as an Input

pinMode(A5, INPUT);
digitalWrite(A5,HIGH);
pinMode(A4, INPUT);
digitalWrite(A4,HIGH);

Serial.begin(115200); // Starts the serial communication

}

void receiveEvent(int bytes) {
  
 x = Wire.read(); // citim un character din I2C

}
 

void loop() {

 Serial.print(x);

 if (x=='f') {
myForward(mpin00,mpin01,mpin10, mpin11);
 }
  if (x=='s') {
myStop(mpin00,mpin01,mpin10, mpin11);
 }
 if (x=='b') {
myBack(mpin00,mpin01,mpin10, mpin11);
 }
   if (x=='l') {
myLeft(mpin00,mpin01,mpin10, mpin11);
 }
 if (x=='r') {
myRight(mpin00,mpin01,mpin10, mpin11);
 }

 readDistance();
 if(distance < 10){
    myStop(mpin00,mpin01,mpin10, mpin11);
 }

}

void readDistance(){
  
digitalWrite(trigPin, LOW);
delayMicroseconds(2);
// Sets the trigPin on HIGH state for 10 micro seconds
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);
// Reads the echoPin, returns the sound wave travel time in microseconds
duration = pulseIn(echoPin, HIGH);
// Calculating the distance
distance= duration*0.034/2;
// Prints the distance on the Serial Monitor
Serial.print("Distance: ");
Serial.println(distance);
  
}

void myForward(int m1, int m2, int m3, int m4){

 digitalWrite(m2, 0);
 analogWrite (m1, 100);

 digitalWrite(m4, 0);
 analogWrite (m3, 100);

}

void myBack(int m1, int m2, int m3, int m4){
 
 digitalWrite(m1, 0);
 analogWrite (m2, 100);

 digitalWrite(m3, 0);
 analogWrite (m4, 100);

}

void myStop(int m1, int m2, int m3, int m4){
 
 digitalWrite(m2, 0);
 digitalWrite(m1, 0);
 digitalWrite(m3, 0);
 digitalWrite(m4, 0);

}

void myLeft(int m1, int m2, int m3, int m4){
 
 digitalWrite(m1, 0);
 analogWrite (m2, 0);

 digitalWrite(m4, 0);
 digitalWrite(m3, 80);


}

void myRight(int m1, int m2, int m3, int m4){


 digitalWrite(m2, 0);
 digitalWrite (m1, 80);

 digitalWrite(m3, 0);
 analogWrite (m4, 0);

}

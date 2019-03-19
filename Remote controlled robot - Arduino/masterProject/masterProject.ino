#define DEBUG true
#include <Wire.h>

void setup() {
  
 // Dechidem magistrala I2C ca master
Wire.begin();

// Starts the serial communications
Serial.begin(115200); 
Serial1.begin(115200); 

//Function calls for wifi module
sendData("AT+RST\r\n", 2000, false); // resetare modul
sendData("AT+CWMODE=2\r\n", 1000, false); // configurare ca access point
sendData("AT+CIFSR\r\n", 1000, DEBUG); // citeste adresa IP 
sendData("AT+CWSAP?\r\n", 2000, DEBUG); // citeste informatia SSID (nume retea)
sendData("AT+CIPMUX=1\r\n", 1000, false); // configurare conexiuni multiple
sendData("AT+CIPSERVER=1,80\r\n", 1000, false); // pornire server pe port 80

}

String sendData(String command, const int timeout, boolean debug) {
  
 String response = "";
 Serial1.print(command); // trimite comanda la esp8266
 long int time = millis();
 
 while ((time + timeout) > millis()) {
   while (Serial1.available()) {
   char c = Serial1.read(); // citeste caracterul urmator
   response += c;
   }
 }
 
 Wire.beginTransmission(9);
 
 if (response.indexOf("/f") != -1) {
 Wire.write('f'); 
 }
 
  if (response.indexOf("/s") != -1) {
 Wire.write('s'); 
 }
 
 if (response.indexOf("/b") != -1) {
 Wire.write('b'); 
 }

  if (response.indexOf("/l") != -1) {
 Wire.write('l'); 
 }
 
  if (response.indexOf("/r") != -1) {
 Wire.write('r'); 
 }
 
 Wire.endTransmission(); // oprim transmisia

 if (debug) {
 Serial.print(response);
 }
 return response;
 
}

void loop() {

 if (Serial1.available()) {
   if (Serial1.find("+IPD,")) {
     delay(500);
     int connectionId = Serial1.read() - 48;
     String webpage = "<h1>Gabi's car!</h1>";
     String cipSend = "AT+CIPSEND=";
     cipSend += connectionId;
     cipSend += ",";     
     cipSend += webpage.length();
     cipSend += "\r\n";
     sendData(cipSend, 100, DEBUG);
     sendData(webpage, 150, DEBUG);
    
     String closeCommand = "AT+CIPCLOSE=";
     closeCommand += connectionId; //se adauga identificatorul conexiunii
     closeCommand += "\r\n";
     sendData(closeCommand, 300, DEBUG);
   }
 }

}

#include <Adafruit_Sensor.h>
#include <ArduinoJson.h>
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>

#define FIREBASE_HOST "fir-ebf02.firebaseio.com"
#define FIREBASE_AUTH "2gZJ8SbHZXVg5WneHjnjd6nuUPLd29w8oL4ahaMo"
#define WIFI_SSID "drive"
#define WIFI_PASSWORD "damodar9157@"


#include <SoftwareSerial.h>
#include <DHT.h>
#include <DHT_U.h>
#include "DHT.h"        // including the library of DHT11 temperature and humidity sensor
#define DHTTYPE DHT11
#define dht_dpin 5 // temperature & humidity pin 
DHT dht(dht_dpin, DHTTYPE);
SoftwareSerial BTSerial(3, 1);//bluetooth pin

String UserbluetoothDeviceID = "00:21:13:04:1D:4E";

int UpperThreshold = 518;
int LowerThreshold = 490;
int reading = 0;
float BPM = 0.0;
bool IgnoreReading = false;
bool FirstPulseDetected = false;
unsigned long FirstPulseTime = 0;
unsigned long SecondPulseTime = 0;
unsigned long PulseInterval = 0;



void setup()

{
  Serial.begin(9600);
  BTSerial.begin(38400);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  delay(200);
  dht.begin();//DHT11sensore

}

void loop() {

  float h = dht.readHumidity();
  float t = dht.readTemperature();

  reading = analogRead(0); 

      // Heart beat leading edge detected.
      if(reading > UpperThreshold && IgnoreReading == false){
        if(FirstPulseDetected == false){
          FirstPulseTime = millis();
          FirstPulseDetected = true;
        }
        else{
          SecondPulseTime = millis();
          PulseInterval = SecondPulseTime - FirstPulseTime;
          FirstPulseTime = SecondPulseTime;
        }
        IgnoreReading = true;
      }

      // Heart beat trailing edge detected.
      if(reading < LowerThreshold){
        IgnoreReading = false;
      }  

      BPM = (1.0/PulseInterval) * 60.0 * 1000;

  Serial.print("current humidity = ");
  Serial.print(h);
  Serial.print("%  ");
  BTSerial.print(h);
  BTSerial.print(",");
  Serial.print("current temperature = ");
  Serial.print(t);
  Serial.println("C  ");
  BTSerial.print(t);
  BTSerial.print(";");
  Serial.print("current heartrate = ");
  Serial.print(BPM);
  BTSerial.print(BPM);
  BTSerial.print(";");

  //sending data to firebase real-time database
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();
  root["temperature"] = t;
  root["humidity"] = h;
  root["heartrate"] = BPM;
  String name = Firebase.push("/Device1/00:21:13:04:1D:4E", root);

}



#include <IRremote.h>

/*
 * IRremote: IRrecvDemo - demonstrates receiving IR codes with IRrecv
 * An IR detector/demodulator must be connected to the input RECV_PIN.
 * Version 0.1 July, 2009
 * Copyright 2009 Ken Shirriff
 * http://arcfn.com
 */

#include <IRremote.h>

#define RECV_PIN 13
#define LED_FORWARD_PIN 12
#define LED_REVERSE_PIN 10
#define LED_STOP_PIN 8
IRrecv irrecv(RECV_PIN);

decode_results results;

void setup()
{
  pinMode(LED_FORWARD_PIN, OUTPUT);
  pinMode(LED_REVERSE_PIN, OUTPUT);
  pinMode(LED_STOP_PIN, OUTPUT);
  irrecv.enableIRIn(); // Start the receiver
  Serial.begin(9600);
}
 
void loop() {
  if (irrecv.decode(&results)) {
    
    digitalWrite(LED_FORWARD_PIN, LOW);
    digitalWrite(LED_REVERSE_PIN, LOW);
    digitalWrite(LED_STOP_PIN, LOW);
    
    Serial.println(results.value, DEC);
    irrecv.resume(); // Receive the next value
    
    if (results.value==4239130558 | results.value==581859881){
      digitalWrite(LED_FORWARD_PIN, HIGH);
    }
    if (results.value==4272685796 | results.value==615415119){
      digitalWrite(LED_REVERSE_PIN, HIGH);
    }
    if (results.value==632192736 | results.value==4255908179){
      digitalWrite(LED_STOP_PIN, HIGH);
    }
    
  }
  //delay(100);
}

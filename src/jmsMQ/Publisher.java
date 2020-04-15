/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jmsMQ;


import java.util.LinkedList;

import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

/**
 * Uses a Future based API to MQTT.
 */
public class Publisher {

    public static void main(String []args) throws Exception {

        String user = env("ACTIVEMQ_USER", "admin");
        String password = env("ACTIVEMQ_PASSWORD", "password");
        String host = env("ACTIVEMQ_HOST", "localhost");
        int port = Integer.parseInt(env("ACTIVEMQ_PORT", "1883"));
        final String destination = arg(args, 0, "/topic/event");

        int messages = 10000;
        int size = 256;

        String DATA = "abcdefghijklmnopqrstuvwxyz";
        String body = "";
        for( int i=0; i < size; i ++) {
            body += DATA.charAt(i%DATA.length());
        }
       Buffer msg = new AsciiBuffer(body);

       MQTT mqtt = new MQTT();
       mqtt.setHost("tcp://localhost:61616");
       BlockingConnection connection = mqtt.blockingConnection();
       connection.connect();
      
        Topic[] topics = { new Topic("mqtt/example/publish", QoS.AT_LEAST_ONCE), new Topic("test/#", QoS.EXACTLY_ONCE), new Topic("foo/+/bar", QoS.AT_LEAST_ONCE) };
        connection.subscribe(topics);
        //FutureConnection connection = mqtt.futureConnection();
        //connection.connect().await();

        
        String payload1 = "This is message 1";
        String payload2 = "This is message 2";
        String payload3 = "This is message 3";

        connection.publish("mqtt/example/publish", payload1.getBytes(), QoS.AT_LEAST_ONCE, false);
        connection.publish("mqtt/test", payload2.getBytes(), QoS.AT_MOST_ONCE, false);
        connection.publish("foo/1/bar", payload3.getBytes(), QoS.AT_MOST_ONCE, false);
        
		/*
		 * final LinkedList<Future<Void>> queue = new LinkedList<Future<Void>>();
		 * UTF8Buffer topic = new UTF8Buffer(destination); for( int i=1; i <= messages;
		 * i ++) {
		 * 
		 * // Send the publish without waiting for it to complete. This allows us // to
		 * send multiple message without blocking.. queue.add(connection.publish(topic,
		 * msg, QoS.AT_LEAST_ONCE, false));
		 * 
		 * // Eventually we start waiting for old publish futures to complete // so that
		 * we don't create a large in memory buffer of outgoing message.s if(
		 * queue.size() >= 1000 ) { queue.removeFirst().await(); }
		 * 
		 * if( i % 1000 == 0 ) { System.out.println(String.format("Sent %d messages.",
		 * i)); } }
		 * 
		 * queue.add(connection.publish(topic, new AsciiBuffer("SHUTDOWN"),
		 * QoS.AT_LEAST_ONCE, false)); while( !queue.isEmpty() ) {
		 * queue.removeFirst().await(); }
		 */

        

        System.exit(0);
    }

    private static String env(String key, String defaultValue) {
        String rc = System.getenv(key);
        if( rc== null )
            return defaultValue;
        return rc;
    }

    private static String arg(String []args, int index, String defaultValue) {
        if( index < args.length )
            return args[index];
        else
            return defaultValue;
    }

}
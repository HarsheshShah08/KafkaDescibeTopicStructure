package com.example.demo;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.TopicDescription;

import java.util.List;
import java.util.Map;

public class KafkaDescribteTopicV3 {

	public static void main(String[] args) {

		try {
			
			Admin admin = Admin.create(
		            Map.of("bootstrap.servers", "localhost:9091,localhost:9092,localhost:9093")
		        );
	
		        Map<String, TopicDescription> topicsDescriptions =
		        	(Map<String, TopicDescription>) admin.describeTopics(List.of("MyFirstTopicForDemo")).all().get();
		            /*admin.describeTopics(List.of("MyFirstTopicForDemo"))
		            .all()
		            .get();*/
	
		        topicsDescriptions.forEach((name, description) -> printTopicDetails(name, description));
		    }catch(Exception e) {
				System.out.println(e.toString());
			}
		}

	    private static void printTopicDetails(String topicName, TopicDescription td) {
	        System.out.printf("%nTopic %s%n", topicName);
	        td.partitions()
	            .forEach(
	                p -> {
	                    System.out.printf("Partition: %d%n", p.partition());
	                    System.out.printf("  Leader: %s%n", p.leader());
	                    System.out.println("  Replicas:");
	                    p.replicas()
	                        .forEach( r -> {
	                            System.out.printf("    - %s%n", r);
	                        });
	                }
	            );
	    }


}



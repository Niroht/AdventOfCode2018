package adventofcodejava;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;

public class TreeReader {
	public static int sumMetadataEntries(List<Integer> input) {
		Node rootNode = buildNode(input);
		
		return rootNode.getMetaDataSumWithChildren();
	}
	
	public static int findRootNodeValue(List<Integer> input) {
		Node rootNode = buildNode(input);
		
		return rootNode.getValue();
	}
	
	private static Node buildNode(List<Integer> input) {
		Node node = new Node();
		
		int childNodeCount = input.get(0);
		
		if (childNodeCount == 0) {
			node.getMetadataEntries().addAll(input.subList(2, 2 + input.get(1)));
			
			return node;
		}
		
		List<Integer> potentialChildNodeEntries = input
				.subList(2, input.size());
		
		for(int i = 0; i < childNodeCount; i++) {
			Node childNode = buildNode(potentialChildNodeEntries);
			node.getChildren().add(childNode);
			
			potentialChildNodeEntries = potentialChildNodeEntries.subList(childNode.getElementCount(), potentialChildNodeEntries.size());
		}
		
		node.getMetadataEntries().addAll(potentialChildNodeEntries.subList(0, input.get(1)));
		
		return node;
	}
	
	private static class Node{
		private UUID id = UUID.randomUUID();
		
		private List<Integer> metadataEntries = new ArrayList<>();
		
		private List<Node> children = new ArrayList<>();
		
		public UUID getId() {
			return this.id;
		}
		
		public List<Node> getChildren(){
			return this.children;
		}
		
		public List<Integer> getMetadataEntries(){
			return this.metadataEntries;
		}
		
		public int getElementCount() {
			int count = 2 + metadataEntries.size();
			
			int childrenElementCount = children.stream().mapToInt(x -> x.getElementCount()).sum();
			
			return count + childrenElementCount;
		}
		
		public int getMetaDataSumWithChildren() {
			return metadataEntries.stream().mapToInt(x -> x).sum() + children.stream().mapToInt(x -> x.getMetaDataSumWithChildren()).sum();
		}
		
		public int getValue() {
			if (CollectionUtils.isEmpty(children)) {
				return metadataEntries.stream().mapToInt(x -> x).sum();
			}
			
			int sum = 0;
			
			for (int metadataEntry : metadataEntries) {
				if (metadataEntry == 0 || metadataEntry > children.size()) {
					continue;
				}
				
				int index = metadataEntry - 1;
				
				sum += children.get(index).getValue();
			}
			
			return sum;
		}
	}
}

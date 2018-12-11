package adventofcodejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class StepReader {
	public static String findOrderOfSteps(List<String> requirements) {
		Map<String, List<String>> stepsAndDependencies = parseStepsAndDependencies(requirements);
		
		Set<String> executedSteps = new LinkedHashSet<>();
		
		while (!stepsAndDependencies.isEmpty()) {
			Set<String> potentialSteps = stepsAndDependencies
					.entrySet()
					.stream()
					.filter(x -> CollectionUtils.isEmpty(x.getValue()))
					.map(Entry::getKey)
					.collect(Collectors.toSet());
			
			String stepToExecute = potentialSteps.stream().sorted().findFirst().get();
			executedSteps.add(stepToExecute);
			
			stepsAndDependencies.entrySet().forEach(x -> x.getValue().remove(stepToExecute));
			stepsAndDependencies.remove(stepToExecute);
		}
		
		return StringUtils.join(executedSteps, StringUtils.EMPTY);
	}
	
	public static int findExecutionTime(List<String> requirements, int baseDelay, int workerCount) {
		Map<String, List<String>> stepsAndDependencies = parseStepsAndDependencies(requirements);
		
		Set<String> executedSteps = new LinkedHashSet<>();
		
		Set<Worker> workers = new HashSet<>();
		for (int i = 0; i < workerCount; i++) {
			workers.add(new Worker(i));
		}
		
		int totalTime = 0;
		while (!stepsAndDependencies.isEmpty()) {
			Set<String> availableSteps = stepsAndDependencies
					.entrySet()
					.stream()
					.filter(x -> CollectionUtils.isEmpty(x.getValue()) && workers.stream().noneMatch(y -> y.currentTask == x.getKey()))
					.map(Entry::getKey)
					.collect(Collectors.toSet());
			
			List<String> orderedAvailableSteps = availableSteps.stream().sorted().collect(Collectors.toList());
			
			int shortestTimeToExecute = Integer.MAX_VALUE;
			for (String step : orderedAvailableSteps) {
				List<Worker> availableWorkers = workers.stream().filter(x -> x.busyTime == 0).collect(Collectors.toList());
				
				if (CollectionUtils.isEmpty(availableWorkers)) {
					break;
				}
				
				Worker availableWorker = availableWorkers.get(0);
				
				int timeToExecute = baseDelay + ((int)step.charAt(0) - 64);
				
				// Assign worker to task
				availableWorker.busyTime = timeToExecute;
				availableWorker.currentTask = step;
				
				if (timeToExecute < shortestTimeToExecute) {
					shortestTimeToExecute = timeToExecute;
				}
			}
			
			// Fast forward until the next worker is free
			int lapsedTime = workers.stream().filter(x -> x.busyTime > 0).map(x -> x.busyTime).min(Comparator.naturalOrder()).get();
			totalTime += lapsedTime;
			
			workers.forEach(x -> x.busyTime = x.busyTime - lapsedTime < 0 ? 0 : x.busyTime - lapsedTime);
			
			workers
				.stream()
				.filter(worker -> worker.busyTime == 0 && worker.currentTask != null)
				.forEach(worker -> {
					stepsAndDependencies.entrySet().forEach(x -> x.getValue().remove(worker.currentTask));
					stepsAndDependencies.remove(worker.currentTask);
					worker.currentTask = null;
				});
		}
		
		return totalTime;
	}

	private static Map<String, List<String>> parseStepsAndDependencies(List<String> requirements) {
		Map<String, List<String>> stepsAndDependencies = new HashMap<>();
		
		Set<String> unexecutedSteps = new HashSet<>();
		
		for (String requirement : requirements) {
			String[] requirementParts = requirement.split(StringUtils.SPACE);
			
			String dependency = requirementParts[1];
			String requirementId = requirementParts[7];
			
			unexecutedSteps.add(dependency);
			unexecutedSteps.add(requirementId);
			
			if (!stepsAndDependencies.containsKey(requirementId)) {
				stepsAndDependencies.put(requirementId, new ArrayList<>(Arrays.asList(dependency)));
			} else {
				stepsAndDependencies.get(requirementId).add(dependency);
			}
		}
		
		unexecutedSteps
			.stream()
			.filter(x -> !stepsAndDependencies.containsKey(x))
			.forEach(x -> stepsAndDependencies.put(x, Arrays.asList()));
		return stepsAndDependencies;
	}
	
	static class Worker {
		public int id;
		
		public String currentTask;
		
		public int busyTime;
		
		public Worker(int id) {
			this.id = id;
		}
	}
}

package adventofcodejava;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class GuardScheduleReader {
	public static int multiplyBestGuardAndMinute(List<String> guardRecords) {
		Map<Integer, List<Integer>> guardSleepTimes = parseGuardSleepTimes(guardRecords);
		
		Entry<Integer, List<Integer>> longestSleepingGuardAndTimes = Collections.max(
				guardSleepTimes.entrySet(), 
				(Entry<Integer, List<Integer>> o1, Entry<Integer, List<Integer>> o2) -> {
					if (o1.getValue().size() > o2.getValue().size()) {
						return 1;
					}
					if (o1.getValue().size() < o2.getValue().size()) {
						return -1;
					}
					return 0;
		});
		
		Map<Integer, Integer> minuteCounts = new HashMap<>();
		for(int minute : longestSleepingGuardAndTimes.getValue()) {
			if (!minuteCounts.containsKey(minute)) {
				minuteCounts.put(minute, 1);
			} else {
				minuteCounts.replace(minute, minuteCounts.get(minute) + 1);
			}
		}
		
		Integer mostCommonMinute = Collections.max(
				minuteCounts.entrySet(),
				(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) -> {
					if (o1.getValue() > o2.getValue()) {
						return 1;
					}
					if (o1.getValue() < o2.getValue()) {
						return -1;
					}
					return 0;
				}).getKey();
		
		return longestSleepingGuardAndTimes.getKey() * mostCommonMinute;
	}

	private static Map<Integer, List<Integer>> parseGuardSleepTimes(List<String> guardRecords) {
		List<RecordEntry> records = new ArrayList<>();
		for (String guardRecord : guardRecords) {
			String[] recordParts = guardRecord.split(" ");
			Optional<Integer> guardId = null;
			EntryType entryType = null;
			
			if (guardRecord.contains("Guard #")) {
				guardId = Optional.of(Integer.parseInt(recordParts[3].substring(1)));
				entryType = EntryType.BEGIN_SHIFT;
			}
			if (guardRecord.contains("falls asleep")) {
				entryType = EntryType.SLEEP;
			}
			if (guardRecord.contains("wakes up")) {
				entryType = EntryType.WAKE;
			}
			
			Calendar recordDate = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			try {
				Date date = dateFormat.parse(recordParts[0].replaceAll("\\[", "") + " " + recordParts[1].replaceAll("\\]", ""));
				recordDate.setTime(date);
			} catch (ParseException e) {
				System.out.println(e);
			}
			
			records.add(new RecordEntry(guardId, entryType, recordDate));
		}
		
		// We will only be getting minutes in the same hour
		int startSleepMinute = 0;
		int endSleepMinute = 0;
		
		int guardId = 0;
		
		Collections.sort(records);
		
		Map<Integer, List<Integer>> guardSleepTimes = new HashMap<>();
		
		for (RecordEntry guardRecord : records) {
			if (EntryType.BEGIN_SHIFT.equals(guardRecord.getEntryType())) {
				guardId = guardRecord.getId().get();
				if (!guardSleepTimes.containsKey(guardId)) {
					guardSleepTimes.put(guardId, new ArrayList<>());
				}
			}
			
			if (EntryType.SLEEP.equals(guardRecord.getEntryType())) {
				startSleepMinute = guardRecord.getTimestamp().get(Calendar.MINUTE);
			}
			if (EntryType.WAKE.equals(guardRecord.getEntryType())) {
				endSleepMinute = guardRecord.getTimestamp().get(Calendar.MINUTE);
				
				for (int i = startSleepMinute; i < endSleepMinute; i++) {
					guardSleepTimes.get(guardId).add(i);
				}
			}
		}
		return guardSleepTimes;
	}
	
	private enum EntryType{
		BEGIN_SHIFT,
		SLEEP,
		WAKE
	}
	
	static class RecordEntry implements Comparable<RecordEntry>{
		private Optional<Integer> id;
		
		private Calendar timestamp;

		private EntryType entryType;
		
		public RecordEntry(Optional<Integer> id, EntryType entryType, Calendar timestamp) {
			super();
			this.id = id;
			this.entryType = entryType;
			this.timestamp = timestamp;
		}
		
		public Optional<Integer> getId() {
			return id;
		}

		public EntryType getEntryType() {
			return entryType;
		}

		public Calendar getTimestamp() {
			return timestamp;
		}

		@Override
		public int compareTo(RecordEntry o) {
			return timestamp.compareTo(o.getTimestamp());
		}
	}
}

package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long,TimeEntry> timeEntryHashMap = new HashMap<>();
    long recordId = 0L;

    @Override
    public TimeEntry create(TimeEntry timeEntryObj) {
        timeEntryObj.setId(++recordId);
        timeEntryHashMap.put(recordId,timeEntryObj);
        return timeEntryObj;
    }

    @Override
    public TimeEntry find(Long id) {
        TimeEntry obj = timeEntryHashMap.get(id);
        return obj;
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>();
        list.addAll(timeEntryHashMap.values());
        return list;
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntryObj) {
        if(timeEntryHashMap.containsKey(id)) {
            timeEntryObj.setId(id);
            timeEntryHashMap.put(id, timeEntryObj);
            return timeEntryObj;
        }
        return null;

    }

    @Override
    public void delete(Long id) {
        //TimeEntry obj = timeEntryHashMap.get(id);
        timeEntryHashMap.remove(id);
    }
}

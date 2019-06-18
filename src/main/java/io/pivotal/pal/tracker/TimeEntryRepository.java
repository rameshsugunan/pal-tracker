package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {
    TimeEntry create(TimeEntry timeEntryObj);
    TimeEntry find(Long id);
    List<TimeEntry> list();
    TimeEntry update(Long id,TimeEntry timeEntryObj);

    void delete(Long id);
}

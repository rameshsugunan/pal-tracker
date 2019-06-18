package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/time-entries")
public class TimeEntryController {

    TimeEntryRepository repository = null;

    public TimeEntryController(@Autowired TimeEntryRepository repostiry) {
        this.repository = repostiry;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        long recordId = 0L;
        timeEntry.setId(++recordId);
        repository.create(timeEntry);
        ResponseEntity response = new ResponseEntity(timeEntry,HttpStatus.CREATED);
        return response;
    }
    @GetMapping("{id}")
    public ResponseEntity<TimeEntry>  read(@PathVariable Long id) {
        TimeEntry timeEntry = repository.find(id);
        ResponseEntity response = null;
        if (timeEntry != null) {
            response = new ResponseEntity(timeEntry, HttpStatus.OK);
        } else {
            response = new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        ResponseEntity<List<TimeEntry>> timeEntryList = new ResponseEntity(repository.list(),HttpStatus.OK);
        return timeEntryList;
    }
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry expected) {
        TimeEntry updatedTimeEntry = repository.update(id, expected);
        ResponseEntity response = null;
        if (updatedTimeEntry != null) {

            response = new ResponseEntity(updatedTimeEntry, HttpStatus.OK);
        } else {
            response = new ResponseEntity(updatedTimeEntry, HttpStatus.NOT_FOUND);
        }
        return response;
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        ResponseEntity response = null;
        repository.delete(id);
        response = new ResponseEntity(HttpStatus.NO_CONTENT);
        return response;
    }

}

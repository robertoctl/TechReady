// ApiController.java
package com.challenge.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final RequestLogRepo repo;
    public ApiController(RequestLogRepo repo){ this.repo = repo; }

    // POST /api/submissions/{exerciseId}
    @PostMapping("/submissions/{exerciseId}")
    public ResponseEntity<?> create(
            @PathVariable String exerciseId,
            @RequestBody SubmissionRequest body,
            @RequestHeader(value = "X-Student-Id", required = false) String studentId,
            HttpServletRequest req) throws Exception {
        if (studentId == null || studentId.isBlank()) return ResponseEntity.badRequest()
                .body("Missing X-Student-Id header");
        RequestLog log = toLog(req, exerciseId, studentId, body);
        repo.save(log);
        Map<String,Object> out = Map.of("submissionId", log.getId(), "status","stored");
        return ResponseEntity.ok(out);
    }

    // GET /api/submissions?exerciseId=...&studentId=...
    @GetMapping("/submissions")
    public List<RequestLog> list(
            @RequestParam(required=false) String exerciseId,
            @RequestParam(required=false) String studentId) {
        if (exerciseId != null && studentId != null)
            return repo.findByStudentIdAndExerciseId(studentId, exerciseId);
        if (exerciseId != null) return repo.findByExerciseId(exerciseId);
        return repo.findAll();
    }

    // PUT /api/submissions/{submissionId}
    @PutMapping("/submissions/{submissionId}")
    public ResponseEntity<?> update(@PathVariable Long submissionId,
                                    @RequestBody UpdateRequest body,
                                    @RequestHeader(value="X-Student-Id", required=false) String studentId,
                                    HttpServletRequest req) throws Exception {
        Optional<RequestLog> existing = repo.findById(submissionId);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();
        RequestLog log = toLog(req,
                existing.get().getExerciseId(),
                studentId != null ? studentId : existing.get().getStudentId(),
                body);
        log.setId(submissionId); // overwrite same id to simulate update
        repo.save(log);
        return ResponseEntity.ok(Map.of("submissionId", submissionId, "status", "updated"));
    }

    // DELETE /api/submissions/{submissionId}
    @DeleteMapping("/submissions/{submissionId}")
    public ResponseEntity<?> delete(@PathVariable Long submissionId){
        if (!repo.existsById(submissionId)) return ResponseEntity.notFound().build();
        repo.deleteById(submissionId);
        return ResponseEntity.noContent().build();
    }

    // Admin: aggregated counts per student & exercise
    @GetMapping("/admin/report")
    public List<Map<String,Object>> report(){
        Map<String, Map<String, Long>> agg = repo.findAll().stream()
                .collect(Collectors.groupingBy(RequestLog::getExerciseId,
                        Collectors.groupingBy(RequestLog::getStudentId, Collectors.counting())));

        List<Map<String,Object>> rows = new ArrayList<>();
        agg.forEach((exercise, byStudent) ->
                byStudent.forEach((student, count) ->
                        rows.add(Map.of("exerciseId", exercise, "studentId", student, "calls", count))));
        return rows;
    }

    private RequestLog toLog(HttpServletRequest req, String exerciseId, String studentId, Object body) throws Exception {
        RequestLog log = new RequestLog();
        log.setExerciseId(exerciseId);
        log.setStudentId(studentId);
        log.setMethod(req.getMethod());
        log.setPath(req.getRequestURI());
        log.setQuery(req.getQueryString());
        log.setHeaders(Collections.list(req.getHeaderNames()).stream()
                .map(h -> h + ":" + req.getHeader(h)).collect(Collectors.joining("\n")));
        if (body != null) log.setBody(body.toString());
        else {
            try (BufferedReader br = req.getReader()){
                log.setBody(br.lines().collect(Collectors.joining("\n")));
            }
        }
        return log;
    }
}

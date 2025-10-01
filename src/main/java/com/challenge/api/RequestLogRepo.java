// RequestLogRepo.java
package com.challenge.api;

import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface RequestLogRepo extends JpaRepository<RequestLog, Long> {
    List<RequestLog> findByExerciseId(String exerciseId);
    List<RequestLog> findByStudentIdAndExerciseId(String studentId, String exerciseId);
}

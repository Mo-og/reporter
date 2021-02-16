package com.practice3.reporter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultationSkeleton {

    private long consultationId;
    private String date;
    private long hospitalId;
    private long reportId;
    private long coordinatorId;
    private long consultantId;
    private long specializationId;
    private long recommendationId;
    private long transportId;
    private long dutyId;
    private long dutySpecializationId;
    boolean isCovid;
}

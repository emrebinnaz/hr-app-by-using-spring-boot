package com.example.hrapp.hrapp.Domain.Common;

import com.example.hrapp.hrapp.Domain.Applicant;
import com.example.hrapp.hrapp.Domain.Job;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class JobApplicationPK implements Serializable {

    private final Job job;
    private final Applicant applicant;


    @Override
    public boolean equals(Object o) { // try to make it with "@EqualsAndHashCode"
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        JobApplicationPK that = (JobApplicationPK) o;
        return Objects.equals(job, that.job) &&
                Objects.equals(applicant, that.applicant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.job, this.applicant);
    }
}

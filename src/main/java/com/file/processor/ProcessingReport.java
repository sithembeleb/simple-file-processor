package com.file.processor;

import javax.persistence.Entity;

@Entity
public class ProcessingReport {

    private int transaction;
    private int tracking;
    private int termination;
    private int file;
    private String filename;

    public ProcessingReport() {
    }

    public ProcessingReport(final int transaction, final int tracking, final int termination, final int file, final String filename) {
        this.transaction = transaction;
        this.tracking = tracking;
        this.termination = termination;
        this.file = file;
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "ProcessingReport{" +
                "transaction=" + transaction +
                ", tracking=" + tracking +
                ", termination=" + termination +
                ", file=" + file +
                ", filename='" + filename + '\'' +
                '}';
    }
}

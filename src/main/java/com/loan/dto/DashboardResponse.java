package com.loan.dto;

public class DashboardResponse {

    private long totalCustomers;
    private long totalLoans;
    private long approvedLoans;
    private long rejectedLoans;
    private long pendingLoans;
    private long closedLoans;

    public DashboardResponse() {
    }

    public DashboardResponse(
            long totalCustomers,
            long totalLoans,
            long approvedLoans,
            long rejectedLoans,
            long pendingLoans,
            long closedLoans) {

        this.totalCustomers = totalCustomers;
        this.totalLoans = totalLoans;
        this.approvedLoans = approvedLoans;
        this.rejectedLoans = rejectedLoans;
        this.pendingLoans = pendingLoans;
        this.closedLoans = closedLoans;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public long getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(long totalLoans) {
        this.totalLoans = totalLoans;
    }

    public long getApprovedLoans() {
        return approvedLoans;
    }

    public void setApprovedLoans(long approvedLoans) {
        this.approvedLoans = approvedLoans;
    }

    public long getRejectedLoans() {
        return rejectedLoans;
    }

    public void setRejectedLoans(long rejectedLoans) {
        this.rejectedLoans = rejectedLoans;
    }

    public long getPendingLoans() {
        return pendingLoans;
    }

    public void setPendingLoans(long pendingLoans) {
        this.pendingLoans = pendingLoans;
    }

    public long getClosedLoans() {
        return closedLoans;
    }

    public void setClosedLoans(long closedLoans) {
        this.closedLoans = closedLoans;
    }
}
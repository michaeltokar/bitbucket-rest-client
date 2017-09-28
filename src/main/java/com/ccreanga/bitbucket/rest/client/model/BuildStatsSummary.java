package com.ccreanga.bitbucket.rest.client.model;

public class BuildStatsSummary {
    private final Long failed;
    private final Long inProgress;
    private final Long successful;

    private BuildStatsSummary(Long failed, Long inProgress, Long successful) {
        this.failed = failed;
        this.inProgress = inProgress;
        this.successful = successful;
    }

    public Long getFailed() {
        return failed;
    }

    public Long getInProgress() {
        return inProgress;
    }

    public Long getSuccessful() {
        return successful;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuildStatsSummary that = (BuildStatsSummary) o;

        if (failed != null ? !failed.equals(that.failed) : that.failed != null) return false;
        if (inProgress != null ? !inProgress.equals(that.inProgress) : that.inProgress != null) return false;
        return successful != null ? successful.equals(that.successful) : that.successful == null;
    }

    @Override
    public int hashCode() {
        int result = failed != null ? failed.hashCode() : 0;
        result = 31 * result + (inProgress != null ? inProgress.hashCode() : 0);
        result = 31 * result + (successful != null ? successful.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BuildStatsSummary{" +
                "failed=" + failed +
                ", inProgress=" + inProgress +
                ", successful=" + successful +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private  Long failed;
        private  Long inProgress;
        private  Long successful;

        public Builder() {
        }

        public void setFailed(Long failed) {
            this.failed = failed;
        }

        public void setInProgress(Long inProgress) {
            this.inProgress = inProgress;
        }

        public void setSuccessful(Long successful) {
            this.successful = successful;
        }

        public BuildStatsSummary build() {
            return new BuildStatsSummary(failed, inProgress, successful);
        }
    }
}

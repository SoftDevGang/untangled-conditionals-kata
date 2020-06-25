public class PipelineStatus {
    private boolean testsPassed;
    private boolean deploySuccess;

    public void setTestsPassed() {
        this.testsPassed = true;
    }

    public void setTestsFailed() {
        this.testsPassed = false;
    }

    public boolean areTestsPassed() {
        return this.testsPassed;
    }

    public void setDeploySuccess() {
        this.deploySuccess = true;
    }

    public void setDeployFailed() {
        this.deploySuccess = false;
    }

    public boolean isDeploySuccess() {
        return  this.deploySuccess;
    }

    String getStatusMessage() {
        String message;
        if (areTestsPassed()) {
            if (isDeploySuccess()) {
                message = "Deployment completed successfully";
            } else {
                message = "Deployment failed";
            }
        } else {
            message = "Tests failed";
        }
        return message;
    }
}

import dependencies.Config;
import dependencies.Emailer;
import dependencies.Logger;
import dependencies.Project;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        PipelineStatus pipelineStatus = new PipelineStatus();

        handleTests(project, pipelineStatus);
        handleDeployment(project, pipelineStatus);
        handleEmail(project, pipelineStatus);
    }

    private void handleEmail(Project project, PipelineStatus pipelineStatus) {
        if (config.sendEmailSummary()) {
            log.info("Sending email");
            emailer.send(pipelineStatus.getStatusMessage());
        } else {
            log.info("Email disabled");
        }
    }

    private void handleDeployment(Project project, PipelineStatus pipelineStatus) {
        if (pipelineStatus.areTestsPassed()) {
            if ("success".equals(project.deploy())) {
                log.info("Deployment successful");
                pipelineStatus.setDeploySuccess();
            } else {
                log.error("Deployment failed");
                pipelineStatus.setDeployFailed();
            }
        } else {
            pipelineStatus.setDeployFailed();
        }
    }

    private void handleTests(Project project, PipelineStatus pipelineStatus) {
        if (project.hasTests()) {
            if ("success".equals(project.runTests())) {
                log.info("Tests passed");
                pipelineStatus.setTestsPassed();
            } else {
                log.error("Tests failed");
                pipelineStatus.setTestsFailed();
            }
        } else {
            log.info("No tests");
            pipelineStatus.setTestsPassed();
        }
    }

}

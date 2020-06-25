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
        boolean testsPassed = runTests(project);

        boolean deploySuccessful = deploy(project, testsPassed);

        if (config.sendEmailSummary()) {
            log.info("Sending email");
            if (testsPassed) {
                if (deploySuccessful) {
                    emailer.send("Deployment completed successfully");
                } else {
                    emailer.send("Deployment failed");
                }
            } else {
                emailer.send("Tests failed");
            }
        } else {
            log.info("Email disabled");
        }
    }

    private boolean runTests(Project project) {
        if (!project.hasTests()) {
            log.info("No tests");
            return true;
        }
        if ("success".equals(project.runTests())) {
            log.info("Tests passed");
            return true;
        } else {
            log.error("Tests failed");
            return false;
        }
    }

    private boolean deploy(Project project, boolean testsPassed) {
        if (!testsPassed) return false;

        if ("success".equals(project.deploy())) {
            log.info("Deployment successful");
            return true;
        } else {
            log.error("Deployment failed");
            return false;
        }
    }
}

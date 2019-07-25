package org.kie.cekit.cacher.builds.github;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.kie.cekit.cacher.builds.yaml.YamlFilesHelper;
import org.kie.cekit.cacher.builds.yaml.pojo.Modules;
import org.kie.cekit.cacher.properties.CacherProperties;
import org.kie.cekit.cacher.utils.CacherUtils;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PullRequestAcceptorTest {

    @Inject
    YamlFilesHelper yamlFilesHelper;

    @Inject
    CacherProperties cacherProperties;

    @Inject
    PullRequestAcceptor prAcceptor;

    @Inject
    GitRepository gitRepository;

    @Inject
    CacherUtils cacherUtils;

    @AfterAll
    public void removeGitRepos() throws Exception {
        gitRepository.cleanGitRepos();
    }

    @BeforeAll
    public void prepareGitRepos() throws IOException, InterruptedException {
        cacherUtils.startupVerifications();
        gitRepository.prepareLocalGitRepo();
    }

    @Test
    public void testRhpamCommentaryAddition() throws IOException, InterruptedException {
        // test bc monitoring
        String bcMonitoringFile = cacherProperties.getGitDir() + "/rhpam-7-image/businesscentral-monitoring/modules/businesscentral-monitoring/module.yaml";
        Modules bcMonitoring = yamlFilesHelper.load(bcMonitoringFile);
        yamlFilesHelper.writeModule(bcMonitoring, bcMonitoringFile);
        prAcceptor.reAddComment(bcMonitoringFile, "target: \"business_central_monitoring_distribution.zip\"",
                String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-monitoring-ee7.zip"));
        Assertions.assertTrue(containsComment(bcMonitoringFile, String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-monitoring-ee7.zip")));

        // test businessCentral
        String businessCentralFile = cacherProperties.getGitDir() + "/rhpam-7-image/businesscentral/modules/businesscentral/module.yaml";
        Modules businessCentral =  yamlFilesHelper.load(businessCentralFile);
        yamlFilesHelper.writeModule(businessCentral, businessCentralFile);
        prAcceptor.reAddComment(businessCentralFile, "target: \"business_central_distribution.zip\"",
                String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-business-central-eap7-deployable.zip"));
        Assertions.assertTrue(containsComment(businessCentralFile, String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-business-central-eap7-deployable.zip")));

        // test rhpam controller
        String controllerFile = cacherProperties.getGitDir() + "/rhpam-7-image/controller/modules/controller/module.yaml";
        Modules controller = yamlFilesHelper.load(controllerFile);
        yamlFilesHelper.writeModule(controller, controllerFile);
        prAcceptor.reAddComment(controllerFile, "target: \"add_ons_distribution.zip\"",
                String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-add-ons.zip"));
        Assertions.assertTrue(containsComment(controllerFile, String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-add-ons.zip")));

        // test rhpam kieserver
        String kieserverFile = cacherProperties.getGitDir() + "/rhpam-7-image/kieserver/modules/kieserver/module.yaml";
        Modules kieserver = yamlFilesHelper.load(kieserverFile);
        yamlFilesHelper.writeModule(kieserver, kieserverFile);
        String buildDate = gitRepository.getCurrentProductBuildDate();
        String backendFileName = String.format("jbpm-wb-kie-server-backend-7.5.0.redhat-%s.jar", buildDate);
        prAcceptor.reAddComment(kieserverFile, String.format("  value: \"%s\"", backendFileName),
                "# remember to also update \"JBPM_WB_KIE_SERVER_BACKEND_JAR\" value");
        Assertions.assertTrue(containsComment(kieserverFile, "# remember to also update \"JBPM_WB_KIE_SERVER_BACKEND_JAR\" value"));

        prAcceptor.reAddComment(kieserverFile, "target: \"slf4j-simple.jar\"", "  # slf4j-simple-1.7.22.redhat-2.jar");
        Assertions.assertTrue(containsComment(kieserverFile,
                String.format("  # %s", "slf4j-simple-1.7.22.redhat-2.jar")));

        prAcceptor.reAddComment(kieserverFile, "target: \"kie_server_distribution.zip\"",
                String.format("  # %s","rhpam-7.5.0.PAM-redhat-20191006-kie-server-ee8.zip"));
        Assertions.assertTrue(containsComment(kieserverFile, String.format("  # %s","rhpam-7.5.0.PAM-redhat-20191006-kie-server-ee8.zip")));

        prAcceptor.reAddComment(kieserverFile, "target: \"business_central_distribution.zip\"",
                String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-business-central-eap7-deployable.zip"));
        Assertions.assertTrue(containsComment(kieserverFile, String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-business-central-eap7-deployable.zip")));

        // smartrouter tests
        String smartrouterFile = cacherProperties.getGitDir() + "/rhpam-7-image/smartrouter/modules/smartrouter/module.yaml";
        Modules smartrouter = yamlFilesHelper.load(smartrouterFile);
        yamlFilesHelper.writeModule(smartrouter, smartrouterFile);
        prAcceptor.reAddComment(smartrouterFile, "target: \"add_ons_distribution.zip\"",
                String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-add-ons.zip"));
        Assertions.assertTrue(containsComment(smartrouterFile, String.format("  # %s", "rhpam-7.5.0.PAM-redhat-20191006-add-ons.zip")));
    }

    @Test
    public void testRhdmCommentaryAddition() throws IOException, InterruptedException {

        String controllerFile = cacherProperties.getGitDir() + "/rhdm-7-image/controller/modules/controller/module.yaml";
        Modules controller = yamlFilesHelper.load(controllerFile);
        yamlFilesHelper.writeModule(controller, controllerFile);
        prAcceptor.reAddComment(controllerFile, "target: \"add_ons_distribution.zip\"",
                String.format("  # %s", "rhdm-7.5.0.DM-redhat-20191006-add-ons.zip"));
        Assertions.assertTrue(containsComment(controllerFile,
                String.format("  # %s", "rhdm-7.5.0.DM-redhat-20191006-add-ons.zip")));


        String decisionCentralFile = cacherProperties.getGitDir() + "/rhdm-7-image/decisioncentral/modules/decisioncentral/module.yaml";
        Modules decisionCentral = yamlFilesHelper.load(decisionCentralFile);
        yamlFilesHelper.writeModule(decisionCentral, decisionCentralFile);
        prAcceptor.reAddComment(decisionCentralFile, "target: \"decision_central_distribution.zip\"",
                String.format("  # %s", "rhdm-7.5.0.DM-redhat-20191006-decision-central-eap7-deployable.zip"));
        Assertions.assertTrue(containsComment(decisionCentralFile,
                String.format("  # %s", "rhdm-7.5.0.DM-redhat-20191006-decision-central-eap7-deployable.zip")));


        String kieserverFile = cacherProperties.getGitDir() + "/rhdm-7-image/kieserver/modules/kieserver/module.yaml";
        Modules kieserver = yamlFilesHelper.load(kieserverFile);
        yamlFilesHelper.writeModule(kieserver, kieserverFile);
        prAcceptor.reAddComment(kieserverFile, "target: \"kie_server_distribution.zip\"",
                String.format("  # %s", "rhdm-7.5.0.DM-redhat-20191006-kie-server-ee8.zip"));
        prAcceptor.reAddComment(kieserverFile, "target: \"slf4j-simple.jar\"", "  # slf4j-simple-1.7.22.redhat-2.jar");
        Assertions.assertTrue(containsComment(kieserverFile,
                String.format("  # %s", "rhdm-7.5.0.DM-redhat-20191006-kie-server-ee8.zip")));
        Assertions.assertTrue(containsComment(kieserverFile,
                String.format("  # %s", "slf4j-simple-1.7.22.redhat-2.jar")));


        String optawebFile = cacherProperties.getGitDir() + "/rhdm-7-image/optaweb-employee-rostering/modules/optaweb-employee-rostering/module.yaml";
        Modules optaweb = yamlFilesHelper.load(optawebFile);
        yamlFilesHelper.writeModule(optaweb, optawebFile);
        String buildDate = gitRepository.getCurrentProductBuildDate();
        String employeeWarFileName = String.format("employee-rostering-distribution-7.5.0.redhat-%s/binaries/employee-rostering-webapp-7.5.0.redhat-%s.war", buildDate, buildDate);
        prAcceptor.reAddComment(optawebFile, String.format("  value: \"%s\"", employeeWarFileName),
                "# remember to also update \"EMPLOYEE_ROSTERING_DISTRIBUTION_WAR\" value");
        Assertions.assertTrue(containsComment(optawebFile, "# remember to also update \"EMPLOYEE_ROSTERING_DISTRIBUTION_WAR\" value"));

        prAcceptor.reAddComment(optawebFile, "target: \"add_ons_distribution.zip\"",
                String.format("  # %s","rhdm-7.5.0.DM-redhat-20191006-add-ons.zip"));
        Assertions.assertTrue(containsComment(optawebFile, String.format("  # %s","rhdm-7.5.0.DM-redhat-20191006-add-ons.zip")));

    }

    /**
     * verify if the comment was correctly added to the target file
     *
     * @param fileName
     * @param comment
     */
    private boolean containsComment(String fileName, String comment) {
        boolean containsString = false;
        String line;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(comment)) {
                    containsString = true;
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return containsString;
    }
}

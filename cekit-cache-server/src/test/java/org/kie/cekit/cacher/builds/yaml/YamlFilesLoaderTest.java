package org.kie.cekit.cacher.builds.yaml;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.kie.cekit.cacher.builds.github.PullRequestAcceptor;
import org.kie.cekit.cacher.builds.yaml.pojo.Modules;
import org.kie.cekit.cacher.properties.CacherProperties;

import javax.inject.Inject;

@QuarkusTest
public class YamlFilesLoaderTest {

    @Inject
    YamlFilesHelper yamlFilesHelper;

    @Inject
    CacherProperties cacherProperties;

    @Inject
    PullRequestAcceptor p;

    @Test
    public void testModulesYamlFile() {
        Modules modules = yamlFilesHelper.load("modules.yaml");

        Assertions.assertNotNull(modules.getSchemaVersion());
        Assertions.assertEquals(1, modules.getSchemaVersion());
        Assertions.assertEquals("rhdm-7-kieserver", modules.getName());
        Assertions.assertEquals("Red Hat Decision Manager KIE Server 7.4 install", modules.getDescription());
        Assertions.assertEquals("[Label{name='org.jboss.product', value='rhdm-kieserver'}, Label{name='org.jboss.product.version', value='7.5.0'}, Label{name='org.jboss.product.rhdm-kieserver.version', value='7.5.0'}]", modules.getLabels().toString());
        Assertions.assertEquals("[Env{name='JBOSS_PRODUCT', value='rhdm-kieserver'}, Env{name='RHDM_KIESERVER_VERSION', value='7.5.0'}, Env{name='PRODUCT_VERSION', value='7.5.0'}, Env{name='KIE_SERVER_DISTRIBUTION_ZIP', value='kie_server_distribution.zip'}]", modules.getEnvs().toString());
        Assertions.assertEquals("[Artifact{name='KIE_SERVER_DISTRIBUTION_ZIP', target='kie_server_distribution.zip', md5='03bc4e80d0945994c4e9beb9e756e0ab'}, Artifact{name='slf4j-simple.jar', target='slf4j-simple.jar', md5='51c319582c16a07c21e41737e45cb03a'}]", modules.getArtifacts().toString());
        Assertions.assertEquals("Run{user=185, cmd=[/opt/eap/bin/standalone.sh, -b, 0.0.0.0, -c, standalone-full.xml]}", modules.getRun().toString());
        Assertions.assertEquals("[Execute{script='install'}]", modules.getExecute().toString());
    }

    @Test
    public void loadRhpamModulesFromGitTest() {
        Modules bcMonitoring = yamlFilesHelper.load(cacherProperties.getGitDir() +
                "/rhpam-7-image/businesscentral-monitoring/modules/businesscentral-monitoring/module.yaml");

        Modules businessCentral =  yamlFilesHelper.load(cacherProperties.getGitDir() +
                "/rhpam-7-image/businesscentral/modules/businesscentral/module.yaml");

        Modules controller = yamlFilesHelper.load(cacherProperties.getGitDir() +
                "/rhpam-7-image/controller/modules/controller/module.yaml");

        Modules kieserver = yamlFilesHelper.load(cacherProperties.getGitDir() +
                "/rhpam-7-image/kieserver/modules/kieserver/module.yaml");

        Modules smartrouter = yamlFilesHelper.load(cacherProperties.getGitDir() +
                "/rhpam-7-image/smartrouter/modules/smartrouter/module.yaml");

        Assertions.assertNotNull(bcMonitoring);
        Assertions.assertEquals("rhpam-7-businesscentral-monitoring", bcMonitoring.getName());

        Assertions.assertNotNull(businessCentral);
        Assertions.assertEquals("rhpam-7-businesscentral", businessCentral.getName());

        Assertions.assertNotNull(controller);
        Assertions.assertEquals("rhpam-7-controller", controller.getName());

        Assertions.assertNotNull(kieserver);
        Assertions.assertEquals("rhpam-7-kieserver", kieserver.getName());

        Assertions.assertNotNull(smartrouter);
        Assertions.assertEquals("rhpam-7-smartrouter", smartrouter.getName());

    }

    @Test
    public void loadRhdmModulesFromGitTest() {

        Modules controller = yamlFilesHelper.load(cacherProperties.getGitDir() +
                "/rhdm-7-image/controller/modules/controller/module.yaml");

        Modules decisioncentral =  yamlFilesHelper.load(cacherProperties.getGitDir() +
                "/rhdm-7-image/decisioncentral/modules/decisioncentral/module.yaml");

        Modules kieserver = yamlFilesHelper.load(cacherProperties.getGitDir() +
                "/rhdm-7-image/kieserver/modules/kieserver/module.yaml");

        Modules optaweb = yamlFilesHelper.load(cacherProperties.getGitDir() +
                "/rhdm-7-image/optaweb-employee-rostering/modules/optaweb-employee-rostering/module.yaml");


        yamlFilesHelper.writeModule(controller,cacherProperties.getGitDir() +
                "/rhdm-7-image/controller/modules/controller/module.yaml");

        Assertions.assertNotNull(controller);
        Assertions.assertEquals("rhdm-7-controller", controller.getName());

        Assertions.assertNotNull(decisioncentral);
        Assertions.assertEquals("rhdm-7-decisioncentral", decisioncentral.getName());

        Assertions.assertNotNull(kieserver);
        Assertions.assertEquals("rhdm-7-kieserver", kieserver.getName());

        Assertions.assertNotNull(optaweb);
        Assertions.assertEquals("rhdm-7-optaweb-employee-rostering", optaweb.getName());

    }

}

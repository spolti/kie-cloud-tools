package org.kie.cekit.cacher.builds.github;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.kie.cekit.cacher.properties.CacherProperties;

import javax.inject.Inject;
import java.io.IOException;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GitRepositoryTest {

    @Inject
    GitRepository gitRepository;

    @Inject
    CacherProperties cacherProperties;

    /**
     * If this tests runs ok means that the github repos
     * are correctly persisted on filesystem
     */
    @Test
    public void getCurrentProductBuildDateTest() throws IOException, InterruptedException {
        Assertions.assertNotNull(gitRepository.getCurrentProductBuildDate(cacherProperties.defaultBranch()));
    }

    @Test
    public void handleBranchTest() throws IOException, InterruptedException {
        gitRepository.handleBranch(BranchOperation.NEW_BRANCH, "myBranch", cacherProperties.defaultBranch(), "rhpam-7-image");
        gitRepository.handleBranch(BranchOperation.NEW_BRANCH, "myBranch", cacherProperties.defaultBranch(), "rhdm-7-image");

        gitRepository.handleBranch(BranchOperation.DELETE_BRANCH, "myBranch", null, "rhpam-7-image");
        gitRepository.handleBranch(BranchOperation.DELETE_BRANCH, "myBranch", null, "rhdm-7-image");
    }

    @Test
    public void handleCustomBranchTest() throws IOException, InterruptedException {
        gitRepository.handleBranch(BranchOperation.NEW_BRANCH, "myOtherBranch", "7.5.x", "rhpam-7-image");
        gitRepository.handleBranch(BranchOperation.NEW_BRANCH, "myOtherBranch", "7.5.x", "rhdm-7-image");

        gitRepository.handleBranch(BranchOperation.DELETE_BRANCH, "myOtherBranch", null, "rhpam-7-image");
        gitRepository.handleBranch(BranchOperation.DELETE_BRANCH, "myOtherBranch", null, "rhdm-7-image");
    }


}

package org.kie.cekit.cacher.builds.github;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.kie.cekit.cacher.utils.CacherUtils;

import javax.inject.Inject;
import java.io.IOException;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GitRepositoryTest {

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
        //gitRepository.prepareLocalGitRepo();
    }

    /**
     * If this tests runs ok means that the github repos
     * are correctly persisted on filesystem
     */
    @Test
    public void getCurrentProductBuildDateTest() throws IOException, InterruptedException {
        Assertions.assertNotNull(gitRepository.getCurrentProductBuildDate());
    }

    @Test
    public void handleBranchTest() throws IOException, InterruptedException {
        gitRepository.handleBranch(BranchOperation.NEW_BRANCH, "myBranch", "rhpam-7-image");
        gitRepository.handleBranch(BranchOperation.NEW_BRANCH, "myBranch", "rhdm-7-image");

        gitRepository.handleBranch(BranchOperation.DELETE_BRANCH, "myBranch", "rhpam-7-image");
        gitRepository.handleBranch(BranchOperation.DELETE_BRANCH, "myBranch", "rhdm-7-image");
    }

}

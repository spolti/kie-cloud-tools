package org.kie.cekit.cacher.builds.nightly;

import org.kie.cekit.cacher.objects.PlainArtifact;

/**
 * A simple callback to notify the PullRequestAcceptor class
 * when a new rhdm/rhpam artifact is being downloaded and
 * after it is persisted (download is finished) to start the
 * Pull Request process
 */
public interface NightlyBuildUpdatesInterceptor {

    /**
     * When a new artifact build is detected and a download starts,
     * then this interface notifies all classes that implements it.
     * see {@link org.kie.cekit.cacher.builds.nightly.NightlyBuildsWatcher}
     *
     * @param plainArtifact
     * @param force         Useful when automated PRs for nightly builds are wrongly merged, it will force the cacher to
     *                      create the PRs containing the latest artifacts
     */
    void onNewBuildReceived(PlainArtifact plainArtifact, boolean force);

    /**
     * When a file is persisted, notify the callback and, if there is a
     * filename waiting for its checksum, update the hashMap
     * and {@link NightlyBuildsPullRequestAcceptor}
     *
     * @param fileName
     * @param checkSum
     */
    void onFilePersisted(String fileName, String checkSum);

}

package org.kie.cekit.cacher.objects;

public class PlainArtifact {

    private String fileName;
    private String checksum;
    private String buildDate;

    public PlainArtifact() {
    }

    public PlainArtifact(String fileName, String checksum, String buildDate) {
        this.fileName = fileName;
        this.checksum = checksum;
        this.buildDate = buildDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public String getChecksum() {
        if (null == checksum) {
            checksum = "";
        }
        return checksum.replace("tmp", "Downloading...");
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    @Override
    public String toString() {
        return "PlainArtifact{" +
                "fileName='" + fileName + '\'' +
                ", checksum='" + checksum + '\'' +
                ", buildDate='" + buildDate + '\'' +
                '}';
    }
}

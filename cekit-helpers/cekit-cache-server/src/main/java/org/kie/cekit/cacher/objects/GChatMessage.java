package org.kie.cekit.cacher.objects;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class GChatMessage {

    @NotEmpty(message = "At least one user must be notified")
    private List<String> recipients;
    @NotEmpty(message = "The message body is missing")
    private String message;
    @NotEmpty(message = "You must identify yourself :)")
    private String from;
    @NotEmpty(message = "Seems this message is useless since there is no subject :)")
    private String subject;

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "GChatMessage{" +
                "receipts=" + recipients +
                ", message='" + message + '\'' +
                ", from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}

package org.kie.cekit.cacher.notification;

public interface Notification {

    /**
     * Exposes notification method for hangout chat, webhook is required.
     *
     * @param message
     * @param webhook - Enables the incoming webhook for hangouts chat notification
     *                it should be enabled by room.
     */
    void send(String message, String webhook);

    /**
     * Format the given text for bold
     *
     * @param text
     * @return bold formatted text
     */
    default String bold(String text) {
        return "*" + text + "*";
    }

    /**
     * Format the given text for italic
     *
     * @param text
     * @return italic formatted text
     */
    default String italic(String text) {
        return "_" + text + "_";
    }

    /**
     * Format the given text for strikethrough
     *
     * @param text
     * @return strikethrough formatted text
     */
    default String strikethrough(String text) {
        return "~" + text + "~";
    }

    /**
     * Format the given text for monospace
     *
     * @param text
     * @return monospace formatted text
     */
    default String monospace(String text) {
        return "`" + text + "`";
    }

    /**
     * Format the given text for monospace block
     *
     * @param text
     * @return monospace block formatted text
     */
    default String monospaceBlock(String text) {
        return "```" + text + "```";
    }

    /**
     * Format the given text for link
     *
     * @param text
     * @param link
     * @return link formatted text
     */
    default String link(String link, String text) {
        return "<" + link + "|" + text + ">";
    }

    /**
     * Format the given text for mention user
     *
     * @param userIds
     * @return mention formatted text
     */
    default String mention(String[] userIds) {
        StringBuilder text = new StringBuilder("Reviewers please take a look:");
        for (String userId : userIds) {
            text.append( " <users/" + userId + ">");
        }
        return text.toString();
    }
}
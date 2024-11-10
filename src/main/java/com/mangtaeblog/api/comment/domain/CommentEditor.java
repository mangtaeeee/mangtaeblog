package com.mangtaeblog.api.comment.domain;

import lombok.Getter;

public record CommentEditor(String content) {

    public static CommentEditorBuilder builder() {
        return new CommentEditorBuilder();
    }

    public static class CommentEditorBuilder {
        private String content;

        CommentEditorBuilder() {
        }

        public CommentEditorBuilder content(final String content) {
            if (content != null) {
                this.content = content;
            }
            return this;
        }

        public CommentEditor build() {
            return new CommentEditor(this.content);
        }

    }


}

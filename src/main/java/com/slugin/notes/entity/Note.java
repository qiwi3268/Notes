package com.slugin.notes.entity;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Length(max = 255, message = "Header is too long")
    private String header;

    @Length(max = 2048, message = "Text is too long")
    private String content;

    private String firstLine;

    public Note() {
    }

    public Note(String header, String content) {
        this.header = header;
        this.content = content;
        initFirstLine();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public boolean hasHeader() {
        return header != null && !header.isEmpty() && header.matches("(\\s*\\S+\\s*)+");
    }

    public boolean hasContent() {
        return content != null && !content.isEmpty() && content.matches("(\\s*\\S+\\s*)+");
    }

    public boolean hasFirstLine() {
        return firstLine != null && !firstLine.isEmpty();
    }

    public void initFirstLine() {
        if (hasHeader()) {
            firstLine = header;
        } else if (hasContent()) {
            StringBuilder builder = new StringBuilder(content);
            while (builder.charAt(0) == '\r' || builder.charAt(0) == '\n') {
                builder.deleteCharAt(0);
            }
            String result = builder.toString();
            firstLine = result.contains("\n") ? result.substring(0, result.indexOf('\n')) : result;
        }
    }
}
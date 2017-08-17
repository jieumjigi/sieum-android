package com.jieumjigi.sieum;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wjddk on 2017-02-06.
 */

public class poemOfToday {

    /**
     * poem/poemOfToday
     * title, poetName, introPoet, linkToBook, question, contents, pushDueDate, published_date
     */

    @SerializedName("title")
    String title;

    @SerializedName("poetName")
    String poetName;

    @SerializedName("introPoet")
    String introPoet;

    @SerializedName("linkToBook")
    String linkToBook;

    @SerializedName("question")
    String question;

    @SerializedName("contents")
    String contents;

    @SerializedName("pushDueDate")
    String pushDueDate;

    @SerializedName("published_date")
    String published_date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoetName() {
        return poetName;
    }

    public void setPoetName(String poetName) {
        this.poetName = poetName;
    }

    public String getIntroPoet() {
        return introPoet;
    }

    public void setIntroPoet(String introPoet) {
        this.introPoet = introPoet;
    }

    public String getLinkToBook() {
        return linkToBook;
    }

    public void setLinkToBook(String linkToBook) {
        this.linkToBook = linkToBook;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getPushDueDate() {
        return pushDueDate;
    }

    public void setPushDueDate(String pushDueDate) {
        this.pushDueDate = pushDueDate;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }
}
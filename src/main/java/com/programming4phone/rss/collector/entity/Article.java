package com.programming4phone.rss.collector.entity;

import java.util.Date;

public class Article {
	
	private String title;
	private String description;
	private Date pubDate;
	private String articleLink;
	private String author;
	private String imageLink;
	
	public String getTitle() {
		return title;
	}
	public Article setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public Article setDescription(String description) {
		this.description = description;
		return this;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public Article setPubDate(Date pubDate) {
		this.pubDate = pubDate;
		return this;
	}
	public String getArticleLink() {
		return articleLink;
	}
	public Article setArticleLink(String articleLink) {
		this.articleLink = articleLink;
		return this;
	}
	public String getAuthor() {
		return author;
	}
	public Article setAuthor(String author) {
		this.author = author;
		return this;
	}
	public String getImageLink() {
		return imageLink;
	}
	public Article setImageLink(String imageLink) {
		this.imageLink = imageLink;
		return this;
	}
	@Override
	public String toString() {
		return "Article [title=" + title + ", description=" + description + ", pubDate=" + pubDate
				+ ", articleLink=" + articleLink + ", author=" + author + ", imageLink=" + imageLink + "]";
	}
}

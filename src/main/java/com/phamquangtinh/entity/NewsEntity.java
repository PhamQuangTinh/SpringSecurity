package com.phamquangtinh.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//Class này là 1 entity, và là bản sao của table trong database, để mapping giữa java class với table trong mysql
//@Table có tên là news
@Entity
@Table(name = "news")
public class NewsEntity extends Base {

	// 1 colunm có tên generate với coloumn trong db
	@Column(name = "title")
	private String title;

	@Column(name = "thumbnail")
	private String thumbnail;

	// columnDefinition = "TEXT": Định nghĩa thuộc tính text trong db
	@Column(name = "shortdescription", columnDefinition = "TEXT")
	private String shortDescription;

	@Column(name = "content", columnDefinition = "TEXT")
	private String content;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

package com.spring.dto;
import java.util.List;
public class AllCourseRequest {

private List<Integer> category=null;
private List<Integer> courseType=null;
private List<Integer> level=null;
private List<Integer> publisher=null;
private List<Integer> course=null;
private String rating=null;
private String searchText=null;
private int pageNumber=0;
private int pageSize=5;
 
public String getSearchText() {
	return searchText;
}
public void setSearchText(String searchText) {
	this.searchText = searchText;
}
public int getPageNumber() {
	return pageNumber;
}
public void setPageNumber(int pageNumber) {
	this.pageNumber = pageNumber;
}
public int getPageSize() {
	return pageSize;
}
public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
}
public String getRating() {
	return rating;
}
public void setRating(String rating) {
	this.rating = rating;
}
public List<Integer> getCategory() {
	return category;
}
public void setCategory(List<Integer> category) {
	this.category = category;
}
public List<Integer> getCourseType() {
	return courseType;
}
public void setCourseType(List<Integer> courseType) {
	this.courseType = courseType;
}
public List<Integer> getLevel() {
	return level;
}
public void setLevel(List<Integer> level) {
	this.level = level;
}
public List<Integer> getPublisher() {
	return publisher;
}
public void setPublisher(List<Integer> publisher) {
	this.publisher = publisher;
}
public List<Integer> getCourse() {
	return course;
}
public void setCourse(List<Integer> course) {
	this.course = course;
}


}

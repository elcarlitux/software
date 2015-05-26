package es.apps.carlos.library;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
//private:
	String tittle;
	String author;
	String gender;
	String description;
	String editorial;
	Date date;
	
//	public:
	Book(String tittle, String author, String gender){
		this.tittle = tittle;
		this.author = author;
		this.gender = gender;
		this.description = null;
		this.editorial = null;
		this.date = new Date();

	}
	Book(String tittle, String author, String gender,String description, String editorial){
		this.tittle = tittle;
		this.author = author;
		this.gender = gender;
		this.description = description;
		this.editorial = editorial;
		this.date = new Date();
	}
	Book(String tittle, String author, String gender,String description, String editorial, Date date){
		this.tittle = tittle;
		this.author = author;
		this.gender = gender;
		this.description = description;
		this.editorial = editorial;
		this.date = date;
	}

	void set_author(String author){
		this.author = author;
	}
	
	void set_gender(String gender){
		this.gender = gender;
	}
	
	void set_description(String description){
		this.description = description;
	}
	
	void set_editorial(String editorial){
		this.editorial = editorial;
	}
	
	void set_date(Date date){
		this.date = date;
	}

	String get_tittle(){
		return this.tittle;
	}
	
	String get_author(){
		return this.author;
	}
	
	String get_gender(){
		return this.gender;
	}
	
	String get_description(){
		return this.description;
	}
	
	String get_editorial(){
		return this.editorial;
	}
	
	Date get_date(){
		return this.date;
	}
}
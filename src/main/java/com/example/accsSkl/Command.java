package com.example.accsSkl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Command {
private Date date = new Date(/*new Date().getYear(), 0, 1*/);
private Long idDoc;
private Long idSticker;

public String getDate() {
	return new SimpleDateFormat("yyyy-MM-dd").format(date);
}

public void setDate(String date) {
	try {
		this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}
	catch(Exception e) {
	}
}

public Long getIdDoc() {
	return idDoc;
}

public void setIdDoc(Long idDoc) {
	this.idDoc = idDoc;
}

public Long getIdSticker() {
	return idSticker;
}

public void setIdSticker(Long idSticker) {
	this.idSticker = idSticker;
}


}

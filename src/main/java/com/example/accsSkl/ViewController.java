package com.example.accsSkl;

 

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.accsSkl.Sticker.Sticker;
import com.example.accsSkl.Sticker.StickerRepository;

import com.example.accsSkl.Config;
 

@RestController("/view")

public class ViewController {

 

@Autowired
StickerRepository stickerRepository;

 

 

@GetMapping
public ModelAndView get() throws ParseException {

	return foobarPost(new Command());

}

@PostMapping(params="action=filter")
public ModelAndView foobarPost(

    @ModelAttribute("command") Command command ) throws ParseException {

ModelAndView mv = new ModelAndView();



mv.addObject("stickers", stickerRepository.findByNumberOfOrderAndDateAfter(command.getIdDoc(),command.getIdSticker(), Config.getStartOfDayLong(new SimpleDateFormat("yyyy-MM-dd").parse(command.getDate()))));

mv.addObject("command", command);
mv.setViewName("k");

return mv;

}

 

@PostMapping(params="action=print")
public Object pdfGet(HttpServletRequest request,
		
		@ModelAttribute("command") Command command,

        HttpServletResponse response) throws IOException, ParseException {
	List<Sticker> stickers = stickerRepository.findByNumberOfOrderAndDateAfter(command.getIdDoc(),command.getIdSticker(), Config.getStartOfDayLong(new SimpleDateFormat("yyyy-MM-dd").parse(command.getDate())));
	
	if (stickers.isEmpty()) {
		return foobarPost(command);
	}
	else {
		ByteArrayInputStream bis = GeneratePdfReport.stickerReport(stickers);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=stickers.pdf");

		return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
	}
}

}
package com.example.accsSkl;

import com.example.accsSkl.Sticker.Sticker;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


	public class GeneratePdfReport {
	    public class Rotate extends PdfPageEventHelper {
	    	 
	        protected PdfNumber orientation = PdfPage.PORTRAIT;
	 
	        public void setOrientation(PdfNumber orientation) {
	            this.orientation = orientation;
	        }
	 
	        @Override
	        public void onStartPage(PdfWriter writer, Document document) {
	            writer.addPageDictEntry(PdfName.ROTATE, orientation);
	        }
	    }

	    public static ByteArrayInputStream stickerReport(List<Sticker> responce) throws IOException {

	        Document document = new Document(PageSize.A4.rotate());
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {
	            Font headFont = FontFactory.getFont(Config.HEADFONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	            Font baseFont = FontFactory.getFont(Config.FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	            headFont.setSize(14);
	            headFont.setStyle(Font.UNDERLINE);
	            Font smallFont = FontFactory.getFont(Config.FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	            smallFont.setSize(10);
	            
                Map<EncodeHintType, Object> hints = new HashMap<>();

                hints.put(EncodeHintType.CHARACTER_SET, "ISO-8859-1");
                hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
	            
	        	PdfPTable table = new PdfPTable(6);
	        	table.setWidthPercentage(100);
	            table.setWidths(new int[]{ 8, 10, 12, 8, 10, 12 });
	            table.setHorizontalAlignment(Element.ALIGN_CENTER);

	            for (Sticker sticker : responce) {
	                PdfPCell cell1, cell2, cell3;
	                
	                cell1 = new PdfPCell();
	                cell1.setPaddingTop(10);
	                cell1.setPaddingBottom(10);
	                cell1.setPaddingLeft(2);
	                cell1.setPaddingRight(2);
	                cell1.setBorderWidthLeft(1f);
	                cell1.setBorderWidthTop(1f);
	                cell1.setBorderWidthBottom(1f);
		            cell1.setBorder(PdfPCell.LEFT | PdfPCell.TOP | PdfPCell.BOTTOM);
		            
	                cell2 = new PdfPCell();
	                cell2.setPaddingTop(10);
	                cell2.setPaddingBottom(10);
	                cell2.setBorderWidthTop(1f);
	                cell2.setBorderWidthBottom(1f);
		            cell2.setBorder(PdfPCell.TOP | PdfPCell.BOTTOM);
		            
	                cell3 = new PdfPCell();
	                cell3.setPaddingTop(10);
	                cell3.setPaddingBottom(10);
	                cell3.setBorderWidthRight(1f);
	                cell3.setBorderWidthTop(1f);
	                cell3.setBorderWidthBottom(1f);
	                cell3.setBorder(PdfPCell.RIGHT | PdfPCell.TOP | PdfPCell.BOTTOM);
	                cell3.setVerticalAlignment(Element.ALIGN_TOP);
	                cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                
	                cell1.addElement(new Phrase("Закройщик 1", baseFont));
	                cell1.addElement(new Phrase("_____________", baseFont));
	                cell1.addElement(new Phrase("Закройщик 2", baseFont));
	                cell1.addElement(new Phrase("_____________", baseFont));
	                cell1.addElement(new Phrase("Закройщик 3", baseFont));
	                cell1.addElement(new Phrase("_____________", baseFont));
	                cell1.addElement(new Phrase("Сапожник", baseFont));
	                cell1.addElement(new Phrase("_____________", baseFont));
	                cell2.setVerticalAlignment(Element.ALIGN_TOP);
	                table.addCell(cell1);
	                
	                cell2.addElement((new Phrase("Дата: "+sticker.getDateOfTrace().toString(), headFont)));
	                cell2.addElement(new Phrase("Пар в кор.: ", baseFont));
	                cell2.addElement((new Paragraph(String.valueOf(sticker.getQuantity()), headFont)));
	                cell2.addElement((new Paragraph("Размерный ряд: ", baseFont)));
	                cell2.addElement((new Paragraph(sticker.getSizing_row().toString(), headFont)));
	                cell2.addElement((new Paragraph("Модель: ", baseFont)));
	                cell2.addElement((new Paragraph(sticker.getModel().toString(), headFont)));
	                cell2.addElement((new Paragraph("Заказчик: ", baseFont)));
	                cell2.addElement((new Phrase(sticker.getClient().toString(), headFont)));
	                cell2.setVerticalAlignment(Element.ALIGN_TOP);
	                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell2);
	                

	                cell3.addElement((new Phrase("№ Заказа: "+sticker.getNumberOfOrder(), headFont)));
	                cell3.addElement((new Phrase("Код док.: "+sticker.getIdDoc(), baseFont)));
	                cell3.addElement((new Phrase("Ростовка: "+sticker.getSizing().toString(), baseFont)));
	                
	                String code = new String ("AS."+sticker.getIdDoc().toString()+"."+sticker.getIdQRCode().toString()+"."
	                +sticker.getIdSticker().toString()+"."+String.valueOf(sticker.getQuantity()));
	                BarcodeQRCode barcodeQRCode = new BarcodeQRCode(code, 1000, 1000, hints)  ;//hints
	                Image codeQrImage = barcodeQRCode.getImage();
	                codeQrImage.scaleAbsolute(150, 150);
	                cell3.addElement(codeQrImage);
	                
	                Paragraph preface = new Paragraph(code, smallFont);
	                preface.setAlignment(Element.ALIGN_CENTER);
	                
	                cell3.addElement(preface);
	                table.addCell(cell3);
	            }
	            
	            if (responce.size() % 2 == 1) {
	            	PdfPCell emptyCell = new PdfPCell();
	            	emptyCell.setBorderWidth(0);
	            	table.addCell(emptyCell);
	            	table.addCell(emptyCell);
	            	table.addCell(emptyCell);
	            }

	            PdfWriter.getInstance(document, out);
	            document.open();
	            
	            document.add(table);
	            
	            document.close();
	            
	        } catch (DocumentException ex) {
	        
	            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
	        }

	        return new ByteArrayInputStream(out.toByteArray());
	    }
	}


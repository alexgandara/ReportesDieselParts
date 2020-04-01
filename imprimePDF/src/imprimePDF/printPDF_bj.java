package imprimePDF;




import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;






public class printPDF_bj {
	
	
	//private static PdfWriter writer;


	
	public static final String FONT = ".\\resources\\fonts\\Consolas.ttf";
	public static final String FONT_AN = ".\\resources\\fonts\\arial-narrow.ttf";
	public static final String FONT_BOLD = ".\\resources\\fonts\\FrankfurtGothic-Bold-Italic.ttf";
	
	// private static String FILE = "c:/temp/FirstPdf.pdf";
	
	public static void imp_factura(String _file_xml, factura_cabecera Cabecera, factura_detalle[] Detalle, int _lineas_de_la_factura, String _file_pdf, String _file_jpg) throws DocumentException, IOException {
		//String reportePDF = ".\\data\\20525719953\\05_pdfs\\xxx.pdf"; 
		
		
		
		String reportePDF = _file_pdf;
		 // 
		String formato_factura = _file_jpg;
		
		
	
        
		 	Document document = new Document();
	        // step 2
	       
	        
	       // Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
          //  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
            
            PdfWriter writer =
    	            PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
            
	        // step 3
	        document.open();
	        
	        BaseFont bf = BaseFont.createFont(FONT,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        BaseFont bf_bold = BaseFont.createFont(FONT_BOLD,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        
	        Font console = new Font(bf, 8);
	        
	
			Image img = Image.getInstance(formato_factura);
			img.scalePercent(23);
			img.setAbsolutePosition(0, 70); // horizontal , vertical
			document.add(img);
	       
	        // step 4
	     
	
	        
	         
	        // ruc  emisor
	        PdfContentByte canvas = writer.getDirectContent(); //  getDirectContentUnder();
	        writer.setCompressionLevel(0);
	        
	        
	        
			// calcula donde poner la razon social para uqe quede centrado
	 		int _tam_razon_social = Cabecera.get_razon_social_emisor().trim().length();
	 		System.out.println("tamaño:"+_tam_razon_social);
	 		String _razon_social = "";
	 		int _sobra = 0;
	 		
	 		if (_tam_razon_social>40) {
	 			_razon_social=Cabecera.get_razon_social_emisor().trim().substring(0, 40);
	 		} else {
	 			_sobra=(int)(40-_tam_razon_social)/2;
	 			System.out.println("sobre:"+_sobra);
	 			_sobra=_sobra*8;
	 			System.out.println("sobre:"+_sobra);
	 			
	 			_razon_social=Cabecera.get_razon_social_emisor().trim();	
	 		}
	 		
	 		
	 		// NOMBRE DEL DOCUMENTO
	 		canvas.saveState();                               // q
	 		canvas.beginText();                               // BT
	 		canvas.moveText(40+_sobra, 740);                         // 36 788 Td
	 		canvas.setFontAndSize(bf, 15); // /F1 12 Tf
	 	//	canvas.setColorFill(BaseColor.WHITE);
//	 		canvas.showText(_razon_social);	        // (Hello World)Tj
	 		canvas.endText();                                 // ET
	 		canvas.restoreState();                            // Q

	 		
	


	        
	        
	        
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(464, 783);                         // 36 788 Td
	        //canvas.setFontAndSize(BaseFont.createFont(), 11); // /F1 12 Tf
	        canvas.setFontAndSize(bf, 11);
	        canvas.showText(Cabecera.get_ruc_emisor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        // 200, 765
	 
	        
	        
	    
	        // NOMBRE DEL DOCUMENTO
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(405, 765);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 13); // /F1 12 Tf
	        canvas.showText(Cabecera.get_tipo_doc_descripcion());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	  
	
	        
	        // serie
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(465, 749);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 10); // /F1 12 Tf
	        canvas.showText(Cabecera.get_serie());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	
	        
	        
	 	        
	        
	        
	        // fecha de emision del docto
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(480, 670);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(Cabecera.get_IssueDate());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	    
	        // fecha de emision del docto
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(480, 655);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(Cabecera.get_ReferenceDate());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
   
	   
	  	     Paragraph _linea00 = new Paragraph(8);
	  	     Chunk _espacio = new Chunk(" ");
	  	     _linea00.add(_espacio);
	  	     
	   
	        //special font sizes
	        BaseFont bf_arial = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        Font arial = new Font(bf_arial, 8);

	  	     
	  	     
	  	     for (int z = 1; z<=25; z++) {
	  	    	 document.add(_linea00);
	  	     }
	  	     
	  	     
	  	     Paragraph _linea01 = new Paragraph(10);
	  	     
	  	     int _ultima = 0;
  	    	 Chunk _linea_recta = new Chunk("                                                                                                                     ");	  	     
  	  	     _linea_recta.setFont(arial); 
  	  	     
  	  	     System.out.println("Linea_ _ _ _ _ _ _ _ _ _ _: " + _lineas_de_la_factura);
  	    	 for (int i=0; i<_lineas_de_la_factura+1; i++) {
	  	     
	  	    
	  	     
	  	    	 Chunk _linea_anulado = new Chunk(Detalle[i].get_linea_anulado());
	  	    	 Chunk _tipo_doc_anulado = new Chunk(Detalle[i].get_tipo_doc_anulado());
	  	    	 Chunk _serie_doc_anulado = new Chunk(Detalle[i].get_serie_doc_anulado());
	  	    	 Chunk _documento_anulado = new Chunk(Detalle[i].get_documento_anulado());
	  	    	 Chunk _razon_anulado = new Chunk(Detalle[i].get_razon_anulado());
	  	    	 

	  	    	 _espacio.setFont(arial);
	  	    	 //  _lineas_de_la_factura
	  	     
	  	    	 _linea01.add(_espacio);
	  	    
	  	     
	  	    	_linea_anulado.setFont(arial);
	  	    	_tipo_doc_anulado.setFont(arial);
	  	    	_serie_doc_anulado.setFont(arial);
	  	    	_documento_anulado.setFont(arial);
	  	    	_razon_anulado.setFont(arial);
	  	     
	  	    	
	  	    	
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_linea_anulado);
	  	    	_linea01.add(_espacio);
	  	    		
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_tipo_doc_anulado);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);	 
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);	 	  	    	
	  	    	
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	
	  	    	
	  	    	
	  	    	_linea01.add(_serie_doc_anulado);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	
	  	    	
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_documento_anulado);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	
	  	    	
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	_linea01.add(_espacio);
	  	    	
	  	    	_linea01.add(_espacio);
	  	    	
	  	    	_linea01.add(_razon_anulado);

	  	     
	  	    	 
	 	  	    	
	  	    
	  	     
	  	     
	  	    	 document.add(_linea01);
	  	
	  	    	 _ultima=1;
	  	     
	  	     
	  	   
	  		
	  	     
	 
	  	     
	  	     _linea01.removeAll(_linea01);
	  	     }
	  	     document.add(_linea_recta);

	  	     
	  	     
		  	       
	  	   	
	  	     
	        
	        // step 5
	        document.close();		
		
		
		
	}

	
	
	
	  

}

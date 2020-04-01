package imprimePDF;



import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;



public class printPDF_nd {
	
	
	//private static PdfWriter writer;

	public static final String FONT_BOLD = ".\\resources\\fonts\\FrankfurtGothic-Bold.ttf";
	public static final String FONT_CALIBRI = ".\\resources\\fonts\\calibri.ttf";
	public static final String FONT_ARIAL_NARROW = ".\\resources\\fonts\\arial-narrow.ttf";

	
	public static final String FONT = ".\\resources\\fonts\\Consolas.ttf";
	// private static String FILE = "c:/temp/FirstPdf.pdf";
	
	public static void imp_factura(String _file_xml, factura_cabecera Cabecera, factura_detalle[] Detalle, int _lineas_de_la_factura, String _file_pdf,  String _file_jpg) throws DocumentException, IOException {
		//String reportePDF = ".\\data\\20525719953\\05_pdfs\\xxx.pdf"; 
		
		
		String reportePDF = _file_pdf;
		 // 
		// String formato_factura = ".\\data\\20175077023\\10_formatos\\CartaCompleta_Pruebas_NC.jpg"; // .gif and .jpg are ok too!
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
	        

	        BaseFont bf_bold = BaseFont.createFont(FONT_BOLD,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        BaseFont bf_cal = BaseFont.createFont(FONT_CALIBRI,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        BaseFont bf_arial_na = BaseFont.createFont(FONT_ARIAL_NARROW,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        
	        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        Font console = new Font(bf, 7);
		        
	        //special font sizes
	  //      BaseFont bf_arial = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	  //      Font arial = new Font(bf_arial, 8);

	        
	        
			Image img = Image.getInstance(formato_factura);
			img.scalePercent(23);
			img.setAbsolutePosition(0, 70); // horizontal , vertical
			document.add(img);
	       
	        // step 4
	     
	    
	        
	         
	        // ruc  emisor
	        PdfContentByte canvas = writer.getDirectContent(); //  getDirectContentUnder();
	        writer.setCompressionLevel(0);
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        // NOMBRE DEL DOCUMENTO
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(392, 762);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 15); // /F1 12 Tf
	        canvas.setColorFill(BaseColor.WHITE);
	        canvas.showText(Cabecera.get_tipo_doc_descripcion());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	  
	       
	        
	 		// ruc  emisor
	

	 		// calcula donde poner la razon social para uqe quede centrado
	 		int _tam_razon_social = Cabecera.get_razon_social_emisor().trim().length();
	 		System.out.println("tama�o:"+_tam_razon_social);
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

	 		
	 		// calcula donde poner la razon social para uqe quede centrado
	 		int _tam_direccion = Cabecera.get_direccion_emisor().trim().length();
	 		String _direccion = "";
	 		_sobra = 0;
 		
	 		if (_tam_direccion>46) {
	 			_direccion=Cabecera.get_razon_social_emisor().trim().substring(0, 40);
	 		} else {
	 			_sobra=(int)(46-_tam_direccion)/2;
	 			_sobra=_sobra*7;
	 					 			
	 			_direccion=Cabecera.get_direccion_emisor().trim();	
	 		}
	 		
	 		
	 		
	 	// NOMBRE DEL DOCUMENTO
	 		canvas.saveState();                               // q
	 		canvas.beginText();                               // BT
	 		canvas.moveText(40+_sobra, 715);                         // 36 788 Td
	 		canvas.setFontAndSize(bf, 13); // /F1 12 Tf
	 	//	canvas.setColorFill(BaseColor.WHITE);
//	 		canvas.showText(_direccion);	        // (Hello World)Tj
	 		canvas.endText();                                 // ET
	 		canvas.restoreState();                            // Q


	 		

	        
	        
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(464, 731);                         // 36 788 Td
	        //canvas.setFontAndSize(BaseFont.createFont(), 11); // /F1 12 Tf
	        canvas.setFontAndSize(bf, 11);
	        canvas.showText(Cabecera.get_ruc_emisor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        // 200, 765
	        

	   
	     	        
	        
	        
	        // razon social del emisor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(110, 705);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 14); // /F1 12 Tf
	   //     canvas.showText(Cabecera.get_razon_social_emisor());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        


	        
	        
	        
	  
	
	        
	        // serie
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(464, 714);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 11); // /F1 12 Tf
	        canvas.showText(Cabecera.get_serie());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	
	        
	        
	        
	        // folio
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(464, 697);                       // 36 788 Td
	        canvas.setFontAndSize(bf, 11); // /F1 12 Tf
	        canvas.showText(Cabecera.get_folio());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        
	   
	        
	        

	        // RAZON SOCIAL  del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(30, 260+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("Motivo de Anulaci�n: "+Cabecera.get_motivo_de_anulacion());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	        
	        
	        
	        // fecha de emision del docto
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(420, 300+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("Fecha de Emision: "+Cabecera.get_fecha());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q


	        //moneda
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(420, 290+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("Moneda: "+Cabecera.get_moneda());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        //IGV
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(420, 280+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("% IGV: "+"18 %");	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	        
	        
	        
	       
        
	        // documento relacionado
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(420, 260+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        canvas.showText("Doc. Relacionado:"+Cabecera.get_doc_relacionado());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	  	     
	        // cantidad en letra
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(50, 148);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	        if (Cabecera.get_moneda().equals("PEN")) {
	        	canvas.showText("Cantidad en Letra:"+Cabecera.get_total_letra()+" Soles.");	        // (Hello World)Tj
	        } else {
	        	canvas.showText("Cantidad en Letra:"+Cabecera.get_total_letra()+" Dolares.");	        // (Hello World)Tj
	        }
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 	
	        
	        // TOTAL exonerado
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(497, 128);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
	     // canvas.showText("Total:      "+Cabecera.get_total());	        // (Hello World)Tj
	        
	        
    	    if (Cabecera.get_moneda().equals("USD")) {
 	        	canvas.showText(" $ "+Formato.dinero(Cabecera.get_total_exonerado()));
 	        } else {
 	        	canvas.showText("s/ "+Formato.dinero(Cabecera.get_total_exonerado()));
 	        }
	        
	        //canvas.showText(Formato.dinero(Cabecera.get_total()));
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	       

	        
	        
	        
	        
	        
	        
	        
	        
		     // TOTAL subtotal
		        canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(497, 113);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
		        if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
		        	
		        	
		    	    if (Cabecera.get_moneda().equals("USD")) {
	     	        	canvas.showText(" $ "+Formato.dinero(Cabecera.get_total()));
	     	        } else {
	     	        	canvas.showText("s/ "+Formato.dinero(Cabecera.get_total()));
	     	        }
	    
		        	
		        }
		        
		        	else
		        {
		        		
		        	    if (Cabecera.get_moneda().equals("USD")) {
		     	        	canvas.showText(" $ "+Formato.dinero(Cabecera.get_subtotal()));
		     	        } else {
		     	        	canvas.showText("s/ "+Formato.dinero(Cabecera.get_subtotal()));
		     	        }
		        		
		        		// canvas.showText(Formato.dinero(Cabecera.get_subtotal()));			
		        }
		        
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
		        
		        
		        
		        
		        
		        // TOTAL subtotal
		        canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(497, 98);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
		        if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
		        	if (Cabecera.get_moneda().equals("USD")) {
	     	        	canvas.showText(" $ "+Formato.dinero(Cabecera.get_total_igv()));
	     	        } else {
	     	        	canvas.showText("s/ "+Formato.dinero(Cabecera.get_total_igv()));
	     	        }
	     	  
		        	
		        	
		        }
		        
		        	else
		        {
		        	    if (Cabecera.get_moneda().equals("USD")) {
		     	        	canvas.showText(" $ "+Formato.dinero(Cabecera.get_total_igv()));
		     	        } else {
		     	        	canvas.showText("s/ "+Formato.dinero(Cabecera.get_total_igv()));
		     	        }
		     	  
		        	//	canvas.showText(Formato.dinero(Cabecera.get_total_igv()));	
		        }
		        
		        
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q  
		        
		        // TOTAL DE LA FACTURAS
		        canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(497, 83);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
		     // canvas.showText("Total:      "+Cabecera.get_total());	        // (Hello World)Tj
		        
		        
        	    if (Cabecera.get_moneda().equals("USD")) {
     	        	canvas.showText(" $ "+Formato.dinero(Cabecera.get_total()));
     	        } else {
     	        	canvas.showText("s/ "+Formato.dinero(Cabecera.get_total()));
     	        }
		        
		        //canvas.showText(Formato.dinero(Cabecera.get_total()));
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
		       
		        
		        

	  	       
       
	        
        
		       
	        
	        
		     // resumen hash
		        canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(110, 87);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
		        canvas.showText("Hash:  "+Cabecera.get_codigo_hash());	        // (Hello World)Tj
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
		        
		  	    BarcodePDF417 pdf417 = new BarcodePDF417();
		  	    String text = Cabecera.get_sello();
		  	    
		  	       pdf417.setText(text);
		  	       Image barras = pdf417.getImage(); 
		  	       barras.setAbsolutePosition(75, 95); 
		  	       document.add(barras);
		  	      
		  	       
			        //special font sizes
			        BaseFont bf_arial = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			        Font arial = new Font(bf_arial, 7);
			        	      
			  	     Paragraph _linea00 = new Paragraph();
			  	     Chunk _espacio = new Chunk(" ",arial);
			  	     _linea00.add(_espacio);
			  	     
			  	     
		  	       
		  	       
		  	    	       
	  	       
		        //special font sizes
		//        BaseFont bf_arial = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		 //       Font arial = new Font(bf_arial, 7);
		        	      
		 // 	     Paragraph _linea00 = new Paragraph();
		  //	     Chunk _espacio = new Chunk(" ",arial);
		  	     _linea00.add(_espacio);
		  	     
		  	     
		  	     for (int z = 1; z<=12; z++) {
		  	    	 document.add(_linea00);
		  	     }
		  	     
		  	     
		  	     Paragraph _linea01 = new Paragraph(8);
		  	    Cabecera.set_subtotal(0);
		  	    Cabecera.set_total_igv(0);
		  	    Cabecera.set_total(0);
		  	   
		  	     for (int i=0; i<_lineas_de_la_factura; i++) {
		  	    	
		  	    	
		  	   		 if (Detalle[i].get_unidad()==null) {
	  	    			 Detalle[i].set_unidad("KG");
	  	    		 }
	  	     
	  	    		 if (Detalle[i].get_unidad().equals("KGM")) {
	  	    			 Detalle[i].set_unidad("KG");
	  	    		 }
	  	     
	  	    		 if (Detalle[i].get_unidad().equals("MTR")) {
	  	    			 Detalle[i].set_unidad("MTS");
	  	    		 }
	  	     
	  	    		 if (Detalle[i].get_unidad().equals("NIU")) {
	  	    			 Detalle[i].set_unidad("UNI");
	  	    		 }
	  	     
	  	    		 if (Detalle[i].get_unidad().equals("BX")) {
	  	    			 Detalle[i].set_unidad("CJ");
	  	    		 }
	  	    
	  	    		 if (Detalle[i].get_unidad().equals("PF")) {
	  	    			 Detalle[i].set_unidad("PAL");
	  	    		 }
	  	    
	  	 
		  	    	
		  	    		 
		  	    		 
		  	    //		Cabecera.set_subtotal(Cabecera.get_subtotal()+Detalle[i].get_subtotal_sin_igv());
			  	//    	 Cabecera.set_total_igv(Cabecera.get_total_igv()+Detalle[i].get_igv());
			  	//    	 Cabecera.set_total(Cabecera.get_subtotal()+Cabecera.get_total_igv());
			  	    	 
			  	    	 
		  	    		 Chunk _producto = new Chunk(Formato.padRight(Detalle[i].get_codigo(),10));
		  	    		 Chunk _descripcion = new Chunk(Formato.padRight(Formato.cadena60(Detalle[i].get_descripcion()),60));
		  	    		 Chunk _unidad_de_medida = new Chunk(Formato.cadena5(Detalle[i].get_unidad()));
		  	    		 Chunk _cantidad = new Chunk(Formato.dinero(Detalle[i].get_cantidad()));
		  	    		 Chunk _precio = new Chunk(Formato.dinero4(Detalle[i].get_precio_unitario()));
		  	    		 Chunk _importe = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));
		  	    		 Chunk _importe_sin_igv = new Chunk(Formato.dinero(Detalle[i].get_subtotal_sin_igv()));
		  	    		 Chunk _igv = new Chunk(Formato.dinero(Detalle[i].get_igv()));
		  	    		 
	  	     
		  	    		 _espacio.setFont(arial);
		  	    		 _producto.setFont(arial);
	  	     
		  	    		 _descripcion.setFont(arial);
		  	    		 _unidad_de_medida.setFont(arial);
		  	    		 _cantidad.setFont(arial);
		  	    		 _precio.setFont(arial);
		  	    		 _importe.setFont(arial);
		  	    		 _importe_sin_igv.setFont(arial);
		  	    		_igv.setFont(arial);
		  	    		 
		  	    		
		  	    		
		  	    		
		  	    		if (Detalle[i].get_codigo().equals(".")) {
	  	    				_linea01.add(_espacio);
	  	    				_linea01.add(_espacio);
	  	    				_linea01.add(_espacio);
	  	    				_linea01.add(_espacio);
	  	    				_linea01.add(_espacio);
	  	    				_linea01.add(_espacio);
	  	    				_linea01.add(_espacio);
	  	    				_linea01.add(_espacio);
	  	    				_linea01.add(_espacio);
	  	    				
	  	    			 } else {
	  	    				 
	  	    			 _linea01.add(_producto);
	  	    			 }
		  	    		 // _linea01.add(_producto);
		  	    
		  	    		 _linea01.add(_descripcion);
		  	    		 _linea01.add(_espacio);
		  	    		 _linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		 _linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		 _linea01.add(_espacio);
		  	    		 
		  	    		 _linea01.add(_cantidad);
		  	    		 
		  	    		 _linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		 _linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		 		  	    		 
		  	    		
		  	    		 _linea01.add(_unidad_de_medida);
		  	    		_linea01.add(_espacio);

		  	    		

	
		  	    		 _linea01.add(_precio);
		  	    		 _linea01.add(_espacio);
		  	    		 _linea01.add(_igv);

		  	    		 
		  	    		 
		  	    		 _linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		_linea01.add(_espacio);
		  	    		 _linea01.add(_importe);
	  	     
		  	    		 document.add(_linea01);
		  	    		 _linea01.removeAll(_linea01);
	
	  	     }
		  	     
		  	     
		  	     
	   	
			      

	        // step 5
	        document.close();		
		
	
		
	}

	
	
	
	  

}

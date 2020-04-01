package imprimePDF;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class readXML_bj {
	
	public static factura_cabecera Cabecera = new factura_cabecera();
	public static factura_detalle[] Detalle = new factura_detalle[100];
	public static int _lineas_de_la_factura=0;
	
	public static void main(String _file_name, parametros misParametros, param misParam) {
		
	//	String _file_xml="R:\\conector\\data\\20525378358\\03_xmls_con_firma\\20525378358-01-F001-0000001.xml";
		
	
		
		String _file= _file_name;
		String _correo_destino = "";
		String _correo_destino_cc = "";
	
		
		
		String _file_xml = misParametros.get_ruta_xml_con_firma()+_file+".xml";;
		String _file_respuesta = misParametros.get_ruta_respuestas()+"r-"+_file+".xml";
		String _file_pdf = misParametros.get_ruta_pdfs()+_file+".pdf";
		String _file_html = misParametros.get_ruta_formatos_htm();
	//	String _file_html = "S:\\conecta.global\\data\\20480617704\\10_formatos\\formato.htm";
		String _file_zip_respuesta = misParametros.get_ruta_respuestas()+"R-"+_file+".xml";;
		String _file_jpg = misParametros.get_ruta_formatos();
		
		
		
		
	
		
		
		File fXmlFile = new File(_file_xml);
		try {
			
			
			
			
			String raya="----------------------------------------------------------------";
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			
			doc.getDocumentElement().normalize();
			
	//		NodeList nList = doc.getElementsByTagName("Invoice");
			
			System.out.println("DATOS DEL DOCUMENTO");
			
			System.out.println(raya);
			
			Cabecera.set_descripcion_documento(doc.getDocumentElement().getNodeName());	
			System.out.println("Documento _ _ _ _ _ _ : " + Cabecera.get_descripcion_documento());
			
			Cabecera.set_tipo_doc_descripcion("FACTURA");
			if (Cabecera.get_descripcion_documento().equals("VoidedDocuments")) {
				Cabecera.set_tipo_doc_descripcion("DOCUMENTOS ANULADOS");
				Cabecera.set_Ruc_Dni("    ");
			} 
			
			
			
			
			
			// cbc:ID	//para serie y folio
	//		NodeList nList_id = doc.getElementsByTagName("cbc:ID");
	//		Node nNode_id = nList_id.item(6);
			//System.out.println("" + nNode_id.getNodeName());
			
	//		String _temp = nNode_id.getTextContent();
	//		Cabecera.set_serie(_temp.substring(0,4));
	//		Cabecera.set_folio(_temp.substring(5,_temp.length()));
			
			
			
			
			
	
			String _temp;
			_temp="000000000000";
		
			if  (Cabecera.get_descripcion_documento().equals("VoidedDocuments")) {
				// cbc:ID	//para serie y folio
				NodeList nList_id = doc.getElementsByTagName("cbc:ID");
				Node nNode_id = nList_id.item(0);
				//System.out.println("" + nNode_id.getNodeName());
				_temp = nNode_id.getTextContent();
			}
			
			
			Cabecera.set_serie(_temp);
			System.out.println("Serie _ _ _ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_serie());

			
			
			if  (Cabecera.get_descripcion_documento().equals("VoidedDocuments")) {
				// cbc:ID	//para serie y folio
				NodeList nList_id = doc.getElementsByTagName("cbc:ID");
				Node nNode_id = nList_id.item(1);
				//System.out.println("" + nNode_id.getNodeName());
				_temp = nNode_id.getTextContent();
			}
			
			System.out.println(_temp);
			Cabecera.set_ruc_emisor(_temp);
			System.out.println("Ruc Emisor _ _ _ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_ruc_emisor());


			
			// cbc:IssueDate
			NodeList nList_IssueDate = doc.getElementsByTagName("cbc:IssueDate");
			Node nNode_IssueDate = nList_IssueDate.item(0);
			String _fecha = nNode_IssueDate.getTextContent();
			
			String _Dia = "";
			String _Mes = "";
			String _Ano = "";
			_Dia = _fecha.substring(8, 10);  //2016.09.17  2016-11-30
			_Mes = _fecha.substring(5, 7);  //2016.09.17  0123456789
			_Ano = _fecha.substring(0, 4);             // 1234567890
			Cabecera.set_IssueDate(_Dia+"/"+_Mes+"/"+_Ano);
			System.out.println("Fecha del Docto _ _ _ _ _ _ _ _ _ _ _: " + Cabecera.get_IssueDate());	
			

			
			// cbc:IssueDate
			NodeList nList_ReferenceDate = doc.getElementsByTagName("cbc:ReferenceDate");
			Node nNode_ReferenceDate = nList_ReferenceDate.item(0);
  		    _fecha = nNode_ReferenceDate.getTextContent();
			
			_Dia = "";
			_Mes = "";
			_Ano = "";
			_Dia = _fecha.substring(8, 10);  //2016.09.17  2016-11-30
			_Mes = _fecha.substring(5, 7);  //2016.09.17  0123456789
			_Ano = _fecha.substring(0, 4);             // 1234567890
			Cabecera.set_ReferenceDate( _Dia+"/"+_Mes+"/"+_Ano);
			System.out.println("Fecha de Baja del Docto _ _ _ _ _ _ _: " + Cabecera.get_ReferenceDate());
			

			
		
			
			// cac:PartyName
			NodeList nList_PartyName = doc.getElementsByTagName("cac:PartyName");
			Node nNode_PartyName = nList_PartyName.item(0);
			Cabecera.set_razon_social_emisor(nNode_PartyName.getTextContent());
			System.out.println("Razon Social del Emisor_ _ _ _ : " + Cabecera.get_razon_social_emisor());
			
	
			
			
			System.out.println(raya);
			
				
			// DigestValue
			NodeList nList_DigestValue = doc.getElementsByTagName("DigestValue");
			Node nNode_DigestValue = nList_DigestValue.item(0);
			Cabecera.set_codigo_hash(nNode_DigestValue.getTextContent());
			System.out.println("Codigo Hash_ _ _ _ _ _ _ _ _ : " + Cabecera.get_codigo_hash());
			
			
			// SignatureValue
			NodeList nList_SignatureValue = doc.getElementsByTagName("SignatureValue");
			Node nNode_SignatureValue = nList_SignatureValue.item(0);
			Cabecera.set_signature(nNode_SignatureValue.getTextContent());
			//System.out.println("Codigo Hash_ _ _ _ _ _ _ _ _ : " + Cabecera.get_codigo_hash());
			
			
			
			 
			 
			 
			 
			
			System.out.println(raya);
			System.out.println("Detalle del Documento_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
			
			// cbc:ID	cantidad
			NodeList nList_linea = doc.getElementsByTagName("sac:VoidedDocumentsLine");
			
		
			
			System.out.println("numero de lineas _: " + nList_linea.getLength());	
			int _total_linea=nList_linea.getLength();
			
			
			
				
			for (int _linea = 0; _linea < _total_linea; _linea++) {
				NodeList nList_Tipo_Dic = doc.getElementsByTagName("cbc:DocumentTypeCode");
				Detalle[_linea] = new factura_detalle();
				Node nNode_Tipo_Dic = nList_Tipo_Dic.item(_linea);
				Detalle[_linea].set_tipo_doc_anulado(nNode_Tipo_Dic.getTextContent());
			}
			
			
			for (int _linea = 0; _linea < _total_linea; _linea++) {
				NodeList nList_Doc_Anulado = doc.getElementsByTagName("sac:DocumentSerialID");
				Node nNode_Doc_Anulado = nList_Doc_Anulado.item(_linea);
				Detalle[_linea].set_serie_doc_anulado(nNode_Doc_Anulado.getTextContent());
			}
		
			
			
			
			


			if  (Cabecera.get_descripcion_documento().equals("VoidedDocuments")) {
				// sac:DocumentSerialID
				NodeList nList_Doc = doc.getElementsByTagName("sac:DocumentNumberID");
				for (int _linea = 0; _linea < _total_linea; _linea++) {
					Node nNode_Doc = nList_Doc.item(_linea);
					Detalle[_linea].set_documento_anulado(nNode_Doc.getTextContent());
					}
				}

			if  (Cabecera.get_descripcion_documento().equals("VoidedDocuments")) {
				// sac:DocumentSerialID
				NodeList nList_Razon = doc.getElementsByTagName("sac:VoidReasonDescription");
				for (int _linea = 0; _linea < _total_linea; _linea++) {
					Node nNode_Razon = nList_Razon.item(_linea);
					Detalle[_linea].set_razon_anulado(nNode_Razon.getTextContent());
					}
				}
		

			if  (Cabecera.get_descripcion_documento().equals("VoidedDocuments")) {
				// cbc:LineID
				NodeList nList_Line = doc.getElementsByTagName("cbc:LineID");
				for (int _linea = 0; _linea < _total_linea; _linea++) {
					Node nNode_Linea = nList_Line.item(_linea);
					Detalle[_linea].set_linea_anulado(nNode_Linea.getTextContent());
					}
				}
			
			
			
				
			
			
			int _linea_imp2=0;
			for (int _linea_imp=0;_linea_imp<_total_linea;_linea_imp++) {
				_linea_imp2=_linea_imp;
				System.out.println("");
				
				System.out.println("Linea_ _ _ _ _ _ _ _ _ _ _: " + _linea_imp2);
				System.out.println("Tipo Documento_ _ _ _ _ _ : " + Detalle[_linea_imp2].get_tipo_doc_anulado());
				System.out.println("Serie de Doc Anulado_ _ _ : " + Detalle[_linea_imp2].get_serie_doc_anulado());
				System.out.println("Documnto Anulado_ _ _ _ _ : " + Detalle[_linea_imp2].get_documento_anulado());
				System.out.println("Razon de la Anulacion _ _ : " + Detalle[_linea_imp2].get_razon_anulado());
				
				_lineas_de_la_factura=_linea_imp2;
				
				
				
			}
			printPDF_bj.imp_factura(_file_xml, Cabecera, Detalle, _lineas_de_la_factura,_file_pdf, _file_jpg);	

			//		if (Cabecera.get_tipo_doc().substring(1).equals("1")) {
			//	SFSprintPDF_AgroSMFacturaNegociable.imp_factura(_file_xml, Cabecera, Detalle, _lineas_de_la_factura,_file_pdf_fn);
	//		}
			
			System.out.println("Reporte PDF Generado:"+_file_pdf);
			if (_correo_destino=="nada") {
				System.out.println("Correo Vacio, no se envio correo...");
			} else {
				System.out.println("Enviando Correo a "+_correo_destino);
	//			SFSemail.main(_correo_destino,_file_xml,_file_pdf,_file,_correo_destino_cc);
				System.out.println("Correo Enviado.");
			}
			
			
			
			
			//factura.imp_factura(_file_xml, Cabecera, Detalle);
				
			
			//SFSprintPDF.imp_factura(_file_xml, Cabecera, Detalle, _lineas_de_la_factura,_file_pdf);
		} catch (Exception e) {
	  		e.printStackTrace();
    	}

	}
	
	public static boolean isNullOrEmpty(String a) {
		return a == null || a.isEmpty();
		} 
			
	
	
	
	


}

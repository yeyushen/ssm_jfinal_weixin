package com.skywayct.util;

import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

public class IReportView extends JasperReportsMultiFormatView{

    private JasperReport jasperReport;  
    
    public IReportView() {  
        super();  
    }  
  
    protected JasperPrint fillReport(Map<String, Object> model) throws Exception {  
        if (model.containsKey("url")) {  
            setUrl(String.valueOf(model.get("url")));  
            this.jasperReport = loadReport();  
        }  
          
        return super.fillReport(model);  
    }  
      
    protected JasperReport getReport() {  
        return this.jasperReport;  
    }	
}

package com.skowrondariusz.mechanicalprojectmanager.mail;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.skowrondariusz.mechanicalprojectmanager.model.CommercialPart;
import com.skowrondariusz.mechanicalprojectmanager.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

@Component
public class InquiryMailSender implements InquirySender {

    private JavaMailSenderImpl mailSender;
    private InvoiceRepository invoiceRepository;


    public InquiryMailSender(Environment environment, InvoiceRepository invoiceRepository) {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public void sendInquiry(String from, String to, String title, String invoiceId) {
        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText("Dear Sir/Madame" + "\n" + "In the attachment I'm sending inquiry, please send me an offer for specified products" + "\n" + "Sincerly,"  + "\n" + from);
            MimeBodyPart textBodyPart = new MimeBodyPart();
            ByteArrayOutputStream outputStream = null;
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream, invoiceId);
            byte[] bytes =  outputStream.toByteArray();
            helper.addAttachment("kupa.pdf", new ByteArrayResource(bytes));
        } catch (MessagingException e) {
            throw new MailParseException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mailSender.send(message);

//
//        String content = "Please realize my inquiry";
//
//        ByteArrayOutputStream outputStream = null;
////        List<CommercialPart> partsList = this.invoiceRepository.getInvoiceById(Long.valueOf(invoiceId)).getCommercialParts();
////
////        String invoice = partsList.stream()
////                .map(CommercialPart::commercialPartToInquiryString)
////                .collect(Collectors.joining("\n"));
////
////
////        message.setText("Name" + "\t" + "Order Symbol" + "\t" + "Quantity" +"\n" + invoice);
////        this.mailSender.send(message);
//        try {
//            MimeBodyPart textBodyPart = new MimeBodyPart();
//            textBodyPart.setText(content);
//            outputStream = new ByteArrayOutputStream();
//            writePdf(outputStream);
//            byte[] bytes =  outputStream.toByteArray();
//
//            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
//            MimeBodyPart pdfBodyPart = new MimeBodyPart();
//            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
//            pdfBodyPart.setFileName("test.pdf");
//
//            MimeMultipart mimeMultipart = new MimeMultipart();
//            mimeMultipart.addBodyPart(textBodyPart);
//            mimeMultipart.addBodyPart(pdfBodyPart);
//
//
//            this.mailSender.send(message);
//
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (null != outputStream) {
//                try {
//                    outputStream.close();
//                    outputStream = null;
//                } catch (Exception ex) {
//                }
//            }
//        }
    }

    public void writePdf(OutputStream outputStream, String invoiceId) throws Exception {
        Document document = new Document();
        var partsList = this.invoiceRepository.getInvoiceById(Long.valueOf(invoiceId)).getCommercialParts();

        PdfWriter.getInstance(document, outputStream);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        document.open();
        document.addTitle("Inquiry");
        document.addSubject("Inquiry number X");
        document.addKeywords("iText, email");
        document.addAuthor("Dariusz Skowron");
        document.addCreator("Dariusz Skowron");

        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("hello!"));
        document.add(paragraph);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("N°", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Order Symbol", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
        int i = 1;
        for (CommercialPart commercialPart : partsList) {
            table.addCell(String.valueOf(i++));
            table.addCell(commercialPart.getName().toString());
            table.addCell(commercialPart.getOrderSymbol().toString());
            table.addCell(String.valueOf(commercialPart.getQuantity()));
        }
        document.add(table);
        document.close();
    }
}


//    @Override
//    public void sendInquiry(String from, String to, String title, String invoiceId) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(to);
//        message.setSubject(title);
//        List<CommercialPart> partsList = this.invoiceRepository.getInvoiceById(Long.valueOf(invoiceId)).getCommercialParts();
//
//        String invoice = partsList.stream()
//                .map(CommercialPart::commercialPartToInquiryString)
//                .collect(Collectors.joining("\n"));
//
//        message.setText("Name" + "\t" + "Order Symbol" + "\t" + "Quantity" +"\n" + invoice);
//        this.mailSender.send(message);
//
//
//    }


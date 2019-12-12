package com.skowrondariusz.mechanicalprojectmanager.mail;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.skowrondariusz.mechanicalprojectmanager.repository.InvoiceRepository;
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
//        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage message = mailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText("chuj ci w doope");
            MimeBodyPart textBodyPart = new MimeBodyPart();
            ByteArrayOutputStream outputStream = null;
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream);
            byte[] bytes =  outputStream.toByteArray();
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            helper.addAttachment(dataSource.getName(), dataSource);
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

    public void writePdf(OutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.addTitle("Inquiry");
        document.addSubject("Inquiry number X");
        document.addKeywords("iText, email");
        document.addAuthor("Dariusz Skowron");
        document.addCreator("Dariusz Skowron");

        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk("hello!"));
        document.add(paragraph);

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


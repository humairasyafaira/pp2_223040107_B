/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author HUMAIRA
 */


import model.User;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserPdf {

    public void exportPdf(List<User> users) {
        System.out.println(users.size());
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(System.getProperty("user.dir") + File.separator + "users.pdf"));

            document.open();

            float[] columnDefinitionSize = {33.33F, 33.33F, 33.33F};
            PdfPTable table = new PdfPTable(columnDefinitionSize);
            table.getDefaultCell().setBorder(1);
            table.setHorizontalAlignment(0);
            table.setTotalWidth(document.getPageSize().getWidth() - 72);
            table.setLockedWidth(true);

            table.addCell(new Phrase("No"));
            table.addCell(new Phrase("Name"));
            table.addCell(new Phrase("Email"));

            int no = 1;
            for (User user : users) {
                table.addCell(new Phrase(String.valueOf(no++)));
                table.addCell(new Phrase(user.getName()));
                table.addCell(new Phrase(user.getEmail()));
            }

            document.add(table);

        } catch (DocumentException | IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            String filePath = System.getProperty("user.dir") + File.separator + "users.pdf";

            System.out.println("File PDF disimpan di: " + System.getProperty("user.dir") + File.separator + "users.pdf");

            document.close();
        }
    }
}

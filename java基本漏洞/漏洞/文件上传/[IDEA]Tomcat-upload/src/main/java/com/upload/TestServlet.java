package com.upload;



import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
@WebServlet("/Test")
public class TestServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse
            response) throws IOException {
        String path = "./";
        try {
            ServletFileUpload servletFileUpload = new
                    ServletFileUpload(new DiskFileItemFactory());
            servletFileUpload.setHeaderEncoding("UTF-8");
            List<FileItem> fileItems =
                    servletFileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                response.getWriter().write(fileItem.getName());
                fileItem.write(new File(path+"/"+fileItem.getName()));
            }
        }catch (Exception e){
        }
    }
}

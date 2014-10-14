package com.rsoft.medicasoft.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UploadFileHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
/*		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		uploadHandler.setSizeMax(2097152);
		try {
			List<?> items = uploadHandler.parseRequest(request);
			Iterator<?> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
				} else {
					do {
						file = new File(TMP_DIR_PATH, new Random().nextLong()
								+ "_tmp.png");
					} while (file.exists());

					item.write(file);
				}
			}
			out.write(file.getName());
		} catch (FileUploadException ex) {
			if (ex.toString().endsWith(
					"exceeds the configured maximum (2097152)")) {
				out.write("size_size");
			}
		} catch (Exception ex) {
			log("Error encountered while uploading file", ex);
		} finally {
			out.close();
		}
*/	}

}
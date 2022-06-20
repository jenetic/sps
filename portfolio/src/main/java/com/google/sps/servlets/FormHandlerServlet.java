package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    // String textValue = request.getParameter("text-input");
    
    String textValue = Jsoup.clean(request.getParameter("text-input"), Whitelist.none());

    // Create instance of Datastore
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Messages");    
    FullEntity taskEntity =
    Entity.newBuilder(keyFactory.newKey())
        .set("title", textValue)
        .build();
    datastore.put(taskEntity);

    // Redirect back to website
    response.sendRedirect("/");
  }
}
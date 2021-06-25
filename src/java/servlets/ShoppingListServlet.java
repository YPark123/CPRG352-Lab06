
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        // if user click logout
        if (action != null && action.equals("logout")){
            //check if session exist and if so, destory session
           HttpSession session = request.getSession(false);
           session.invalidate();
        } 
        
     
        // get the session if exist
        HttpSession session = request.getSession(false);
       
       //if session exists, open shoppingList page 
       if (session != null && session.getAttribute("username") != null){
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }    
        else{
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
         }
       
        
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        
        // if action is register, create a new session and store username in the session
        if (action != null && action.equals("register")){
           if(username == null || username.equals("")){
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
        } 
           else{ 
           HttpSession session=request.getSession(true);
           session.setAttribute("username", username);
           getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
           }
        } 
        
        
        // if action is add, create an array list and store item values in the list
        if (action != null && action.equals("add")){
           HttpSession session=request.getSession(false);
           
           ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("itemList");
           // if item list is null, create a new list. 
           if(itemList == null){
                itemList = new ArrayList<>();
            }
           
           if (request.getParameter("iteminput") == null || request.getParameter("iteminput").equals("")){
               getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response); 
           }
           
           else{
               String item = request.getParameter("iteminput");
                itemList.add(item);
                session.setAttribute("itemList", itemList);
                session.setAttribute("item", item);
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
           }
           
        } 
        
        // if action is delete, remove the element from the list
        if (action != null && action.equals("delete")){
           HttpSession session = request.getSession(false);
           ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("itemList");
           String itemSelect = request.getParameter("itemselect");
           itemList.remove(itemSelect);
           session.setAttribute("itemList", itemList);
           getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
           
        }
        
    }

    

}

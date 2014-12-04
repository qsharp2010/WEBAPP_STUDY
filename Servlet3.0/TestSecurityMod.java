//It is not able to respond other than GET method.
@ServletSecurity(value = @HttpConstraint(EmptyRoleSemantic.DENY), httpMethodConstraints = @HttpMethodConstraint("GET"))
public class TestSecurityMod extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doSomething();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    doSomething();
    //Writing in the old servlet version looks like this.
    //throw new IOException("~");
  }
}

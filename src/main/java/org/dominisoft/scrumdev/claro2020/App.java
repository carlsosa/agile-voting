package org.dominisoft.scrumdev.claro2020;

import io.javalin.Javalin;
import org.dominisoft.scrumdev.claro2020.domain.DopNationalIdentificationNumber;
import org.dominisoft.scrumdev.claro2020.domain.model.User;
import org.dominisoft.scrumdev.claro2020.domain.validators.UserValidator;
import org.dominisoft.scrumdev.claro2020.domain.votes.InMemoryVotingService;

/**
 * Hello world.
 */
public final class App {
  /**
   * Default port.
   */
  public static final int DEFAULT_PORT = 7000;

  private static Javalin app;

  /**
   * Prevent instantiation.
   */
  private App() {
  }

  /**
   * Entry point.
   *
   * @param args Console arguments
   */
  public static void main(final String[] args) {
    app = Javalin.create(config -> config.addStaticFiles("/html")).start(DEFAULT_PORT);

    Runtime.getRuntime().addShutdownHook(new Thread(() -> app.stop()));

    app.events(event -> {
      event.serverStopping(() -> System.out.println("The server is stopping."));
      event.serverStopped(() -> System.out.println("Server is gone!"));
    });

    app.post("/init-voting", ctx -> {
      ctx.contentType("text/html; charset=UTF-8");
      final String rawCedula = ctx.req.getParameter("id");
      ctx.result(String.format("TODO: Validate ID '%s' (Cédula)!!!", rawCedula));
    });

    app.post("/validate-document", ctx -> {
      String doc = ctx.req.getParameter("document");
      String message = null;
      try {
        DopNationalIdentificationNumber document = new DopNationalIdentificationNumber(doc);
        if (new InMemoryVotingService().hasVoted(document)) {
          message = "La persona con la cedula: " + doc + ", ya ha votado.";
        }
      } catch (Exception e) {
        message = e.getMessage();
      }
      if (message == null) {
        ctx.result(String.format("{\"success\": \"%s\"}", "Welcome to agile voting"));
      } else {
        ctx.result(String.format("{\"error\": \"%s\"}", message));
      }
    });

    app.post("/login-success", ctx -> {
      final User user = new User(ctx.req.getParameter("password"), ctx.req.getParameter("username"));

      if (UserValidator.isAdmin(user)) {
        ctx.redirect("/login_result.html");
      } else {
        ctx.redirect("/index.html?loginError=true");
      }

    });

    app.after(ctx -> {
      // run after all requests
      final String whatHappened = ctx.req.getMethod() + " " + ctx.req.getRequestURI() + " -> "
              + ctx.res.getStatus();
      System.out.println("whatHappened: " + whatHappened);
    });
  }

  /**
   * Stops the server.
   */
  public static void stop() {
    app.stop();
  }
}

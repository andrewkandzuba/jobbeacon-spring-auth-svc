package ai.jobbeacon.security;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlBase;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JobBeaconSpringOAuth2ServerApplicationTest {

    public static final String REDIRECT_URI = "http://127.0.0.1:8080/login/oauth2/code/jobbeacon-user-management-client-oidc";

    private static final String AUTHOTIZATION_REQUEST = UriComponentsBuilder
            .fromPath("/oauth2/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", "jobbeacon-user-management-client-oidc")
            .queryParam("scope", "openid")
            .queryParam("state", "some-state")
            .queryParam("redirect_uri", REDIRECT_URI)
            .toUriString();

    @Autowired
    private WebClient webClient;

    @Test
    public void whenLoginSuccessfulThenDisplayNotFoundError() throws IOException {
        HtmlPage page = this.webClient.getPage("/");

        assertLoginPage(page);

        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        WebResponse signInResponse = signIn(page, "user", "password").getWebResponse();
        assertThat(signInResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenLoginSuccessfulThenDisplayBadCredentials() throws IOException {
        HtmlPage page = this.webClient.getPage("/");

        assertLoginPage(page);

        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        WebResponse signInResponse = signIn(page, "user", "password").getWebResponse();
        assertThat(signInResponse.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    private static <P extends Page> P signIn(HtmlPage page, String username, String password) throws IOException {
        HtmlInput usernameInput = page.querySelector("input[name=username]");
        HtmlInput passwordInput = page.querySelector("input[name=password]");
        HtmlButton signInButton = page.querySelector("button[type=submit]");

        usernameInput.type(username);
        passwordInput.type(password);

        return signInButton.click();
    }

    private static void assertLoginPage(HtmlPage page) {
        assertThat(page.getUrl().toExternalForm()).endsWith("/login");

        HtmlInput usernameInput = page.querySelector("input[name=username]");
        HtmlInput passwordInput = page.querySelector("input[name=password]");
        HtmlButton signInButton = page.querySelector("button[type=submit]");

        assertThat(usernameInput).isNotNull();
        assertThat(passwordInput).isNotNull();
        assertThat(signInButton.getTextContent()).isEqualTo("Sign in");
    }

}

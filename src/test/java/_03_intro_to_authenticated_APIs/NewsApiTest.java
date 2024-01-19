package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class NewsApiTest {

    NewsApi newsApi;
    
    Article grill;
    
    Source sour;
    
    @Mock
    Mono<ApiExampleWrapper> apiExampleWrapperMono;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	newsApi = new NewsApi();
    	
    	grill = new Article();
    	sour = new Source();
    	sour.setId("engadget");
    	sour.setName("Engadget");
    	grill.setSource(sour);
    	grill.setAuthor("Billy Steele");
    	grill.setTitle("The Perfecta grill uses AI to help cook a steak in 90 seconds");
    	grill.setDescription("CES has increasingly become a grilling show, with companies constantly finding ways to bring more tech to your deck or patio. One company that's added a dash of AI to its spice rack is Seergrills, a UK-based startup comprised of engineers and product develope…");
    	grill.setUrl("https://www.engadget.com/the-perfecta-grill-uses-ai-to-help-cook-a-steak-in-90-seconds-041030578.html");
    	grill.setUrlToImage("https://s.yimg.com/ny/api/res/1.2/y996wb.2ZH_bj_xsKzHTIg--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyMDA7aD04MDA-/https://s.yimg.com/os/creatr-uploaded-images/2024-01/fdce3a30-aea2-11ee-b7f2-21be7c0abad5");
    	grill.setPublishedAt("2024-01-09T04:10:30Z");
    	grill.setContent("CES has increasingly become a grilling show, with companies constantly finding ways to bring more tech to your deck or patio. One company that's added a dash of AI to its spice rack is Seergrills, a … [+2176 chars]");
    }

    @Test
    void itShouldGetNewsStoryByTopic() {
        //given
    	String topic = "grill";
        //when
    	ApiExampleWrapper wrap = newsApi.getNewsStoryByTopic(topic);
        Article article = wrap.getArticles().get(0);
        //then
    	assertEquals(article.getTitle(),grill.getTitle());
    }

    @Test
    void itShouldFindStory(){
        //given
    	String topic = "grill";
        String articleTitle = grill.getTitle();
        String articleContent = grill.getContent();
        String articleUrl = grill.getUrl();
    	String message =
    				articleTitle + " -\n"
                        + articleContent
                        + "\nFull article: " + articleUrl;
        //when
    	String story = newsApi.findStory(topic);
        //then
    	assertEquals(story,message);
    }


}
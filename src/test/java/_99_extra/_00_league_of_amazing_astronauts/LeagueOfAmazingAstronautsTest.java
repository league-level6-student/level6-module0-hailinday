package _99_extra._00_league_of_amazing_astronauts;

import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {
	@Mock
    Rocketship rocket;
	
    LeagueOfAmazingAstronauts underTest;
    
    @Mock
    Astronaut astro;
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	underTest = new LeagueOfAmazingAstronauts(rocket);
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given
    	Boolean expectedTrained = true;
        //when
    	when(astro.isTrained()).thenReturn(true);
    	underTest.prepareAstronaut(astro);
        //then
    	verify(rocket, times(1)).loadOccupant(astro);
    }

    @Test
    void itShouldLaunchRocket() {
        //given
    	String destination = "Mars";
        //when
    	when(rocket.isLoaded()).thenReturn(false);
    	underTest.launchRocket(destination);
        //then
    	verify(rocket, times(1)).launch();
    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() {
        //given
    	when(rocket.isLoaded()).thenReturn(false);
        //when
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket("Idk"));
        assertEquals(exceptionThrown.getMessage(), "Destination is unavailable");
        //then
    	
    }

    @Test
    void itShouldThrowNotLoaded() {
        //given
    	when(rocket.isLoaded()).thenReturn(true);
        //when
        //then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket("Mars"));
        assertEquals(exceptionThrown.getMessage(), "Rocketship is not loaded");

    }
}
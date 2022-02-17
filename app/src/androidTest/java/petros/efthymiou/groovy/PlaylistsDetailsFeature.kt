package petros.efthymiou.groovy

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import org.hamcrest.CoreMatchers
import org.junit.Test

class PlaylistsDetailsFeature : BaseUITest() {

    @Test
    fun displaysPlaylistNameAndDetails() {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.playlist_image),
                ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.playlists_list), 0))
            )
        ).perform(ViewActions.click())

        // depend on random mockApi data
        assertDisplayed("Herbert Aufderhar")

        assertDisplayed("The Nagasaki Lander is the trademarked name of several series of Nagasaki sport bikes, that started with the 1984 ABC800J")
    }

    @Test
    fun displayLoaderWhileFetchingThePlaylistDetails() {
        unregisterIdling()
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hideLoader() {
        assertNotDisplayed(R.id.loader)
    }
}
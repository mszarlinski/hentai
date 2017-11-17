package eu.solidcraft

import com.fasterxml.jackson.databind.ObjectMapper
import eu.solidcraft.base.IntegrationSpec
import eu.solidcraft.film.domain.FilmFacade
import eu.solidcraft.film.domain.SampleFilms
import eu.solidcraft.rent.RentalController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AcceptanceSpec extends IntegrationSpec implements SampleFilms {
    @Autowired
    FilmFacade filmFacade

    ObjectMapper objectMapper = new ObjectMapper()

    @WithMockUser
    def "positive renting scenario"() {
        given: 'inventory has an old film "American Clingon Bondage" and a new release of "50 shades of Trumpet"'
            filmFacade.add(trumper)
            filmFacade.add(clingon)

        when: 'I go to /films'
            ResultActions getFilms = mockMvc.perform(get("/films"))
        then: 'I see both films'
            getFilms.andExpect(status().isOk())
                    .andExpect(content().json("""
                {
                    "content": [
                        {"title":"$clingon.title","type":"$clingon.type"},
                        {"title":"$trumper.title","type":"$trumper.type"}
                    ]
                }"""))

        when: 'I go to /points'
            ResultActions points = mockMvc.perform(get("/points"))

        then: 'I see I have no points'
            points.andExpect(status().isOk())
                    .andExpect(content().json("""
                {
                    "points": 0
                }"""))

        when: 'I post to /calculate with both films for 3 days'
        then: 'I can see it will cost me 120 SEK for Trumpet and 90 SEK for Clingon'

        when: 'I post to /rent with both firms for 3 days'

            RentalController.FilmRentalRequest request = new RentalController.FilmRentalRequest()

            RentalController.FilmRentalRequestItem item1 = new RentalController.FilmRentalRequestItem()
            item1.setTitle(clingon.title)
            item1.setDuration("3")

            RentalController.FilmRentalRequestItem item2 = new RentalController.FilmRentalRequestItem()
            item1.setTitle(trumper.title)
            item1.setDuration("3")

            request.getFilms().add(item1)
            request.getFilms().add(item2)

            ResultActions rentalResult = mockMvc.perform(post("/rent")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(request)))

        then: 'I have rented both movies'
            rentalResult.andExpect(status().isOk())

        when: 'I go to /rent'
        then: 'I see both movies are rented'

        when: 'I go to /points'
        then: 'I see I have 3 points'

        when: 'I post to /return with Trumper'
        then: 'trumper is returned'

        when: 'I go to /rent'
        then: 'I see only Clingon is rented'

    }
}

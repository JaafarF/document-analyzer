package com.ailysis.documentsanalyzer.controller;

import com.ailysis.documentsanalyzer.domain.dto.DocumentRequestDto;
import com.ailysis.documentsanalyzer.domain.model.Document;
import com.ailysis.documentsanalyzer.exception.DocumentNameExistsException;
import com.ailysis.documentsanalyzer.exception.ExceptionHandlerAdvice;
import com.ailysis.documentsanalyzer.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController extends ExceptionHandlerAdvice {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public List<Document> findAllDocuments() {
        return this.documentService.findAllDocuments();
    }

    @GetMapping("/save")
    public Document saveDocument() throws IOException , DocumentNameExistsException {
        return this.documentService.saveDocument(constructDocument());
    }

    @DeleteMapping("/{documentId}")
    public String delecteDocument(@PathVariable("documentId") Long documentId) throws IOException {
        this.documentService.delecteDocument(documentId);
        return "Document deleted";
    }

    @GetMapping("/category/{categoryId}")
    public List<Document> findDocumentsByCategory(@PathVariable("categoryId") String categoryId) {
        return this.documentService.findDocumentsByCategory(categoryId);
    }

    private DocumentRequestDto constructDocument() {
        String text = "Alan Anthony Silvestri (born March 26, 1950) is an American composer and conductor known for his film and television scores. Silvestri has frequently collaborated with director Robert Zemeckis, scoring such films as the Back to the Future trilogy, Who Framed Roger Rabbit, Cast Away, The Polar Express, and Forrest Gump. He has also scored films in the Marvel Cinematic Universe: Captain America: The First Avenger, The Avengers, Avengers: Infinity War, and Avengers: Endgame, and the television series Cosmos: A Spacetime Odyssey. He is a two-time Academy Award and Golden Globe Award nominee, and a three-time Saturn Award and two-time Primetime Emmy Award recipient.\n" +
                "\n" +
                "\n" +
                "Contents\n" +
                "1\tEarly life and education\n" +
                "2\tCareer\n" +
                "3\tPersonal life\n" +
                "4\tAwards\n" +
                "5\tFilms\n" +
                "5.1\tTelevision series\n" +
                "6\tReferences\n" +
                "7\tExternal links\n" +
                "Early life and education\n" +
                "Silvestri's grandparents emigrated in 1909 from the Italian town of Castell'Alfero, and settled in Teaneck, New Jersey.[1] He grew up in Teaneck,[2] and graduated in 1968 from Teaneck High School.[3] He went to Berklee College of Music for two years. Silvestri was a drummer for a short time in 1966 with Teaneck-based rock band The Herd.\n" +
                "\n" +
                "Career\n" +
                "Silvestri started his film/television composing career in 1972 at age 21 composing the score for the low-budget action film The Doberman Gang.\n" +
                "\n" +
                "From 1977 to 1983, Silvestri served as the main composer for the television series CHiPs, writing music for 95 of the series' 139 episodes.\n" +
                "\n" +
                "Silvestri met film director Robert Zemeckis when the two worked together on Zemeckis's film Romancing the Stone (1984). Since then, Silvestri has composed the music for all of Zemeckis' movies, including the Back to the Future trilogy (1985–1990), Who Framed Roger Rabbit (1988), Death Becomes Her (1992), Forrest Gump (1994), Contact (1997), Cast Away (2000), The Polar Express (2004), Beowulf (2007), A Christmas Carol (2009), Flight (2012) and The Walk (2015).\n" +
                "\n" +
                "In 1989, Silvestri composed the score for the James Cameron-directed film The Abyss. Since 2001, Silvestri has also collaborated regularly with director Stephen Sommers, scoring the films The Mummy Returns (2001), Van Helsing (2004), and G.I. Joe: The Rise of Cobra (2009).\n" +
                "\n" +
                "Silvestri has also composed music for television series, including T. J. Hooker (one episode), Starsky & Hutch (three episodes), Tales from the Crypt (seven episodes). In 2014, he composed the award-winning music for the science documentary series Cosmos: A Spacetime Odyssey.\n" +
                "\n" +
                "Personal life\n" +
                "Silvestri and his wife Sandra own a vineyard, Silvestri Vineyards, located in Carmel Valley, California.[4] He has a daughter and two sons and has a license to fly his own jet plane.[5]\n" +
                "\n" +
                "Awards\n" +
                "Silvestri has received two Academy Award nominations, one for Best Original Score for Forrest Gump (1994) and one for Best Original Song for \"Believe\" on The Polar Express soundtrack. He also received two Golden Globe nominations: Best Score for Forrest Gump and Best Song for The Polar Express.\n" +
                "\n" +
                "Silvestri was awarded an Honorary Doctorate of Music from Berklee College of Music in 1995.[6]\n" +
                "\n" +
                "He has also received nine Grammy Award nominations, winning two awards – Best Song Written for a Motion Picture, Television or Other Visual Media, for \"Believe\" from The Polar Express in 2004 and Best Instrumental Composition, for \"Cast Away End Credits\" from Cast Away in 2002. His other nominations were for Best Album of Original Score Written for a Motion Picture or a Television Special and Best Instrumental Composition, for Back to the Future in 1985, Best Album of Original Instrumental Background Score Written for a Motion Picture or Television, for Who Framed Roger Rabbit in 1988, Best Instrumental Composition, for \"Who Framed Roger Rabbit Suite\" in 1989, Best Pop Instrumental Performance, for \"I'm Forrest...Forrest Gump (The Feather Theme)\" in 1994, Best Instrumental Composition, for Avengers: Infinity War in 2018 and Best Score Soundtrack For Visual Media, for Avengers: Endgame in 2019.[7] During the 2005 Grammy Awards, Josh Groban performed \"Believe\".\n" +
                "\n" +
                "He has won two Emmys, both for Cosmos: A Spacetime Odyssey – Outstanding Main Title Theme Music and Outstanding Music Composition for a Series for the episode \"Standing Up in the Milky Way\".\n" +
                "\n" +
                "He has won the Saturn Award for Best Music three times, for his scores for Predator (1987), Back to the Future Part III (1989/90) and Van Helsing (2004).\n" +
                "\n" +
                "On September 23, 2011, he was awarded with the Max Steiner Film Music Achievement Award by the City of Vienna at the yearly film music gala concert Hollywood in Vienna.\n" +
                "\n" +
                "Films\n" +
                "Year\tTitle\tNotes\n" +
                "1972\tThe Doberman Gang\tWith Bradford Craig\n" +
                "1975\tLas Vegas Lady\t\n" +
                "1976\tThe Amazing Dobermans\t\n" +
                "1978\tThe Fifth Floor\t\n" +
                "1983\tTiger Man\tCredited as Alan Sylvestri\n" +
                "1984\tRomancing the Stone\t\n" +
                "Par où t'es rentré ? On t'a pas vu sortir\t\n" +
                "1985\tFandango\t\n" +
                "Cat's Eye\t\n" +
                "Back to the Future\t\n" +
                "Nominated — Saturn Award for Best Music\n" +
                "Nominated — Grammy Award for Best Score Soundtrack for Visual Media\n" +
                "Summer Rental\t\n" +
                "1986\tThe Clan of the Cave Bear\t\n" +
                "The Delta Force\t\n" +
                "American Anthem\t\n" +
                "Flight of the Navigator\t\n" +
                "No Mercy\t\n" +
                "1987\tCritical Condition\t\n" +
                "Outrageous Fortune\tWon - BMI Film Music Award\n" +
                "Predator\t\n" +
                "Won - Saturn Award for Best Music\n" +
                "Won - BMI Film Music Award\n" +
                "Overboard\t\n" +
                "1988\tMac and Me\t\n" +
                "Who Framed Roger Rabbit\t\n" +
                "Also conductor\n" +
                "Won - BMI Film Music Award\n" +
                "Nominated — Saturn Award for Best Music\n" +
                "Nominated — Grammy Award for Best Score Soundtrack for Visual Media\n" +
                "My Stepmother Is an Alien\t\n" +
                "1989\tShe's Out of Control\t\n" +
                "The Abyss\tNominated — Saturn Award for Best Music\n" +
                "Back to the Future Part II\tWon - BMI Film Music Award\n" +
                "1990\tDowntown\t\n" +
                "Back to the Future Part III\t\n" +
                "Won - Saturn Award for Best Music\n" +
                "Won - BMI Film Music Award\n" +
                "Young Guns II\tThemes by Anthony Marinelli and Brian Banks\n" +
                "Predator 2\tFirst collaboration with Stephen Hopkins\n" +
                "Also conductor.\n" +
                "1991\tShattered\t\n" +
                "Also orchestrator\n" +
                "Replaced Angelo Badalamenti\n" +
                "Back to the Future: The Ride\tFilm in simulator ride\n" +
                "Soapdish\tAlso orchestrator\n" +
                "Dutch\tAlso pianist\n" +
                "Ricochet\tAlso conductor\n" +
                "Father of the Bride\tWon - BMI Film Music Award\n" +
                "1992\tTwo-Fisted Tales\t\n" +
                "Segment: Yellow\n" +
                "Segment was extracted to become an individual episode of Tales from the Crypt\n" +
                "Stop! Or My Mom Will Shoot\t\n" +
                "FernGully: The Last Rainforest\t\n" +
                "Death Becomes Her\tNominated — Saturn Award for Best Music\n" +
                "Diner\tShort film\n" +
                "The Bodyguard\tWon - BMI Film Music Award\n" +
                "Sidekicks\t\n" +
                "1993\tIn Search of the Obelisk\tIMAX film\n" +
                "Cop and a Half\t\n" +
                "Super Mario Bros.\tReplaced Jerry Goldsmith\n" +
                "Judgment Night\tSecond collaboration with Stephen Hopkins\n" +
                "Also conductor\n" +
                "Grumpy Old Men\tWon - BMI Film Music Award\n" +
                "1994\tClean Slate\t\n" +
                "Forrest Gump\t\n" +
                "Nominated — Academy Award for Best Original Score\n" +
                "Won - BMI Film Music Award\n" +
                "Nominated — Saturn Award for Best Music\n" +
                "Nominated — Golden Globe Award for Best Original Score\n" +
                "Blown Away\tThird collaboration with Stephen Hopkins\n" +
                "Also conductor\n" +
                "Richie Rich\t\n" +
                "1995\tThe Quick and the Dead\t\n" +
                "The Perez Family\t\n" +
                "Judge Dredd\t\n" +
                "Also conductor\n" +
                "Replaced David Arnold and Jerry Goldsmith\n" +
                "Father of the Bride Part II\tWon - BMI Film Music Award\n" +
                "Grumpier Old Men\n" +
                "1996\tSgt. Bilko\tAlso conductor\n" +
                "Eraser\t\n" +
                "Won - BMI Film Music Award\n" +
                "Also conductor\n" +
                "The Long Kiss Goodnight\tAlso conductor\n" +
                "1997\tFools Rush In\t\n" +
                "Volcano\tAlso conductor\n" +
                "Contact\t\n" +
                "Won - ASCAP Award for Top Box Office Films\n" +
                "Nominated — Saturn Award for Best Music\n" +
                "Mouse Hunt\tGore Verbinski\n" +
                "1998\tThe Odd Couple II\tAlso conductor\n" +
                "The Parent Trap\n" +
                "Holy Man\n" +
                "Practical Magic\t\n" +
                "Also conductor\n" +
                "Replaced Michael Nyman\n" +
                "1999\tSiegfried & Roy: The Magic Box\t\n" +
                "Stuart Little\t\n" +
                "Also conductor\n" +
                "Won - ASCAP Award for Top Box Office Films\n" +
                "2000\tReindeer Games\tAlso conductor\n" +
                "Cast Away\t\n" +
                "Also conductor\n" +
                "Won - ASCAP Award for Top Box Office Films\n" +
                "What Lies Beneath\t\n" +
                "Also conductor and orchestrator\n" +
                "Won - ASCAP Award for Top Box Office Films\n" +
                "What Women Want\tAlso conductor and orchestrator\n" +
                "2001\tThe Mexican\tAlso conductor and orchestrator\n" +
                "The Mummy Returns\tWon - ASCAP Award for Top Box Office Films\n" +
                "Serendipity\tAlso conductor\n" +
                "2002\tShowtime\tAlso conductor and orchestrator\n" +
                "Lilo & Stitch\t\n" +
                "Also choir arranger and conductor\n" +
                "Won - ASCAP Award for Top Box Office Films\n" +
                "Nominated — Annie Award for Music in a Feature Production\n" +
                "Stuart Little 2\tAlso conductor\n" +
                "Maid in Manhattan\t\n" +
                "2003\tIdentity\t\n" +
                "Also conductor\n" +
                "Replaced Angelo Badalamenti\n" +
                "Stitch! The Movie\tThemes only. Score composed by Michael Tavera\n" +
                "Lara Croft: Tomb Raider – The Cradle of Life\t\n" +
                "Also conductor\n" +
                "Replaced Craig Armstrong\n" +
                "Themes by Graeme Revell\n" +
                "Two Soldiers\t\n" +
                "Short film\n" +
                "Also conductor\n" +
                "2004\tVan Helsing\t\n" +
                "Also conductor\n" +
                "Won - ASCAP Award for Top Box Office Films\n" +
                "Nominated — Saturn Award for Best Music\n" +
                "The Polar Express\t\n" +
                "Also conductor\n" +
                "Won - ASCAP Award for Top Box Office Films\n" +
                "Nominated — Academy Award for Best Original Song\n" +
                "Nominated — Saturn Award for Best Music\n" +
                "Nominated — Golden Globe Award for Best Original Song\n" +
                "Nominated — Satellite Award for Best Original Song\n" +
                "Nominated — World Soundtrack Award for Best Original Song Written Directly for a Film\n" +
                "2006\tThe Wild\tAlso conductor\n" +
                "Night at the Museum\n" +
                "2007\tBeowulf\t\n" +
                "Also conductor and orchestrator\n" +
                "Nominated — World Soundtrack Award for Best Original Song Written Directly for a Film\n" +
                "2009\tNight at the Museum: Battle of the Smithsonian\tAlso conductor\n" +
                "G.I. Joe: The Rise of Cobra\tAlso conductor and orchestrator\n" +
                "G.I. Joe: The Invasion of Cobra Island\t\n" +
                "A Christmas Carol\tAlso lyricist, conductor and orchestrator\n" +
                "2010\tThe A-Team\t\n" +
                "Also conductor and orchestrator\n" +
                "Themes by Mike Post and Pete Carpenter\n" +
                "2011\tCaptain America: The First Avenger\t\n" +
                "Also conductor and orchestrator\n" +
                "Nominated — Saturn Award for Best Music\n" +
                "BMI Film Music Award\n" +
                "2012\tThe Avengers\tAlso conductor and orchestrator\n" +
                "Flight\t\n" +
                "2013\tThe Croods\tAlso conductor and orchestrator\n" +
                "RED 2\tThemes by Christophe Beck\n" +
                "2014\tNight at the Museum: Secret of the Tomb\t\n" +
                "2015\tThe Walk\t\n" +
                "2016\tAllied\t\n" +
                "2018\tReady Player One\t\n" +
                "Avengers: Infinity War\tScore composer only. Score orchestrated and conducted by Mark Graham\n" +
                "Welcome to Marwen\tAlso conductor\n" +
                "2019\tAvengers: Endgame\t\n" +
                "Nominated — Grammy Award for Best Score Soundtrack for Visual Media\n" +
                "\n" +
                "Score conducted with Mark Graham\n" +
                "2021\tThe Witches\t\n" +
                "\n" +
                "Television series\n" +
                "Year\tTitle\tNotes\n" +
                "1978–1979\tStarsky & Hutch\t\n" +
                "Episodes:\n" +
                "\"Deckwatch\"\n" +
                "\"Starsky's Brother\"\n" +
                "\"Sweet Revenge\"\n" +
                "1978–1983\tCHiPs\t95 episodes\n" +
                "1983\tManimal\t\n" +
                "T. J. Hooker\tEpisode: \"A Child Is Missing\"\n" +
                "1986\tAmazing Stories\tEpisode: \"Go to the Head of Class\"\n" +
                "1989–1995\tTales from the Crypt\t\n" +
                "Episodes:\n" +
                "\"And All Through the House\"\n" +
                "\"Abra Cadaver\"\n" +
                "\"Yellow\"\n" +
                "\"Beauty Rest\"\n" +
                "\"House of Horror\"\n" +
                "\"Till Death Do We Part\"\n" +
                "\"You, Murderer\"\n" +
                "2014\tCosmos: A Spacetime Odyssey\t\n" +
                "Primetime Emmy Award for Outstanding Main Title Theme Music\n" +
                "Primetime Emmy Award for Outstanding Music Composition for a Series\n" +
                "2020\tCosmos: Possible Worlds\t\n";

        DocumentRequestDto document = new DocumentRequestDto();
        document.setTitle("Alan Silvestri.txt");
        document.setContent(text);

        return document;
    }
}

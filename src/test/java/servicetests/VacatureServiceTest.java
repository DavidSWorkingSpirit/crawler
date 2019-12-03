package servicetests;

import com.example.crawlertest.repositories.VacatureRepository;
import com.example.crawlertest.services.VacatureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VacatureServiceTest {

    @Mock
    VacatureRepository vacatureRepository;

    @InjectMocks
    VacatureService vacatureService;

    @Test
    public void alleVacaturesTest(){

        int mockPage = 0;
        int mockSize = 10;
        String mockSort = "vacature";
        String mockSortDir = "desc";


    }
}

package servicetests;

import com.example.crawlertest.domein.Vacature;
import com.example.crawlertest.domein.VacatureDTO;
import com.example.crawlertest.repositories.VacatureRepository;
import com.example.crawlertest.services.VacatureService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VacatureServiceTest {

    @Mock
    private VacatureRepository vacatureRepository;

    @InjectMocks
    private VacatureService vacatureService;

    @Test
    public void alleVacaturesTest(){

        int mockPage = 0;
        int mockSize = 25;
        String mockSort = "vacature";
        String mockSortDir = "DESC";
        String mockZoekopdracht= "java";
        String mockRepoFilter= "%"+mockZoekopdracht + "%";
        Pageable mockPageable = PageRequest.of(mockPage, mockSize, Sort.Direction.valueOf(mockSortDir), mockSort);

        Vacature mockVacature = new Vacature();
        mockVacature.setTitel("testVacature");
        mockVacature.setUrl("www.mockUrl.nl");
        mockVacature.setTekst("java is een eiland");

        List<Vacature> mockVacatureLijst = new ArrayList<>();
        List<VacatureDTO> vacatureDTOList;
        mockVacatureLijst.add(mockVacature);
        PageImpl<Vacature> mockPageImpl = new PageImpl(mockVacatureLijst);

        when(vacatureRepository.findAllByTekst(mockRepoFilter, mockPageable)).thenReturn(mockPageImpl);

        vacatureDTOList = vacatureService.alleVacatures(mockPage, mockSize, mockSortDir, mockSort, mockZoekopdracht);

       Assert.assertEquals(vacatureDTOList.size(), mockVacatureLijst.size());
        Assert.assertEquals(vacatureDTOList.get(0).getTitel(), mockVacatureLijst.get(0).getTitel());

    }

    @Test
    public void alleNieuweVacaturesTest(){

        int mockPage = 0;
        int mockSize = 25;
        String mockSort = "vacature";
        String mockSortDir = "DESC";
        LocalDate mockDatum= LocalDate.now();
        Pageable mockPageable = PageRequest.of(mockPage, mockSize, Sort.Direction.valueOf(mockSortDir), mockSort);

        Vacature mockNieuweVacature = new Vacature();
        mockNieuweVacature.setTitel("testVacature");
        mockNieuweVacature.setUrl("www.mockUrl.nl");
        mockNieuweVacature.setTekst("java is een eiland");
        mockNieuweVacature.setDatum(Timestamp.valueOf(LocalDateTime.now()));

        List<Vacature> mockVacatureLijst = new ArrayList<>();
        List<VacatureDTO> vacatureDTOList;
        mockVacatureLijst.add(mockNieuweVacature);
        PageImpl<Vacature> mockPageImpl = new PageImpl(mockVacatureLijst);

        when(vacatureRepository.findAllByDatum(mockDatum, mockPageable)).thenReturn(mockPageImpl);

        vacatureDTOList = vacatureService.alleNieuweVacatures(mockPage, mockSize, mockSortDir, mockSort);

        Assert.assertEquals(vacatureDTOList.size(), mockVacatureLijst.size());
        Assert.assertTrue(vacatureDTOList.get(0).getDatum().equals(mockVacatureLijst.get(0).getDatum()));

    }

    @Test
    public void vacatureOpslaanTest() {
        Vacature mockVacature = new Vacature();
        mockVacature.setId(1L);
        mockVacature.setTitel("mockVacature");
        mockVacature.setUrl("www.mockUrl.nl");

        when(vacatureRepository.findByUrl(anyString())).thenReturn(Optional.of(mockVacature));
        boolean opgeslagen = vacatureService.vacatureOpslaan(mockVacature);
        Assert.assertTrue(opgeslagen);
    }

    }

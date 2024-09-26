package model.repository;

import model.dto.StationDto;
import model.exception.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class StationRepositoryTest {
    @Mock
    private StationDao mockDao;

    private StationRepository stationRepository = new StationRepository(mockDao);
    private final StationDto existingStation = new StationDto(8012, "DE BROUCKERE");
    private final StationDto nonExistingStation = new StationDto(9999, "NON EXISTING");

    @BeforeEach
    public void init() throws RepositoryException {
        stationRepository = new StationRepository(StationRepository.Language.FRENCH);

        Mockito.lenient().when(mockDao.select(existingStation.getKey())).thenReturn(existingStation);
        Mockito.lenient().when(mockDao.select(nonExistingStation.getKey())).thenReturn(null);
        Mockito.lenient().when(mockDao.select(null)).thenThrow(new RepositoryException("No key provided"));

        Mockito.lenient().doThrow(new RepositoryException("Insert not supported")).when(mockDao).insert(any(StationDto.class));
        Mockito.lenient().doThrow(new RepositoryException("Update not supported")).when(mockDao).update(any(StationDto.class));
        Mockito.lenient().doThrow(new RepositoryException("Delete not supported")).when(mockDao).delete(anyInt());

        List<StationDto> allStations = new ArrayList<>();
        allStations.add(existingStation);
        Mockito.lenient().when(mockDao.selectAll()).thenReturn(allStations);
    }

    @Test
    void testSelectAll() throws RepositoryException {
        stationRepository = new StationRepository(mockDao);
        mockDao.setLanguage(StationRepository.Language.FRENCH);
        var stations = stationRepository.selectAll();
        assertNotNull(stations);
        assertEquals(60, stations.size());
        assertEquals(existingStation, stations.get(0));
        verify(mockDao, times(1)).selectAll();
    }

    @Test
    void testSelectExistingKey() throws RepositoryException {
        stationRepository = new StationRepository(mockDao);
        var station = stationRepository.select(existingStation.getKey());
        assertNotNull(station);
        assertEquals(existingStation, station);
        verify(mockDao, times(1)).select(existingStation.getKey());
    }

    @Test
    void testSelectNonExistingKey() throws RepositoryException {
        stationRepository = new StationRepository(mockDao);
        var station = stationRepository.select(nonExistingStation.getKey());
        assertNull(station);
        verify(mockDao, times(1)).select(nonExistingStation.getKey());
    }

    @Test
    void testSelectNullKey() {
        stationRepository = new StationRepository(mockDao);
        assertThrows(RepositoryException.class, () -> stationRepository.select(null));
        verify(mockDao, times(1)).select(null);
    }

    @Test
    void testInsertThrowsExceptionForExisting() {
        stationRepository = new StationRepository(mockDao);
        assertThrows(RepositoryException.class, () -> stationRepository.insert(existingStation));
        verify(mockDao, times(1)).insert(existingStation);
    }

    @Test
    void testInsertThrowsExceptionForNonExisting() {
        stationRepository = new StationRepository(mockDao);
        assertThrows(RepositoryException.class, () -> stationRepository.insert(nonExistingStation));
        verify(mockDao, times(1)).insert(nonExistingStation);
    }

    @Test
    void testUpdateThrowsExceptionForExisting() {
        stationRepository = new StationRepository(mockDao);
        assertThrows(RepositoryException.class, () -> stationRepository.update(existingStation));
        verify(mockDao, times(1)).update(existingStation);
    }

    @Test
    void testUpdateThrowsExceptionForNonExisting() {
        stationRepository = new StationRepository(mockDao);
        assertThrows(RepositoryException.class, () -> stationRepository.update(nonExistingStation));
        verify(mockDao, times(1)).update(nonExistingStation);
    }

    @Test
    void testDeleteThrowsExceptionForExisting() {
        stationRepository = new StationRepository(mockDao);
        assertThrows(RepositoryException.class, () -> stationRepository.delete(existingStation.getKey()));
        verify(mockDao, times(1)).delete(existingStation.getKey());
    }

    @Test
    void testDeleteThrowsExceptionForNonExisting() {
        stationRepository = new StationRepository(mockDao);
        assertThrows(RepositoryException.class, () -> stationRepository.delete(nonExistingStation.getKey()));
        verify(mockDao, times(1)).delete(nonExistingStation.getKey());
    }
}

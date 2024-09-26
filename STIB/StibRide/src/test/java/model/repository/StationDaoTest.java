package model.repository;

import model.dto.StationDto;
import model.exception.RepositoryException;
import org.junit.jupiter.api.Test;  //JUNIT 5

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StationDaoTest {

    private final StationDto nonExisting;
    private final StationDto DE_BROUCKERE;

    StationDaoTest() {
        nonExisting = new StationDto(1,"TEST");
        DE_BROUCKERE = new StationDto(8012,"DE BROUCKERE");
    }

    @Test
    public void testInsertThrowsException() {
        StationDao stationDao = new StationDao(StationRepository.Language.FRENCH);
        assertThrows(RepositoryException.class, () -> stationDao.insert(DE_BROUCKERE));
        assertThrows(RepositoryException.class, () -> stationDao.insert(nonExisting));
    }

    @Test
    public void testDeleteThrowsException() {
        StationDao stationDao = new StationDao(StationRepository.Language.FRENCH);
        assertThrows(RepositoryException.class, () -> stationDao.delete(DE_BROUCKERE.getKey())); // Assume that 8012 exists
        assertThrows(RepositoryException.class, () -> stationDao.delete(nonExisting.getKey()));    // Non-existing station
    }

    @Test
    public void testUpdateThrowsException() {
        StationDao stationDao = new StationDao(StationRepository.Language.FRENCH);
        assertThrows(RepositoryException.class, () -> stationDao.update(DE_BROUCKERE));
        assertThrows(RepositoryException.class, () -> stationDao.update(nonExisting));
    }
    @Test
    public void testSelectExistingKey() {
        StationDao stationDao = new StationDao(StationRepository.Language.FRENCH);
        StationDto result = stationDao.select(DE_BROUCKERE.getKey());
        assertNotNull(result);
        assertEquals(DE_BROUCKERE.getKey(), result.getKey());
        assertEquals(DE_BROUCKERE.getName(), result.getName());
    }
    @Test
    public void testSelectNonExistingKey() {
        StationDao stationDao = new StationDao(StationRepository.Language.FRENCH);
        assertNull(stationDao.select(nonExisting.getKey()));
    }

    @Test
    public void testSelectNullKey() {
        StationDao stationDao = new StationDao(StationRepository.Language.FRENCH);
        assertThrows(RepositoryException.class, () -> stationDao.select(null));
    }
    @Test
    public void testSelectAll() {
        StationDao stationDao = new StationDao(StationRepository.Language.FRENCH);
        List<StationDto> stations = stationDao.selectAll();
        assertNotNull(stations);
        assertEquals(60, stations.size());
        for (StationDto station : stations) {
            assertNotNull(station.getKey());
            assertNotNull(station.getName());
        }
    }
}

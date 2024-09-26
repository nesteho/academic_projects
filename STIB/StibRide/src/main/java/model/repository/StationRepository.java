package model.repository;

import model.dto.StationDto;
import model.exception.RepositoryException;

import java.util.List;
//Av PB : code dupliqué : 1 rep par langue a cause d'un attri qui a valeur différente or mm code!
// changement en 2 tps : 1) -> 1 seul dao pr tt langue 2) 1 seul rep possible grace a attribut language
// le rep donne aux dao sa langue avant select
public class StationRepository implements Repository<Integer, StationDto>{
    private StationDao dao;
    private Language language;

    public StationRepository(Language language) throws RepositoryException {
        checkLanguage(language);
        dao = new StationDao(language);
        this.language = language;
    }
    public StationRepository(StationDao dao){
        this.dao = dao;
    }
    @Override
    public List<StationDto> selectAll(){
        dao.setLanguage(language);
        return dao.selectAll();
    }

    @Override
    public StationDto select(Integer key){
        dao.setLanguage(language);
        return dao.select(key);
    }

    @Override
    public Integer insert(StationDto item) throws RepositoryException {
        dao.setLanguage(language);
        return dao.insert(item);
    }
    @Override
    public void delete(Integer key) throws RepositoryException {
        dao.setLanguage(language);
        dao.delete(key);
    }
    @Override
    public void update(StationDto item){
        dao.setLanguage(language);
        dao.update(item);
    }
    private void checkLanguage(Language language){
        if( language== null){
            throw new RepositoryException("No language selected for the repository: " + language);
        }
    }
    public void setLanguage(Language language) {
        checkLanguage(language);
        this.language = language;
    }
    public enum Language { //car limite boolean= ok pour 2 langue MAX
        FRENCH,DUTCH;
    }
}

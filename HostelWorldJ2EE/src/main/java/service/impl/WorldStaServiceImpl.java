package service.impl;

import org.springframework.stereotype.Service;
import service.WorldStaService;

import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/06/2017.
 */
@Service
public class WorldStaServiceImpl implements WorldStaService{
    @Override
    public Map<Integer, Double[]> getAdrBySeason() {
        return null;
    }

    @Override
    public Map<String, Double[]> getAdrByCity() {
        return null;
    }

    @Override
    public Map<Integer, Double[]> getOccBySeason() {
        return null;
    }

    @Override
    public Map<String, Double[]> getOccByCity() {
        return null;
    }

    @Override
    public Map<Integer, Double[]> getRevparBySeason() {
        return null;
    }

    @Override
    public Map<String, Double[]> getRevparByCity() {
        return null;
    }

    @Override
    public Map<String, Double> getMoneyByTime() {
        return null;
    }

    @Override
    public Map<String, Double> getMoneyByCity() {
        return null;
    }

    @Override
    public Map<String, Double> getMoneyByLevel() {
        return null;
    }

    @Override
    public Map<Integer, Double> getMoneyByMonth() {
        return null;
    }

    @Override
    public Map<Integer, Double> getMoneyBySeason() {
        return null;
    }

    @Override
    public Map<Integer, Map<Integer, Integer>> getActiveByMonth() {
        return null;
    }

    @Override
    public Map<String, List<String>> getActiveByCity() {
        return null;
    }

    @Override
    public Map<String, Integer> getCityByTime() {
        return null;
    }
}

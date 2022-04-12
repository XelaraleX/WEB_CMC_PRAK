package ru.msu.cmc.webprak.model.dao;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.model.entity.BonusProgram;

import java.util.Collection;

public interface BonusProgramDAO extends BaseDAO<BonusProgram> {
    @Builder
    @Getter
    class Filter {
        private String bonusCard;
        private Integer cntKmMin;
        private Integer cntKmMax;
        private Integer cntUseKmMin;
        private Integer cntUseKmMax;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }

    Collection<BonusProgram> getBonusProgramByFilter(Filter filter);
}

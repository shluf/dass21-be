package com.carlosamaral.dass21.service;

import com.carlosamaral.dass21.enums.ScaleEnum;
import com.carlosamaral.dass21.enums.SymptomTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class SymptomService {
    private boolean between(int variable, int minValueInclusive, int maxValueInclusive) {
        return variable >= minValueInclusive && variable <= maxValueInclusive;
    }
    private ScaleEnum anxietyCalc(Integer score) {
        if (between(score, 0, 3))
            return ScaleEnum.NO_SYMPTOM;
        else if (between(score, 4, 5))
            return ScaleEnum.MILD;
        else if (between(score, 6,7))
            return ScaleEnum.MODERATE;
        else if (between(score, 8, 9))
            return ScaleEnum.SEVERE;
        else
            return ScaleEnum.VERY_SEVERE;
    }

    private ScaleEnum depressionCalc(Integer score) {
        if (between(score, 0, 4))
            return ScaleEnum.NO_SYMPTOM;
        else if (between(score, 5, 6))
            return ScaleEnum.MILD;
        else if (between(score, 7,10))
            return ScaleEnum.MODERATE;
        else if (between(score, 11, 13))
            return ScaleEnum.SEVERE;
        else
            return ScaleEnum.VERY_SEVERE;
    }

    private ScaleEnum stressCalc(Integer score) {
        if (between(score, 0, 7))
            return ScaleEnum.NO_SYMPTOM;
        else if (between(score, 8, 9))
            return ScaleEnum.MILD;
        else if (between(score, 10,12))
            return ScaleEnum.MODERATE;
        else if (between(score, 13, 16))
            return ScaleEnum.SEVERE;
        else
            return ScaleEnum.VERY_SEVERE;
    }

    public ScaleEnum getScaleByType(SymptomTypeEnum type, Integer score) {
        return switch (type) {
            case DEPRESSION -> this.depressionCalc(score);
            case ANXIETY -> this.anxietyCalc(score);
            case STRESS -> this.stressCalc(score);
        };
    }
}

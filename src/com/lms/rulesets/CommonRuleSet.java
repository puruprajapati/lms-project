package com.lms.rulesets;

public class CommonRuleSet {
    public static void isNullOrEmpty(String Input) throws RuleException {
        if(Input.isEmpty()||Input.isBlank()){
            throw new RuleException("Input cannot be empty");
        }
    }

    public static  void isNumber(String Input) throws RuleException{
        if(!RuleSet.isNumeric(Input)){
            throw new RuleException("Input should be Integer");
        }
    }
}

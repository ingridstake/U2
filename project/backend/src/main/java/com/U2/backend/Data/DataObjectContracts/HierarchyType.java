package com.U2.backend.Data.DataObjectContracts;

public enum HierarchyType {
    EVENT, PRODUCTION_CHILD, PRODUCTION, UNKNOWN;

    public static HierarchyType readHierarchyType(String str){

        switch (str){
            case "production" : return PRODUCTION;
            case "production-child" : return PRODUCTION_CHILD;
            case "event" : return EVENT;
            default : return UNKNOWN;
        }
    }
}

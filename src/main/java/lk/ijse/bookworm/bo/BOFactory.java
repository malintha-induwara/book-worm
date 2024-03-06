package lk.ijse.bookworm.bo;

import lk.ijse.bookworm.bo.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory bOFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (bOFactory==null)?bOFactory=new BOFactory():bOFactory;
    }

    public enum BOTypes{
       USER
    }

    public SuperBO getBO(BOTypes boTypes){
        switch(boTypes){
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }




}


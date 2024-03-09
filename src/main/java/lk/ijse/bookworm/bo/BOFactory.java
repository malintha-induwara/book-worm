package lk.ijse.bookworm.bo;

import lk.ijse.bookworm.bo.custom.impl.AdminBOImpl;
import lk.ijse.bookworm.bo.custom.impl.BranchBOImpl;
import lk.ijse.bookworm.bo.custom.impl.UserBOImpl;

public class BOFactory {

    private static BOFactory bOFactory;
    private BOFactory(){}

    public static BOFactory getInstance(){
        return (bOFactory==null)?bOFactory=new BOFactory():bOFactory;
    }

    public enum BOTypes{
       USER,ADMIN,BRANCH
    }

    public SuperBO getBO(BOTypes boTypes){
        switch(boTypes){
            case USER:
                return new UserBOImpl();
            case ADMIN:
                return new AdminBOImpl();
            case BRANCH:
                return new BranchBOImpl();
            default:
                return null;
        }
    }




}


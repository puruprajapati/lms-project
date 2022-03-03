package com.lms.business;

import com.lms.dataaccess.DataAccess;
import com.lms.dataaccess.DataAccessFacade;
import com.lms.model.LibraryMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MemberService {
    private static MemberService memberInstance = null;
    DataAccess da = new DataAccessFacade();

    private MemberService(){};

    public static MemberService getInstance() {
        if (memberInstance == null) {
            memberInstance = new MemberService();
        }
        return memberInstance;
    }

    public List<LibraryMember> getAllMembers(){
        List<LibraryMember> membersList = new ArrayList<>();

        HashMap<String, LibraryMember> members = da.readMemberMap();
        Set<String> keys = members.keySet();
        for (String k : keys) {
            LibraryMember lb = members.get(k);
            membersList.add(lb);
        }
        return membersList;
    }

    public void addNewMember(LibraryMember member){
        da.saveNewMember(member);
    }

    public boolean deleteMember(String memberId){
        return da.deleteMember(memberId);
    }


}

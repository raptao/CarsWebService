package fr.upem.webservice.app.soap;

import fr.upem.rmi.shared.interfaces.Member;
import fr.upem.rmi.shared.interfaces.MemberDatabase;
import fr.upem.rmi.shared.interfaces.MemberType;

import java.rmi.RemoteException;

/**
 * Created by raptao on 11/13/2016.
 */
public class MemberDataBaseSoap {

    private MemberDatabase members;

    
    public MemberDataBaseSoap(){
    }
    
    public MemberDatabase getMembers() {
		return members;
	}
	public void setMembers(MemberDatabase members) {
		this.members = members;
	}
    public MemberDataBaseSoap(MemberDatabase memberDatabase) {
        members = memberDatabase;
    }

    public SoapMember get(int id, String password) throws RemoteException {
        return new SoapMember(members.get(id, password));
    }

    public SoapMember add(String memberType, String firstName, String lastName, int accountId, String password) throws RemoteException {
        MemberType type = memberType.equals("string") ? MemberType.STUDENT : MemberType.TEACHER;
        Member add = members.add(type, firstName, lastName, accountId, password);
        return new SoapMember(add);
    }
   
}

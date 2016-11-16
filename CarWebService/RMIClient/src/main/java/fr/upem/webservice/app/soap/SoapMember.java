package fr.upem.webservice.app.soap;

import java.rmi.RemoteException;

import fr.upem.rmi.shared.interfaces.Member;

/**
 * Created by raptao on 11/13/2016.
 */
public class SoapMember {
	
	private Member member;
	
	public SoapMember(){
		
	}
	
    public Member getMember() {
		return member;
	}
    
	public void setMember(Member member) {
		this.member = member;
	}
	
	public SoapMember(Member member) {


    }
	
	public String getFirstName() throws RemoteException{
		return member.getFirstName();
	}
	
	public String getLastName() throws RemoteException{
		return member.getLastName();
	}
	
	public String getDescription() throws RemoteException{
		return member.description();
	}
}
